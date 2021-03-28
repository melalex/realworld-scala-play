package com.melalex.commons.auth.impl

import com.melalex.commons.auth.{AuthService, UserPrincipal}

import scala.util.Try

class JwtAuthService extends AuthService {

  override def validateToken(token: String): Try[UserPrincipal] = ???

  override def generateNewToken(principal: UserPrincipal): String = ???
}
