package controllers

import io.flow.splashpage.v0.Client
import io.flow.common.v0.models.Healthcheck

import play.api.libs.ws._
import play.api.test._

class IoFlowCommonV0ModelsHealthchecksSpec extends PlaySpecification {

  import scala.concurrent.ExecutionContext.Implicits.global

  private val port = 9010
  lazy val client = new Client(s"http://localhost:$port")

  "GET /_internal_/healthcheck" in new WithServer(port=port) {
    await(
      client.ioFlowCommonV0ModelsHealthchecks.getInternalAndHealthcheck()
    ) must be(
      Healthcheck("healthy")
    )
  }

}
