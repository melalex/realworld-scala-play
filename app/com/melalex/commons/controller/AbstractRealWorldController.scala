package com.melalex.commons.controller

import play.api.libs.json.{JsError, Reads}
import play.api.mvc.{AbstractController, BodyParser, ControllerComponents}

import scala.concurrent.ExecutionContext

abstract class AbstractRealWorldController(controllerComponents: ControllerComponents) extends AbstractController(controllerComponents) {

  implicit protected val executionContext: ExecutionContext = defaultExecutionContext

  protected def jsonToDto[A: Reads]: BodyParser[A] = parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )
}
