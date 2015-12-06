import anorm._

package io.flow.splashpage.v0.anorm.parsers {

  import io.flow.splashpage.v0.anorm.conversions.Json._

  object Publication {

    case class Mappings(value: String)

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        value = s"${prefix}${sep}value"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.splashpage.v0.models.Publication] = {
      SqlParser.str(mappings.value) map {
        case value => io.flow.splashpage.v0.models.Publication(value)
      }
    }

  }
  object Geo {

    case class Mappings(
      ipAddress: String = "ipAddress",
      latitude: String = "latitude",
      longitude: String = "longitude"
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        ipAddress = s"${prefix}${sep}ip_address",
        latitude = s"${prefix}${sep}latitude",
        longitude = s"${prefix}${sep}longitude"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.splashpage.v0.models.Geo] = {
      SqlParser.str(mappings.ipAddress).? ~
      SqlParser.str(mappings.latitude).? ~
      SqlParser.str(mappings.longitude).? map {
        case ipAddress ~ latitude ~ longitude => {
          io.flow.splashpage.v0.models.Geo(
            ipAddress = ipAddress,
            latitude = latitude,
            longitude = longitude
          )
        }
      }
    }

  }

  object Subscription {

    case class Mappings(
      guid: String = "guid",
      email: String = "email",
      publication: String = "publication",
      geo: io.flow.splashpage.v0.anorm.parsers.Geo.Mappings,
      audit: io.flow.common.v0.anorm.parsers.Audit.Mappings
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        guid = s"${prefix}${sep}guid",
        email = s"${prefix}${sep}email",
        publication = s"${prefix}${sep}publication",
        geo = io.flow.splashpage.v0.anorm.parsers.Geo.Mappings.prefix(Seq(prefix, "geo").filter(!_.isEmpty).mkString("_"), "_"),
        audit = io.flow.common.v0.anorm.parsers.Audit.Mappings.prefix(Seq(prefix, "audit").filter(!_.isEmpty).mkString("_"), "_")
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.splashpage.v0.models.Subscription] = {
      SqlParser.get[_root_.java.util.UUID](mappings.guid) ~
      SqlParser.str(mappings.email) ~
      io.flow.splashpage.v0.anorm.parsers.Publication.parser(io.flow.splashpage.v0.anorm.parsers.Publication.Mappings(mappings.publication)) ~
      io.flow.splashpage.v0.anorm.parsers.Geo.parser(mappings.geo).? ~
      io.flow.common.v0.anorm.parsers.Audit.parser(mappings.audit) map {
        case guid ~ email ~ publication ~ geo ~ audit => {
          io.flow.splashpage.v0.models.Subscription(
            guid = guid,
            email = email,
            publication = publication,
            geo = geo,
            audit = audit
          )
        }
      }
    }

  }

  object SubscriptionForm {

    case class Mappings(
      email: String = "email",
      publication: String = "publication",
      geo: io.flow.splashpage.v0.anorm.parsers.Geo.Mappings
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        email = s"${prefix}${sep}email",
        publication = s"${prefix}${sep}publication",
        geo = io.flow.splashpage.v0.anorm.parsers.Geo.Mappings.prefix(Seq(prefix, "geo").filter(!_.isEmpty).mkString("_"), "_")
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.splashpage.v0.models.SubscriptionForm] = {
      SqlParser.str(mappings.email) ~
      io.flow.splashpage.v0.anorm.parsers.Publication.parser(io.flow.splashpage.v0.anorm.parsers.Publication.Mappings(mappings.publication)) ~
      io.flow.splashpage.v0.anorm.parsers.Geo.parser(mappings.geo).? map {
        case email ~ publication ~ geo => {
          io.flow.splashpage.v0.models.SubscriptionForm(
            email = email,
            publication = publication,
            geo = geo
          )
        }
      }
    }

  }

}