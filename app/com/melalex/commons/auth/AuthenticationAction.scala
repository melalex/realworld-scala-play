package com.melalex.commons.auth

import com.melalex.commons.errors.mappers.RealWorldErrorMapper
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class AuthenticationAction[B](
    parser: BodyParsers.Default,
    tokenService: TokenService,
    errorMapper: RealWorldErrorMapper,
)(implicit ec: ExecutionContext)
    extends ActionBuilderImpl[AuthenticatedUserRequest[B]](parser) {

  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]): Future[Result] = block(request)

  override protected def composeAction[A](action: Action[A]): Action[A] = new Authenticated[A](tokenService, errorMapper, action)
}
