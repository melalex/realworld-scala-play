package com.melalex.commons.auth

import com.melalex.commons.errors.RealWorldException
import com.melalex.commons.errors.mappers.RealWorldErrorMapper
import com.melalex.commons.errors.model.RealWorldError
import play.api.http.HeaderNames
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future.successful
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class Authenticated[A](
    tokenService: TokenService,
    errorMapper: RealWorldErrorMapper,
    action: Action[A]
) extends Action[A] {

  override def apply(request: Request[A]): Future[Result] =
    request.headers.get(HeaderNames.AUTHORIZATION) match {
      case Some(token) =>
        tokenService.validateToken(token) match {
          case Success(principal)                  => action(AuthenticatedUserRequest(principal, request))
          case Failure(RealWorldException(errors)) => successful(Results.Unauthorized(convertToResponse(request, errors)))
          case _                                   => successful(Results.Unauthorized)
        }
      case None => successful(Results.Unauthorized)
    }

  override def parser: BodyParser[A] = action.parser

  override def executionContext: ExecutionContext = action.executionContext

  private def convertToResponse(request: Request[A], errors: List[RealWorldError]) =
    Json.toJson(errorMapper.map(errors, request.acceptLanguages))
}
