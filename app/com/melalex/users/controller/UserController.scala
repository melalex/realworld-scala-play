package com.melalex.users.controller

import com.melalex.commons.util.AbstractRealWorldController
import com.melalex.users.dto.{UserAuthenticationDto, UserRegistrationDto, UserUpdateDto}
import play.api.mvc.{Action, AnyContent, ControllerComponents}

class UserController(val controllerComponents: ControllerComponents) extends AbstractRealWorldController {

  def authenticateUser(authentication: UserAuthenticationDto): Action[AnyContent] = ???

  def registerUser(registration: UserRegistrationDto): Action[AnyContent] = ???

  def getCurrentUser: Action[AnyContent] = ???

  def updateUser(userUpdateDto: UserUpdateDto): Action[AnyContent] = ???
}
