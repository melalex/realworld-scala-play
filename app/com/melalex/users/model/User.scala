package com.melalex.users.model

import com.melalex.commons.auth.ActualUserPrincipal
import com.melalex.commons.model.ModelId

import java.time.Instant

case class User(
    id: ModelId,
    email: String,
    username: String,
    password: String,
    bio: Option[String],
    image: Option[String],
    createdAt: Instant,
    updatedAt: Instant
) {

  def asPrincipal: ActualUserPrincipal = ActualUserPrincipal(
    id = id.value,
    email = email,
    username = username
  )

  def withToken(token: String): UserWithToken = UserWithToken(this, token)
}
