package com.melalex.users.repository.impl

import com.melalex.commons.db.DbOps
import com.melalex.commons.model.{ActualModelId, ModelId, UnSaved}
import com.melalex.users.model.User
import com.melalex.users.repository.UserRepository
import com.melalex.users.repository.impl.SlickUserRepository.query
import slick.dbio.DBIO
import slick.jdbc.MySQLProfile.api.{DBIO => _, MappedTo => _, Rep => _, TableQuery => _, _}
import slick.lifted.{ProvenShape, _}

import java.time.Instant
import scala.concurrent.ExecutionContext

class SlickUserRepository(
    implicit executionContext: ExecutionContext
) extends UserRepository {

  override def save(user: User): DBIO[User] = user.id match {
    case ActualModelId(_) => query.update(user).andThen(DBIO.successful(user))
    case UnSaved          => (query.returning(query.map(_.id)) += user).map(id => user.copy(id = id))
  }

  override def findByEmail(email: String): DBIO[Option[User]] =
    query
      .filter(_.email === email)
      .take(1)
      .result
      .headOption

  override def findById(id: ModelId): DBIO[Option[User]] =
    query
      .filter(_.id === id)
      .take(1)
      .result
      .headOption
}

object SlickUserRepository {

  val query = TableQuery[UserSchema]

  class UserSchema(tag: Tag) extends Table[User](tag, "user") {

    def id: Rep[ModelId]        = column[ModelId]("id", O.PrimaryKey, O.AutoInc)
    def email: Rep[String]      = column[String]("email", O.Unique, O.Length(320))
    def username: Rep[String]   = column[String]("username", O.Unique, O.Length(40))
    def password: Rep[String]   = column[String]("password")
    def bio: Rep[String]        = column[String]("bio", O.Length(254))
    def image: Rep[String]      = column[String]("image")
    def createdAt: Rep[Instant] = column[Instant]("created_at")
    def updatedAt: Rep[Instant] = column[Instant]("updated_at")

    override def * : ProvenShape[User] =
      (id, email, username, password, bio.?, image.?, createdAt, updatedAt) <>
        ((User.apply _).tupled, User.unapply)
  }
}
