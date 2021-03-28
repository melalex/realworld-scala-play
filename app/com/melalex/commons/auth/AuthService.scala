package com.melalex.commons.auth

import scala.util.Try

trait AuthService {

  def validateToken(token: String): Try[UserPrincipal]

  def generateNewToken(principal: UserPrincipal): String
}
