package com.melalex.users.dto

case class UserUpdateDto(
    user: UserUpdateDto.Body
)

object UserUpdateDto {

  case class Body(
      email: String,
      bio: String,
      image: String
  )
}
