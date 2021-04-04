package com.melalex.users.model

case class UserWithToken(
    user: User,
    token: String
)
