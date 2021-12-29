package simulations

import io.gatling.core.scenario.Simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://catfact.ninja")
    .acceptHeader(
      "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
    )
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")

  val scn =
    scenario("Send requests")
      .exec(
        http("main page")
          .get("/fact")
          .check(status is 200)
      )

  setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))
}