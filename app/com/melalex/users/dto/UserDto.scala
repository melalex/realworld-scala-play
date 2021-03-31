package com.melalex.users.dto

import com.melalex.users.model.UserAndToken

case class UserDto(
    email: String,
    token: String,
    username: String,
    bio: Option[String],
    image: Option[String]
)

object UserDto {

  def fromUserAndToken(source: UserAndToken): UserDto = UserDto(
    email = source.user.email,
    token = source.token,
    username = source.user.username,
    bio = source.user.bio,
    image = source.user.image
  )
}
