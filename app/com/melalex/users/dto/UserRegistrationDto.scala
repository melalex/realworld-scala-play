package com.melalex.users.dto

import play.api.libs.json.{Json, Reads}

case class UserRegistrationDto(
    user: UserRegistrationDto.Body
)

object UserRegistrationDto {

  implicit val Reads: Reads[UserRegistrationDto] = Json.reads[UserRegistrationDto]

  case class Body(
      username: String,
      email: String,
      password: String
  )
}
