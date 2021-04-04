package com.melalex.commons.auth

case class UserPrincipalWithToken(
    principal: ActualUserPrincipal,
    token: String
)
