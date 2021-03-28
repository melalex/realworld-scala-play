package com.melalex.commons.auth

import play.api.mvc.{Request, WrappedRequest}

case class AuthenticatedRequest[A](
    principal: UserPrincipal,
    request: Request[A]
) extends WrappedRequest[A](request)
