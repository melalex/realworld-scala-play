package com.melalex.commons.request

import com.melalex.commons.auth.{ActualUserPrincipal, AnonymousUserPrincipal, UserPrincipal}

import java.util.Locale

case class RequestContext(
    principal: UserPrincipal,
    locales: Seq[Locale]
) {

  def isAuthenticated: Boolean = principal match {
    case ActualUserPrincipal(_, _, _) => true
    case AnonymousUserPrincipal       => false
  }
}
