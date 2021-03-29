package com.melalex.commons.auth

import scala.util.Try

trait TokenService {

  def validateToken(token: String): Try[UserPrincipal]

  def generateNewToken(principal: UserPrincipal): String
}
