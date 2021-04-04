package com.melalex.commons.auth

import com.melalex.users.model.{User, UserWithToken}

import scala.util.Try

trait TokenService {

  def validateToken(token: String): Try[UserPrincipalWithToken]

  def generateNewToken(principal: UserPrincipal): String

  def generateNewToken(user: User): UserWithToken = user.withToken(generateNewToken(user.asPrincipal))
}
