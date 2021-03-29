package com.melalex.users.service

import com.melalex.users.model.User
import slick.dbio.DBIO

trait UserService {

  def authenticateUser(email: String, password: String): DBIO[UserAndToken]

  type UserAndToken = (User, String)
}
