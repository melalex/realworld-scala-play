package com.melalex.users.service

import com.melalex.users.model.{SecurityUserDetails, User, UserAndToken, UserDetails}
import slick.dbio.DBIO

trait UserService {

  def authenticateUser(email: String, password: String): DBIO[UserAndToken]

  def createUser(newUser: SecurityUserDetails): DBIO[UserAndToken]

  def updateUser(newUser: UserDetails): DBIO[User]
}
