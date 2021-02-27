package com.practice.performance

import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.http.Predef.http

import scala.concurrent.duration._
import scala.language.postfixOps


class SpikeSimulation extends Simulation{
  val httpProtocol = http.baseUrl(URL.REQ_RES)
  private val baselineDuration = 1 minute
  private val peakDuration = 30 seconds
  private val users = 5

  def simulation():Seq[OpenInjectionStep] = {
    var sequence = Seq[OpenInjectionStep](nothingFor(1 second))
    for (i <- 1 to 2) {
      for (j <- 1 to 3) {
        sequence :+= (constantUsersPerSec(users) during (baselineDuration))
        sequence :+= (constantUsersPerSec(users * j * 5) during (peakDuration))
      }
    }
    sequence
  }

  setUp(Scenarios.createUserWithFeeder.inject(simulation()).protocols(httpProtocol))
}
