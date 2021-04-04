package com.melalex.users.dto

import play.api.libs.json.{Json, Writes}

case class SingleUserDto(
    user: UserDto
)

object SingleUserDto {

  implicit val Reads: Writes[SingleUserDto] = Json.writes[SingleUserDto]
}
