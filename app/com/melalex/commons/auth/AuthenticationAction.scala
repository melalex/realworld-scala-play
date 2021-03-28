package com.melalex.commons.auth

import play.api.mvc.{Action, ActionBuilderImpl, BodyParsers, Request, Result}

import scala.concurrent.{ExecutionContext, Future}

class AuthenticationAction(parser: BodyParsers.Default)(implicit ec: ExecutionContext) extends ActionBuilderImpl(parser) {

  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]): Future[Result] = block(request)

  override protected def composeAction[A](action: Action[A]): Action[A] = new Authenticated[A](action)
}
