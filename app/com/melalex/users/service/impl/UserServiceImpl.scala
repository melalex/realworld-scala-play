package com.melalex.users.service.impl

import com.melalex.commons.auth.TokenService
import com.melalex.commons.db.DbOps
import com.melalex.commons.errors.model.RealWorldError
import com.melalex.commons.model.ActualModelId
import com.melalex.users.model.{NewUser, UpdateUser, User, UserWithToken}
import com.melalex.users.repository.UserRepository
import com.melalex.users.service.{PasswordHash, UserFactory, UserService}
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

class UserServiceImpl(
    userRepository: UserRepository,
    tokenService: TokenService,
    passwordHash: PasswordHash,
    userFactory: UserFactory
)(implicit executionContext: ExecutionContext)
    extends UserService {

  def authenticateUser(email: String, password: String): DBIO[UserWithToken] = userRepository.findByEmail(email).flatMap {
    case Some(value) if passwordHash.verify(password, value.password) => DBIO.successful(tokenService.generateNewToken(value))
    case _                                                            => DBIO.failed(RealWorldError.InvalidCredentials.ex)
  }

  def createUser(newUser: NewUser): DBIO[UserWithToken] =
    userRepository
      .save(userFactory.createNewUser(newUser))
      .map(tokenService.generateNewToken)

  def updateUser(id: ActualModelId, updateUser: UpdateUser): DBIO[User] =
    getUserById(id)
      .map(user => userFactory.createUpdatedUser(user, updateUser))
      .flatMap(userRepository.save)

  def getUserById(id: ActualModelId): DBIO[User] =
    userRepository
      .findById(id)
      .flatMap(DbOps.foundById(id))
}
