package com.melalex.users.dto

import play.api.libs.json.{Json, Reads}

case class UserAuthenticationDto(
    user: UserAuthenticationDto.Body
)

object UserAuthenticationDto {

  implicit val reads: Reads[UserAuthenticationDto] = Json.reads[UserAuthenticationDto]

  case class Body(
                   email: String,
                   password: String
  )
}
