package com.practice.performance

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Scenarios {
  val getSingleUser = scenario("Get Single User")
    .exec(http("get single user")
      .get("/api/users/2"))

  val createUser = scenario("Create User")
    .exec(session => session.set("name", "vishu").set("job", "software"))
    .exec(http("Create User")
      .post("/api/users")
      //    .body(StringBody("""{"name": "morpheus","job": "leader"}""")).asJson)
      //      .body(RawFileBody("CreateUser.json")).asJson
      .body(ElFileBody("CreateUserWithPlaceholder.json")).asJson
      .check(bodyString.saveAs("ResponseBody"))
    ).exec(session => {
    val response = session("ResponseBody").as[String]
    println(s"Response body: $response")
    session
  })

  val createUserWithFeeder = scenario("create user with feeder").exec(feed(csv("user.csv").circular)
    .exec(http("Create User with Feeder")
      .post("/api/users")
      //    .body(StringBody("""{"name": "morpheus","job": "leader"}""")).asJson)
      //      .body(RawFileBody("CreateUser.json")).asJson
      .body(ElFileBody("CreateUserWithPlaceholder.json")).asJson
      .check(bodyString.saveAs("ResponseBody"))
    )).exec(session => {
    val response = session("ResponseBody").as[String]
    println(s"Response body: $response")
    session
  })
}
