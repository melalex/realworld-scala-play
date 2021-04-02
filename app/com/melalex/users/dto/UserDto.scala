package com.melalex.users.dto

import com.melalex.users.model.UserAndToken

case class UserDto(
    email: String,
    token: String,
    username: String,
    bio: Option[String],
    image: Option[String]
)
