package com.melalex.users.controller

import com.melalex.commons.auth.AuthenticationAction
import com.melalex.commons.controller.AbstractRealWorldController
import com.melalex.commons.db.WorkExecutor
import com.melalex.users.dto._
import com.melalex.users.mapper.UserConversions
import com.melalex.users.model.UserWithToken
import com.melalex.users.service.UserService
import play.api.libs.json._
import play.api.mvc._

class UserController(
    authenticationAction: AuthenticationAction,
    controllerComponents: ControllerComponents,
    userService: UserService,
    workExecutor: WorkExecutor
) extends AbstractRealWorldController(controllerComponents) {

  def authenticateUser: Action[UserAuthenticationDto] = Action.async(jsonToDto[UserAuthenticationDto]) { request =>
    workExecutor
      .execute(userService.authenticateUser(request.body.user.email, request.body.user.password))
      .map(okUser)
  }

  def registerUser: Action[UserRegistrationDto] = Action.async(jsonToDto[UserRegistrationDto]) { request =>
    workExecutor
      .executeInTransaction(userService.createUser(UserConversions.toNewUser(request.body)))
      .map(okUser)
  }

  def getCurrentUser: Action[AnyContent] = authenticationAction.async { request =>
    workExecutor
      .execute(userService.getUserById(request.user.principal.id))
      .map(_.withToken(request.user.token))
      .map(okUser)
  }

  def updateUser(): Action[UserUpdateDto] = authenticationAction.async(jsonToDto[UserUpdateDto]) { request =>
    workExecutor
      .executeInTransaction(userService.updateUser(request.user.principal.id, UserConversions.toUserUpdate(request.body)))
      .map(_.withToken(request.user.token))
      .map(okUser)
  }

  private def okUser(source: UserWithToken) = Ok(Json.toJson(SingleUserDto(UserConversions.toUserDto(source))))
}
