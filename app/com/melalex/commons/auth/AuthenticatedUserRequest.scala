package com.melalex.commons.auth

import play.api.mvc.Request
import play.api.mvc.Security.AuthenticatedRequest

case class AuthenticatedUserRequest[+A](
    principal: UserPrincipal,
    request: Request[A]
) extends AuthenticatedRequest[A, UserPrincipal](principal, request)
