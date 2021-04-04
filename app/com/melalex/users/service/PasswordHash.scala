package com.melalex.users.service

trait PasswordHash {

  def hash(password: String): String

  def verify(password: String, actualHash: String): Boolean
}
