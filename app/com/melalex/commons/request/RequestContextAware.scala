package com.melalex.commons.request

import com.melalex.commons.auth.{AnonymousUserPrincipal, AuthService, UserPrincipal}
import com.melalex.commons.errors.RealWorldException
import com.melalex.commons.errors.mappers.RealWorldErrorMapper
import play.api.http.HeaderNames
import play.api.mvc._

import java.util.Locale
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class RequestContextAware[A](
    authService: AuthService,
    errorMapper: RealWorldErrorMapper,
    action: Action[A]
)(implicit executionContext: ExecutionContext)
    extends Action[A] {

  override def apply(request: Request[A]): Future[Result] = {
    val locales = extractLocales(request)

    extractPrincipal(request) match {
      case Success(principal)                  => action(RequestContextAwareRequest(RequestContext(principal, locales), request))
      case Failure(RealWorldException(errors)) => Future.successful(Results.Unauthorized(errorMapper.map(errors, locales)))
      case _                                   => Future.successful(Results.Unauthorized)
    }
  }
  override def parser: BodyParser[A] = action.parser

  override def executionContext: ExecutionContext = action.executionContext

  private def extractPrincipal(request: Request[A]): Try[UserPrincipal] =
    request.headers.get(HeaderNames.AUTHORIZATION) match {
      case Some(token) => authService.validateToken(token)
      case None        => Success(AnonymousUserPrincipal)
    }

  private def extractLocales(request: Request[A]): Seq[Locale] = request.acceptLanguages.map(_.toLocale)
}
