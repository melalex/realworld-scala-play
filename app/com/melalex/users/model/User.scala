package com.melalex.users.model

import java.time.Instant

case class User(
    id: Long,
    email: String,
    username: String,
    password: String,
    bio: Option[String],
    image: Option[String],
    createdAt: Instant,
    updatedAt: Instant,
    version: Int
)
