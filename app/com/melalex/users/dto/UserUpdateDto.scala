package com.melalex.users.dto

import play.api.libs.json.{Json, Reads}

case class UserUpdateDto(
    user: UserUpdateDto.Body
)

object UserUpdateDto {

  implicit val Reads: Reads[UserUpdateDto] = Json.reads[UserUpdateDto]

  case class Body(
      email: String,
      bio: Option[String],
      image: Option[String]
  )
}
