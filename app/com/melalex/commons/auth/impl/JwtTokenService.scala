package com.melalex.commons.auth.impl

import com.melalex.commons.auth.{TokenService, UserPrincipal}

import scala.util.Try

class JwtTokenService extends TokenService {

  override def validateToken(token: String): Try[UserPrincipal] = ???

  override def generateNewToken(principal: UserPrincipal): String = ???
}
