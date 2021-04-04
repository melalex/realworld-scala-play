package com.melalex.commons.auth

import com.melalex.commons.model.{ActualModelId, ModelId, UnSaved}

sealed trait UserPrincipal {

  val id: ModelId
  val email: String
  val username: String
  val authenticated: Boolean
}

case class ActualUserPrincipal(
    id: ActualModelId,
    email: String,
    username: String
) extends UserPrincipal {

  override val authenticated: Boolean = true
}

object AnonymousUserPrincipal extends UserPrincipal {

  override val id: ModelId            = UnSaved
  override val email: String          = "anon@example.com"
  override val username: String       = "Anon"
  override val authenticated: Boolean = false
}
