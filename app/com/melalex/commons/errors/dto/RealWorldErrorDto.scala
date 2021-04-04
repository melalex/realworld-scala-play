package com.melalex.commons.errors.dto

import play.api.libs.json.{Json, Writes}

case class RealWorldErrorDto(
    body: Seq[String]
)

object RealWorldErrorDto {

  implicit val Writes: Writes[RealWorldErrorDto] = Json.writes[RealWorldErrorDto]
}
