package com.melalex.users.dto

import play.api.libs.json.{Json, Reads, Writes}

case class SingleUserDto(
    user: UserDto
)

object SingleUserDto {

  implicit val reads: Writes[SingleUserDto] = Json.writes[SingleUserDto]
}
