package com.melalex.articles.dto

import com.melalex.profiles.dto.ProfileDto

import java.time.ZonedDateTime

case class ArticleDto(
    slug: String,
    tittle: String,
    description: String,
    body: String,
    tagList: List[String],
    createdAt: ZonedDateTime,
    updatedAt: ZonedDateTime,
    favorite: Boolean,
    favoritesCount: Int,
    author: ProfileDto
)
