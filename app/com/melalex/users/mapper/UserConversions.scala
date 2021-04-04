package com.melalex.users.mapper

import com.melalex.users.dto.{UserDto, UserRegistrationDto, UserUpdateDto}
import com.melalex.users.model.{NewUser, UpdateUser, UserWithToken}

object UserConversions {

  def toNewUser(source: UserRegistrationDto): NewUser = NewUser(
    username = source.user.username,
    email = source.user.email,
    password = source.user.password
  )

  def toUserUpdate(source: UserUpdateDto): UpdateUser = UpdateUser(
    email = source.user.email,
    bio = source.user.bio,
    image = source.user.image
  )

  def toUserDto(source: UserWithToken): UserDto = UserDto(
    email = source.user.email,
    token = source.token,
    username = source.user.username,
    bio = source.user.bio,
    image = source.user.image
  )
}
