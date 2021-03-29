package com.melalex.users.controller

import com.melalex.commons.auth.AuthenticationAction
import com.melalex.commons.util.AbstractRealWorldController
import com.melalex.users.dto.{UserAuthenticationDto, UserRegistrationDto, UserUpdateDto}
import com.melalex.users.service.UserService
import play.api.http.JWTConfiguration
import play.api.mvc.{Action, AnyContent, ControllerComponents}

class UserController(
    authenticationAction: AuthenticationAction,
    controllerComponents: ControllerComponents,
    userService: UserService
) extends AbstractRealWorldController(controllerComponents) {

  def authenticateUser(authentication: UserAuthenticationDto): Action[AnyContent] = {
    val userAndToken = userService.authenticateUser(authentication.user.email, authentication.user.password)

  }

  def registerUser(registration: UserRegistrationDto): Action[AnyContent] = ???

  def getCurrentUser: Action[AnyContent] = authenticationAction {
    Ok("")
  }

  def updateUser(userUpdateDto: UserUpdateDto): Action[AnyContent] = authenticationAction {
    Ok("")
  }
}
