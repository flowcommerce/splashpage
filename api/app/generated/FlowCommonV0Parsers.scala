/**
 * Generated by apidoc - http://www.apidoc.me
 * Service version: 0.0.99
 * apidoc:0.11.33 http://www.apidoc.me/flow/common/0.0.99/anorm_2_x_parsers
 */
import anorm._

package io.flow.common.v0.anorm.parsers {

  import io.flow.common.v0.anorm.conversions.Standard._

  import io.flow.common.v0.anorm.conversions.Types._

  object Calendar {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "calendar"): RowParser[io.flow.common.v0.models.Calendar] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.Calendar(value)
      }
    }

  }

  object Capability {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "capability"): RowParser[io.flow.common.v0.models.Capability] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.Capability(value)
      }
    }

  }

  object ChangeType {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "change_type"): RowParser[io.flow.common.v0.models.ChangeType] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.ChangeType(value)
      }
    }

  }

  object DeliveredDuty {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "delivered_duty"): RowParser[io.flow.common.v0.models.DeliveredDuty] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.DeliveredDuty(value)
      }
    }

  }

  object Environment {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "environment"): RowParser[io.flow.common.v0.models.Environment] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.Environment(value)
      }
    }

  }

  object ExceptionType {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "exception_type"): RowParser[io.flow.common.v0.models.ExceptionType] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.ExceptionType(value)
      }
    }

  }

  object HolidayCalendar {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "holiday_calendar"): RowParser[io.flow.common.v0.models.HolidayCalendar] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.HolidayCalendar(value)
      }
    }

  }

  object MarginType {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "margin_type"): RowParser[io.flow.common.v0.models.MarginType] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.MarginType(value)
      }
    }

  }

  object MeasurementSystem {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "measurement_system"): RowParser[io.flow.common.v0.models.MeasurementSystem] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.MeasurementSystem(value)
      }
    }

  }

  object Role {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "role"): RowParser[io.flow.common.v0.models.Role] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.Role(value)
      }
    }

  }

  object RoundingMethod {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "rounding_method"): RowParser[io.flow.common.v0.models.RoundingMethod] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.RoundingMethod(value)
      }
    }

  }

  object RoundingType {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "rounding_type"): RowParser[io.flow.common.v0.models.RoundingType] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.RoundingType(value)
      }
    }

  }

  object ScheduleExceptionStatus {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "schedule_exception_status"): RowParser[io.flow.common.v0.models.ScheduleExceptionStatus] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.ScheduleExceptionStatus(value)
      }
    }

  }

  object SortDirection {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "sort_direction"): RowParser[io.flow.common.v0.models.SortDirection] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.SortDirection(value)
      }
    }

  }

  object UnitOfMeasurement {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "unit_of_measurement"): RowParser[io.flow.common.v0.models.UnitOfMeasurement] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.UnitOfMeasurement(value)
      }
    }

  }

  object UnitOfTime {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "unit_of_time"): RowParser[io.flow.common.v0.models.UnitOfTime] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.UnitOfTime(value)
      }
    }

  }

  object ValueAddedService {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "value_added_service"): RowParser[io.flow.common.v0.models.ValueAddedService] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.ValueAddedService(value)
      }
    }

  }

  object Visibility {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "visibility"): RowParser[io.flow.common.v0.models.Visibility] = {
      SqlParser.str(name) map {
        case value => io.flow.common.v0.models.Visibility(value)
      }
    }

  }

  object Address {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      text = s"$prefix${sep}text",
      streets = s"$prefix${sep}streets",
      city = s"$prefix${sep}city",
      province = s"$prefix${sep}province",
      postal = s"$prefix${sep}postal",
      country = s"$prefix${sep}country",
      latitude = s"$prefix${sep}latitude",
      longitude = s"$prefix${sep}longitude"
    )

    def parser(
      text: String = "text",
      streets: String = "streets",
      city: String = "city",
      province: String = "province",
      postal: String = "postal",
      country: String = "country",
      latitude: String = "latitude",
      longitude: String = "longitude"
    ): RowParser[io.flow.common.v0.models.Address] = {
      SqlParser.str(text).? ~
      SqlParser.get[Seq[String]](streets).? ~
      SqlParser.str(city).? ~
      SqlParser.str(province).? ~
      SqlParser.str(postal).? ~
      SqlParser.str(country).? ~
      SqlParser.str(latitude).? ~
      SqlParser.str(longitude).? map {
        case text ~ streets ~ city ~ province ~ postal ~ country ~ latitude ~ longitude => {
          io.flow.common.v0.models.Address(
            text = text,
            streets = streets,
            city = city,
            province = province,
            postal = postal,
            country = country,
            latitude = latitude,
            longitude = longitude
          )
        }
      }
    }

  }

  object Contact {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      namePrefix = s"$prefix${sep}name",
      email = s"$prefix${sep}email",
      phone = s"$prefix${sep}phone"
    )

    def parser(
      namePrefix: String = "name",
      email: String = "email",
      phone: String = "phone"
    ): RowParser[io.flow.common.v0.models.Contact] = {
      io.flow.common.v0.anorm.parsers.Name.parserWithPrefix(namePrefix) ~
      SqlParser.str(email).? ~
      SqlParser.str(phone).? map {
        case name ~ email ~ phone => {
          io.flow.common.v0.models.Contact(
            name = name,
            email = email,
            phone = phone
          )
        }
      }
    }

  }

  object Customer {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      namePrefix = s"$prefix${sep}name",
      number = s"$prefix${sep}number",
      phone = s"$prefix${sep}phone",
      email = s"$prefix${sep}email"
    )

    def parser(
      namePrefix: String = "name",
      number: String = "number",
      phone: String = "phone",
      email: String = "email"
    ): RowParser[io.flow.common.v0.models.Customer] = {
      io.flow.common.v0.anorm.parsers.Name.parserWithPrefix(namePrefix) ~
      SqlParser.str(number).? ~
      SqlParser.str(phone).? ~
      SqlParser.str(email).? map {
        case name ~ number ~ phone ~ email => {
          io.flow.common.v0.models.Customer(
            name = name,
            number = number,
            phone = phone,
            email = email
          )
        }
      }
    }

  }

  object DatetimeRange {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      from = s"$prefix${sep}from",
      to = s"$prefix${sep}to"
    )

    def parser(
      from: String = "from",
      to: String = "to"
    ): RowParser[io.flow.common.v0.models.DatetimeRange] = {
      SqlParser.get[_root_.org.joda.time.DateTime](from) ~
      SqlParser.get[_root_.org.joda.time.DateTime](to) map {
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

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      depthPrefix = s"$prefix${sep}depth",
      diameterPrefix = s"$prefix${sep}diameter",
      lengthPrefix = s"$prefix${sep}length",
      weightPrefix = s"$prefix${sep}weight",
      widthPrefix = s"$prefix${sep}width"
    )

    def parser(
      depthPrefix: String = "depth",
      diameterPrefix: String = "diameter",
      lengthPrefix: String = "length",
      weightPrefix: String = "weight",
      widthPrefix: String = "width"
    ): RowParser[io.flow.common.v0.models.Dimension] = {
      io.flow.common.v0.anorm.parsers.Measurement.parserWithPrefix(depthPrefix).? ~
      io.flow.common.v0.anorm.parsers.Measurement.parserWithPrefix(diameterPrefix).? ~
      io.flow.common.v0.anorm.parsers.Measurement.parserWithPrefix(lengthPrefix).? ~
      io.flow.common.v0.anorm.parsers.Measurement.parserWithPrefix(weightPrefix).? ~
      io.flow.common.v0.anorm.parsers.Measurement.parserWithPrefix(widthPrefix).? map {
        case depth ~ diameter ~ length ~ weight ~ width => {
          io.flow.common.v0.models.Dimension(
            depth = depth,
            diameter = diameter,
            length = length,
            weight = weight,
            width = width
          )
        }
      }
    }

  }

  object Dimensions {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      productPrefix = s"$prefix${sep}product",
      packagingPrefix = s"$prefix${sep}packaging"
    )

    def parser(
      productPrefix: String = "product",
      packagingPrefix: String = "packaging"
    ): RowParser[io.flow.common.v0.models.Dimensions] = {
      io.flow.common.v0.anorm.parsers.Dimension.parserWithPrefix(productPrefix).? ~
      io.flow.common.v0.anorm.parsers.Dimension.parserWithPrefix(packagingPrefix).? map {
        case product ~ packaging => {
          io.flow.common.v0.models.Dimensions(
            product = product,
            packaging = packaging
          )
        }
      }
    }

  }

  object Error {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      code = s"$prefix${sep}code",
      message = s"$prefix${sep}message"
    )

    def parser(
      code: String = "code",
      message: String = "message"
    ): RowParser[io.flow.common.v0.models.Error] = {
      SqlParser.str(code) ~
      SqlParser.str(message) map {
        case code ~ message => {
          io.flow.common.v0.models.Error(
            code = code,
            message = message
          )
        }
      }
    }

  }

  object Exception {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      `type` = s"$prefix${sep}type",
      datetimeRangePrefix = s"$prefix${sep}datetime_range"
    )

    def parser(
      `type`: String = "type",
      datetimeRangePrefix: String = "datetime_range"
    ): RowParser[io.flow.common.v0.models.Exception] = {
      io.flow.common.v0.anorm.parsers.ExceptionType.parser(`type`) ~
      io.flow.common.v0.anorm.parsers.DatetimeRange.parserWithPrefix(datetimeRangePrefix) map {
        case typeInstance ~ datetimeRange => {
          io.flow.common.v0.models.Exception(
            `type` = typeInstance,
            datetimeRange = datetimeRange
          )
        }
      }
    }

  }

  object ExperienceSummary {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      id = s"$prefix${sep}id",
      key = s"$prefix${sep}key",
      name = s"$prefix${sep}name",
      currency = s"$prefix${sep}currency",
      country = s"$prefix${sep}country"
    )

    def parser(
      id: String = "id",
      key: String = "key",
      name: String = "name",
      currency: String = "currency",
      country: String = "country"
    ): RowParser[io.flow.common.v0.models.ExperienceSummary] = {
      SqlParser.str(id) ~
      SqlParser.str(key) ~
      SqlParser.str(name) ~
      SqlParser.str(currency).? ~
      SqlParser.str(country).? map {
        case id ~ key ~ name ~ currency ~ country => {
          io.flow.common.v0.models.ExperienceSummary(
            id = id,
            key = key,
            name = name,
            currency = currency,
            country = country
          )
        }
      }
    }

  }

  object Healthcheck {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      status = s"$prefix${sep}status"
    )

    def parser(
      status: String = "status"
    ): RowParser[io.flow.common.v0.models.Healthcheck] = {
      SqlParser.str(status) map {
        case status => {
          io.flow.common.v0.models.Healthcheck(
            status = status
          )
        }
      }
    }

  }

  object LineItem {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      number = s"$prefix${sep}number",
      quantity = s"$prefix${sep}quantity",
      center = s"$prefix${sep}center",
      discountPrefix = s"$prefix${sep}discount"
    )

    def parser(
      number: String = "number",
      quantity: String = "quantity",
      center: String = "center",
      discountPrefix: String = "discount"
    ): RowParser[io.flow.common.v0.models.LineItem] = {
      SqlParser.str(number) ~
      SqlParser.long(quantity) ~
      SqlParser.str(center).? ~
      io.flow.common.v0.anorm.parsers.Money.parserWithPrefix(discountPrefix).? map {
        case number ~ quantity ~ center ~ discount => {
          io.flow.common.v0.models.LineItem(
            number = number,
            quantity = quantity,
            center = center,
            discount = discount
          )
        }
      }
    }

  }

  object Margin {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      `type` = s"$prefix${sep}type",
      value = s"$prefix${sep}value"
    )

    def parser(
      `type`: String = "type",
      value: String = "value"
    ): RowParser[io.flow.common.v0.models.Margin] = {
      io.flow.common.v0.anorm.parsers.MarginType.parser(`type`) ~
      SqlParser.get[BigDecimal](value) map {
        case typeInstance ~ value => {
          io.flow.common.v0.models.Margin(
            `type` = typeInstance,
            value = value
          )
        }
      }
    }

  }

  object Measurement {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      value = s"$prefix${sep}value",
      units = s"$prefix${sep}units"
    )

    def parser(
      value: String = "value",
      units: String = "units"
    ): RowParser[io.flow.common.v0.models.Measurement] = {
      SqlParser.str(value) ~
      io.flow.common.v0.anorm.parsers.UnitOfMeasurement.parser(units) map {
        case value ~ units => {
          io.flow.common.v0.models.Measurement(
            value = value,
            units = units
          )
        }
      }
    }

  }

  object Money {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      amount = s"$prefix${sep}amount",
      currency = s"$prefix${sep}currency"
    )

    def parser(
      amount: String = "amount",
      currency: String = "currency"
    ): RowParser[io.flow.common.v0.models.Money] = {
      SqlParser.get[Double](amount) ~
      SqlParser.str(currency) map {
        case amount ~ currency => {
          io.flow.common.v0.models.Money(
            amount = amount,
            currency = currency
          )
        }
      }
    }

  }

  object Name {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      first = s"$prefix${sep}first",
      last = s"$prefix${sep}last"
    )

    def parser(
      first: String = "first",
      last: String = "last"
    ): RowParser[io.flow.common.v0.models.Name] = {
      SqlParser.str(first).? ~
      SqlParser.str(last).? map {
        case first ~ last => {
          io.flow.common.v0.models.Name(
            first = first,
            last = last
          )
        }
      }
    }

  }

  object Organization {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      id = s"$prefix${sep}id",
      name = s"$prefix${sep}name",
      environment = s"$prefix${sep}environment",
      parentPrefix = s"$prefix${sep}parent"
    )

    def parser(
      id: String = "id",
      name: String = "name",
      environment: String = "environment",
      parentPrefix: String = "parent"
    ): RowParser[io.flow.common.v0.models.Organization] = {
      SqlParser.str(id) ~
      SqlParser.str(name) ~
      io.flow.common.v0.anorm.parsers.Environment.parser(environment) ~
      io.flow.common.v0.anorm.parsers.OrganizationReference.parserWithPrefix(parentPrefix).? map {
        case id ~ name ~ environment ~ parent => {
          io.flow.common.v0.models.Organization(
            id = id,
            name = name,
            environment = environment,
            parent = parent
          )
        }
      }
    }

  }

  object OrganizationReference {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      id = s"$prefix${sep}id"
    )

    def parser(
      id: String = "id"
    ): RowParser[io.flow.common.v0.models.OrganizationReference] = {
      SqlParser.str(id) map {
        case id => {
          io.flow.common.v0.models.OrganizationReference(
            id = id
          )
        }
      }
    }

  }

  object OrganizationSummary {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      id = s"$prefix${sep}id",
      name = s"$prefix${sep}name"
    )

    def parser(
      id: String = "id",
      name: String = "name"
    ): RowParser[io.flow.common.v0.models.OrganizationSummary] = {
      SqlParser.str(id) ~
      SqlParser.str(name) map {
        case id ~ name => {
          io.flow.common.v0.models.OrganizationSummary(
            id = id,
            name = name
          )
        }
      }
    }

  }

  object Price {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      amount = s"$prefix${sep}amount",
      currency = s"$prefix${sep}currency",
      label = s"$prefix${sep}label"
    )

    def parser(
      amount: String = "amount",
      currency: String = "currency",
      label: String = "label"
    ): RowParser[io.flow.common.v0.models.Price] = {
      SqlParser.get[Double](amount) ~
      SqlParser.str(currency) ~
      SqlParser.str(label) map {
        case amount ~ currency ~ label => {
          io.flow.common.v0.models.Price(
            amount = amount,
            currency = currency,
            label = label
          )
        }
      }
    }

  }

  object PriceForm {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      amount = s"$prefix${sep}amount",
      currency = s"$prefix${sep}currency"
    )

    def parser(
      amount: String = "amount",
      currency: String = "currency"
    ): RowParser[io.flow.common.v0.models.PriceForm] = {
      SqlParser.get[Double](amount) ~
      SqlParser.str(currency) map {
        case amount ~ currency => {
          io.flow.common.v0.models.PriceForm(
            amount = amount,
            currency = currency
          )
        }
      }
    }

  }

  object Rounding {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      `type` = s"$prefix${sep}type",
      method = s"$prefix${sep}method",
      value = s"$prefix${sep}value"
    )

    def parser(
      `type`: String = "type",
      method: String = "method",
      value: String = "value"
    ): RowParser[io.flow.common.v0.models.Rounding] = {
      io.flow.common.v0.anorm.parsers.RoundingType.parser(`type`) ~
      io.flow.common.v0.anorm.parsers.RoundingMethod.parser(method) ~
      SqlParser.get[BigDecimal](value) map {
        case typeInstance ~ method ~ value => {
          io.flow.common.v0.models.Rounding(
            `type` = typeInstance,
            method = method,
            value = value
          )
        }
      }
    }

  }

  object Schedule {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      calendar = s"$prefix${sep}calendar",
      holiday = s"$prefix${sep}holiday",
      exception = s"$prefix${sep}exception",
      cutoff = s"$prefix${sep}cutoff"
    )

    def parser(
      calendar: String = "calendar",
      holiday: String = "holiday",
      exception: String = "exception",
      cutoff: String = "cutoff"
    ): RowParser[io.flow.common.v0.models.Schedule] = {
      io.flow.common.v0.anorm.parsers.Calendar.parser(calendar).? ~
      io.flow.common.v0.anorm.parsers.HolidayCalendar.parser(holiday) ~
      SqlParser.get[Seq[io.flow.common.v0.models.Exception]](exception) ~
      SqlParser.str(cutoff).? map {
        case calendar ~ holiday ~ exception ~ cutoff => {
          io.flow.common.v0.models.Schedule(
            calendar = calendar,
            holiday = holiday,
            exception = exception,
            cutoff = cutoff
          )
        }
      }
    }

  }

  object User {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      id = s"$prefix${sep}id",
      email = s"$prefix${sep}email",
      namePrefix = s"$prefix${sep}name"
    )

    def parser(
      id: String = "id",
      email: String = "email",
      namePrefix: String = "name"
    ): RowParser[io.flow.common.v0.models.User] = {
      SqlParser.str(id) ~
      SqlParser.str(email).? ~
      io.flow.common.v0.anorm.parsers.Name.parserWithPrefix(namePrefix) map {
        case id ~ email ~ name => {
          io.flow.common.v0.models.User(
            id = id,
            email = email,
            name = name
          )
        }
      }
    }

  }

  object UserReference {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      id = s"$prefix${sep}id"
    )

    def parser(
      id: String = "id"
    ): RowParser[io.flow.common.v0.models.UserReference] = {
      SqlParser.str(id) map {
        case id => {
          io.flow.common.v0.models.UserReference(
            id = id
          )
        }
      }
    }

  }

  object UserSummary {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      id = s"$prefix${sep}id",
      email = s"$prefix${sep}email",
      name = s"$prefix${sep}name"
    )

    def parser(
      id: String = "id",
      email: String = "email",
      name: String = "name"
    ): RowParser[io.flow.common.v0.models.UserSummary] = {
      SqlParser.str(id) ~
      SqlParser.str(email).? ~
      SqlParser.str(name) map {
        case id ~ email ~ name => {
          io.flow.common.v0.models.UserSummary(
            id = id,
            email = email,
            name = name
          )
        }
      }
    }

  }

  object Zone {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      province = s"$prefix${sep}province",
      country = s"$prefix${sep}country"
    )

    def parser(
      province: String = "province",
      country: String = "country"
    ): RowParser[io.flow.common.v0.models.Zone] = {
      SqlParser.str(province).? ~
      SqlParser.str(country) map {
        case province ~ country => {
          io.flow.common.v0.models.Zone(
            province = province,
            country = country
          )
        }
      }
    }

  }

  object ExpandableOrganization {

    def parserWithPrefix(prefix: String, sep: String = "_") = {
      io.flow.common.v0.anorm.parsers.Organization.parserWithPrefix(prefix, sep) |
      io.flow.common.v0.anorm.parsers.OrganizationReference.parserWithPrefix(prefix, sep)
    }

    def parser() = {
      io.flow.common.v0.anorm.parsers.Organization.parser() |
      io.flow.common.v0.anorm.parsers.OrganizationReference.parser()
    }

  }

  object ExpandableUser {

    def parserWithPrefix(prefix: String, sep: String = "_") = {
      io.flow.common.v0.anorm.parsers.User.parserWithPrefix(prefix, sep) |
      io.flow.common.v0.anorm.parsers.UserReference.parserWithPrefix(prefix, sep)
    }

    def parser() = {
      io.flow.common.v0.anorm.parsers.User.parser() |
      io.flow.common.v0.anorm.parsers.UserReference.parser()
    }

  }

}