package com.melalex.commons.auth

import com.melalex.commons.errors.RealWorldException
import com.melalex.commons.errors.mappers.RealWorldErrorMapper
import play.api.http.HeaderNames
import play.api.mvc.{Action, ActionBuilder, BodyParser, Request, Result, Results}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class Authenticated[A](
    authService: AuthService,
    errorMapper: RealWorldErrorMapper,
    action: Action[A]
) extends Action[A] {

  override def apply(request: Request[A]): Future[Result] =
    request.headers.get(HeaderNames.AUTHORIZATION) match {
      case Some(token) =>
        authService.validateToken(token) match {
          case Success(principal) => action(AuthenticatedRequest(principal, request))
          case Failure(RealWorldException(errors)) =>
            Future.successful(Results.Unauthorized(errorMapper.map(errors, request.acceptLanguages.map(_.toLocale))))
          case _ => Future.successful(Results.Unauthorized)
        }
      case None => Future.successful(Results.Unauthorized)
    }

  override def parser: BodyParser[A] = action.parser

  override def executionContext: ExecutionContext = action.executionContext
}
