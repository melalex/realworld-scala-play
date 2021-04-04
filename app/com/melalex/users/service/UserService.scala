package com.melalex.users.service

import com.melalex.commons.model.ActualModelId
import com.melalex.users.model.{NewUser, UpdateUser, User, UserWithToken}
import slick.dbio.DBIO

trait UserService {

  def authenticateUser(email: String, password: String): DBIO[UserWithToken]

  def createUser(newUser: NewUser): DBIO[UserWithToken]

  def updateUser(id: ActualModelId, updateUser: UpdateUser): DBIO[User]

  def getUserById(id: ActualModelId): DBIO[User]
}
