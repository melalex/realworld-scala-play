package com.melalex.commons.auth

import com.melalex.commons.errors.mappers.RealWorldErrorMapper
import com.melalex.commons.errors.model.RealWorldError
import play.api.http.HeaderNames
import play.api.libs.json.Json
import play.api.mvc._

import scala.util.{Failure, Success}

class Authenticator(
    tokenService: TokenService,
    errorMapper: RealWorldErrorMapper,
) {

  def extractUserInfo(headers: RequestHeader): Option[UserPrincipalWithToken] =
    headers.headers.get(HeaderNames.AUTHORIZATION) match {
      case Some(token) =>
        tokenService.validateToken(token) match {
          case Success(principal) => Some(principal)
          case Failure(_)         => None
        }
      case None => None
    }

  def onUnauthorized(headers: RequestHeader): Result =
    Results.Unauthorized(Json.toJson(errorMapper.map(Seq(RealWorldError.Unauthorized), headers.acceptLanguages)))
}
