package com.melalex.users.service

import com.melalex.commons.model.UnSaved
import com.melalex.commons.util.InstantProvider
import com.melalex.users.model.{NewUser, User, UpdateUser}

class UserFactory(
    instantProvider: InstantProvider,
    passwordHash: PasswordHash
) {

  def createNewUser(newUser: NewUser): User = {
    val now = instantProvider.provide()

    User(
      id = UnSaved,
      email = newUser.email,
      username = newUser.username,
      password = passwordHash.hash(newUser.password),
      bio = None,
      image = None,
      createdAt = now,
      updatedAt = now
    )
  }

  def createUpdatedUser(user:User, updateUser: UpdateUser): User = user.copy(
    email = updateUser.email,
    bio = updateUser.bio,
    image = updateUser.image,
    updatedAt = instantProvider.provide()
  )
}
