package com.melalex.users.dto

case class UserAuthenticationDto(
    user: UserAuthenticationDto.Body
)

object UserAuthenticationDto {

  case class Body(
      username: String,
      password: String
  )
}
