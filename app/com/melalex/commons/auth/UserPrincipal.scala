package com.melalex.commons.auth

sealed trait UserPrincipal {

  val id: Long
  val email: String
  val username: String
}

case class ActualUserPrincipal(
    id: Long,
    email: String,
    username: String
) extends UserPrincipal

object AnonymousUserPrincipal extends UserPrincipal {

  override val id: Long         = 0
  override val email: String    = "anon@example.com"
  override val username: String = "Anon"
}
