package com.melalex.commons.request

import com.melalex.commons.auth.AuthService
import com.melalex.commons.errors.mappers.RealWorldErrorMapper
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class RequestContextAction(
    parser: BodyParsers.Default,
    authService: AuthService,
    errorMapper: RealWorldErrorMapper
)(implicit ec: ExecutionContext)
    extends ActionBuilderImpl(parser) {

  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]): Future[Result] = block(request)

  override protected def composeAction[A](action: Action[A]): Action[A] = new RequestContextAware[A](authService, errorMapper, action)
}
