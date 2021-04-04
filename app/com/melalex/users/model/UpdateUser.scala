package com.melalex.users.model

case class UpdateUser(
    email: String,
    bio: Option[String],
    image: Option[String]
)
