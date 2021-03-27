package com.melalex.users.dto

case class UserRegistrationDto(
    user: UserRegistrationDto.Body
)

object UserRegistrationDto {

  case class Body(
      username: String,
      email: String,
      password: String
  )
}
