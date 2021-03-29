package com.melalex.users.repository

import com.melalex.users.model.User
import slick.dbio.DBIO

trait UserRepository {

  def findByEmail(email: String): DBIO[User]
}
