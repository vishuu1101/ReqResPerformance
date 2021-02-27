package com.practice.performance

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.language.postfixOps

class BasicSimulation extends Simulation{
  val httpProtocol = http.baseUrl(URL.REQ_RES)

    setUp(Scenarios.getSingleUser.inject(atOnceUsers(1)).protocols(httpProtocol))
}
