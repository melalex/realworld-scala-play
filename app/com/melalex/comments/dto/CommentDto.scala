package com.melalex.comments.dto

import com.melalex.profiles.dto.ProfileDto

import java.time.ZonedDateTime

case class CommentDto(
    id: Int,
    createdAt: ZonedDateTime,
    updatedAt: ZonedDateTime,
    body: String,
    author: ProfileDto
)
