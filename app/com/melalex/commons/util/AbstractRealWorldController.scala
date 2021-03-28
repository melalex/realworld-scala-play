package com.melalex.commons.util

import play.api.mvc.{AbstractController, BaseController, ControllerComponents}

abstract class AbstractRealWorldController(controllerComponents: ControllerComponents) extends AbstractController(controllerComponents) {}
