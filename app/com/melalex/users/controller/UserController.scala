package com.melalex.users.controller

import com.melalex.commons.auth.AuthenticationAction
import com.melalex.commons.controller.AbstractRealWorldController
import com.melalex.commons.db.WorkExecutor
import com.melalex.users.dto._
import com.melalex.users.mapper.UserConversions
import com.melalex.users.service.UserService
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.Future

class UserController(
    authenticationAction: AuthenticationAction,
    controllerComponents: ControllerComponents,
    userService: UserService,
    workExecutor: WorkExecutor
) extends AbstractRealWorldController(controllerComponents) {

  def authenticateUser: Action[UserAuthenticationDto] = Action.async(jsonToDto[UserAuthenticationDto]) { request =>
    val unitOfWork = userService
      .authenticateUser(request.body.user.email, request.body.user.password)

    workExecutor
      .execute(unitOfWork)
      .map(UserConversions.toUserDto)
      .map(SingleUserDto(_))
      .map(Json.toJson(_))
      .map(Ok(_))
  }

  def registerUser: Action[UserRegistrationDto] = Action.async(jsonToDto[UserRegistrationDto]) { request =>
    val unitOfWork = userService.createUser(UserConversions.toSecurityUserDetails(request.body))

    workExecutor
      .executeInTransaction(unitOfWork)
      .map(UserConversions.toUserDto)
      .map(SingleUserDto(_))
      .map(Json.toJson(_))
      .map(Ok(_))
  }

  def getCurrentUser: Action[AnyContent] = authenticationAction.async {
    Future.successful(Ok(""))
  }

  def updateUser(userUpdateDto: UserUpdateDto): Action[AnyContent] = authenticationAction.async {
    Future.successful(Ok(""))
  }
}
