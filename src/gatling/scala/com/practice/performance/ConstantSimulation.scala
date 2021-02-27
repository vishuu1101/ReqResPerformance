package com.practice.performance

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.language.postfixOps

import scala.concurrent.duration.DurationInt

class ConstantSimulation extends Simulation{

  val httpProtocol = http.baseUrl(URL.REQ_RES)

  setUp(Scenarios.createUserWithFeeder.inject(constantUsersPerSec(2).during(10 seconds)).protocols(httpProtocol))

}
