package com.melalex.users.mapper

import com.melalex.users.dto.{UserDto, UserRegistrationDto}
import com.melalex.users.model.{SecurityUserDetails, UserAndToken}

object UserConversions {

  def toSecurityUserDetails(source: UserRegistrationDto): SecurityUserDetails = SecurityUserDetails(
    username = source.user.username,
    email = source.user.email,
    password = source.user.password
  )

  def toUserDto(source: UserAndToken): UserDto = UserDto(
    email = source.user.email,
    token = source.token,
    username = source.user.username,
    bio = source.user.bio,
    image = source.user.image
  )
}
