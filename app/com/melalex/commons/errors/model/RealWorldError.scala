package com.melalex.commons.errors.model

case class RealWorldError private(
    messageCode: String,
    arguments: Any*
)

object RealWorldError {

  val InvalidToken: RealWorldError = RealWorldError("token.invalid")

  val Unauthorized: RealWorldError = RealWorldError("user.unauthorized")
}
