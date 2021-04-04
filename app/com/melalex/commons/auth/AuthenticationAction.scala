package com.melalex.commons.auth

import play.api.mvc.Security.AuthenticatedBuilder
import play.api.mvc._

import scala.concurrent.ExecutionContext

class AuthenticationAction(
    defaultParser: BodyParser[AnyContent],
    authenticator: Authenticator,
)(implicit ec: ExecutionContext)
    extends AuthenticatedBuilder[UserPrincipalWithToken](authenticator.extractUserInfo, defaultParser, authenticator.onUnauthorized)
