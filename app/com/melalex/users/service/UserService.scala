package com.melalex.users.service

import com.melalex.users.model.{User, UserAndToken}
import slick.dbio.DBIO

trait UserService {

  def authenticateUser(email: String, password: String): DBIO[UserAndToken]

  def createUser(newUser: User): DBIO[UserAndToken]
}
