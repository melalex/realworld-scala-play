package com.melalex.users.dto

case class UserDto(
    email: String,
    token: String,
    username: String,
    bio: String,
    image: String
)
