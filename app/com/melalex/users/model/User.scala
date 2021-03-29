package com.melalex.users.model

import java.time.ZonedDateTime

case class User(
    id: Long,
    email: String,
    username: String,
    password: String,
    bio: Option[String],
    image: Option[String],
    createdAt: ZonedDateTime,
    updatedAt: ZonedDateTime,
    version: Int
)
