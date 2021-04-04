package com.melalex.users.repository

import com.melalex.commons.model.ModelId
import com.melalex.users.model.User
import slick.dbio.DBIO

trait UserRepository {

  def save(user: User): DBIO[User]

  def findByEmail(email: String): DBIO[Option[User]]

  def findById(id: ModelId): DBIO[Option[User]]
}
