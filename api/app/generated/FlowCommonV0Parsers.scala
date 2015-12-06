import anorm._

package io.flow.common.v0.anorm.parsers {

  import io.flow.common.v0.anorm.conversions.Json._

  object Calendar {

    case class Mappings(value: String)

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        value = s"${prefix}${sep}value"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.Calendar] = {
      SqlParser.str(mappings.value) map {
        case value => io.flow.common.v0.models.Calendar(value)
      }
    }

  }
  object Capability {

    case class Mappings(value: String)

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        value = s"${prefix}${sep}value"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.Capability] = {
      SqlParser.str(mappings.value) map {
        case value => io.flow.common.v0.models.Capability(value)
      }
    }

  }
  object ScheduleExceptionStatus {

    case class Mappings(value: String)

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        value = s"${prefix}${sep}value"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.ScheduleExceptionStatus] = {
      SqlParser.str(mappings.value) map {
        case value => io.flow.common.v0.models.ScheduleExceptionStatus(value)
      }
    }

  }
  object UnitOfMeasurement {

    case class Mappings(value: String)

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        value = s"${prefix}${sep}value"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.UnitOfMeasurement] = {
      SqlParser.str(mappings.value) map {
        case value => io.flow.common.v0.models.UnitOfMeasurement(value)
      }
    }

  }
  object ValueAddedService {

    case class Mappings(value: String)

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        value = s"${prefix}${sep}value"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.ValueAddedService] = {
      SqlParser.str(mappings.value) map {
        case value => io.flow.common.v0.models.ValueAddedService(value)
      }
    }

  }
  object Address {

    case class Mappings(
      address: String = "address"
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        address = s"${prefix}${sep}address"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.Address] = {
      SqlParser.str(mappings.address) map {
        case address => {
          io.flow.common.v0.models.Address(
            address = address
          )
        }
      }
    }

  }

  object Audit {

    case class Mappings(
      createdAt: String = "created_at",
      createdBy: io.flow.common.v0.anorm.parsers.Reference.Mappings,
      updatedAt: String = "updated_at",
      updatedBy: io.flow.common.v0.anorm.parsers.Reference.Mappings
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        createdAt = s"${prefix}${sep}created_at",
        createdBy = io.flow.common.v0.anorm.parsers.Reference.Mappings.prefix(Seq(prefix, "created_by").filter(!_.isEmpty).mkString("_"), "_"),
        updatedAt = s"${prefix}${sep}updated_at",
        updatedBy = io.flow.common.v0.anorm.parsers.Reference.Mappings.prefix(Seq(prefix, "updated_by").filter(!_.isEmpty).mkString("_"), "_")
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.Audit] = {
      SqlParser.get[_root_.org.joda.time.DateTime](mappings.createdAt) ~
      io.flow.common.v0.anorm.parsers.Reference.parser(mappings.createdBy) ~
      SqlParser.get[_root_.org.joda.time.DateTime](mappings.updatedAt) ~
      io.flow.common.v0.anorm.parsers.Reference.parser(mappings.updatedBy) map {
        case createdAt ~ createdBy ~ updatedAt ~ updatedBy => {
          io.flow.common.v0.models.Audit(
            createdAt = createdAt,
            createdBy = createdBy,
            updatedAt = updatedAt,
            updatedBy = updatedBy
          )
        }
      }
    }

  }

  object DatetimeRange {

    case class Mappings(
      from: String = "from",
      to: String = "to"
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        from = s"${prefix}${sep}from",
        to = s"${prefix}${sep}to"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.DatetimeRange] = {
      SqlParser.get[_root_.org.joda.time.DateTime](mappings.from) ~
      SqlParser.get[_root_.org.joda.time.DateTime](mappings.to) map {
        case from ~ to => {
          io.flow.common.v0.models.DatetimeRange(
            from = from,
            to = to
          )
        }
      }
    }

  }

  object Dimension {

    case class Mappings(
      value: String = "value",
      units: String = "units"
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        value = s"${prefix}${sep}value",
        units = s"${prefix}${sep}units"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.Dimension] = {
      SqlParser.get[Double](mappings.value) ~
      io.flow.common.v0.anorm.parsers.UnitOfMeasurement.parser(io.flow.common.v0.anorm.parsers.UnitOfMeasurement.Mappings(mappings.units)) map {
        case value ~ units => {
          io.flow.common.v0.models.Dimension(
            value = value,
            units = units
          )
        }
      }
    }

  }

  object Error {

    case class Mappings(
      code: String = "code",
      message: String = "message"
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        code = s"${prefix}${sep}code",
        message = s"${prefix}${sep}message"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.Error] = {
      SqlParser.str(mappings.code) ~
      SqlParser.str(mappings.message) map {
        case code ~ message => {
          io.flow.common.v0.models.Error(
            code = code,
            message = message
          )
        }
      }
    }

  }

  object Healthcheck {

    case class Mappings(
      status: String = "status"
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        status = s"${prefix}${sep}status"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.Healthcheck] = {
      SqlParser.str(mappings.status) map {
        case status => {
          io.flow.common.v0.models.Healthcheck(
            status = status
          )
        }
      }
    }

  }

  object Price {

    case class Mappings(
      amount: String = "amount",
      currency: String = "currency"
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        amount = s"${prefix}${sep}amount",
        currency = s"${prefix}${sep}currency"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.Price] = {
      SqlParser.get[BigDecimal](mappings.amount) ~
      SqlParser.str(mappings.currency) map {
        case amount ~ currency => {
          io.flow.common.v0.models.Price(
            amount = amount,
            currency = currency
          )
        }
      }
    }

  }

  object Reference {

    case class Mappings(
      guid: String = "guid"
    )

    object Mappings {

      val base = prefix("", "")

      def table(table: String) = prefix(table, ".")

      def prefix(prefix: String, sep: String) = Mappings(
        guid = s"${prefix}${sep}guid"
      )

    }

    def table(table: String) = parser(Mappings.prefix(table, "."))

    def parser(mappings: Mappings): RowParser[io.flow.common.v0.models.Reference] = {
      SqlParser.get[_root_.java.util.UUID](mappings.guid) map {
        case guid => {
          io.flow.common.v0.models.Reference(
            guid = guid
          )
        }
      }
    }

  }

}