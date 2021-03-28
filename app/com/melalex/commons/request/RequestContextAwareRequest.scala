package com.melalex.commons.request

import play.api.mvc.{Request, WrappedRequest}

case class RequestContextAwareRequest[A](
    context: RequestContext,
    request: Request[A]
) extends WrappedRequest[A](request)
