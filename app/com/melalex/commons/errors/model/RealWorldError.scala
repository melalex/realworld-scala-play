package com.melalex.commons.errors.model

import com.melalex.commons.errors.RealWorldException
import com.melalex.commons.model.ActualModelId

import scala.reflect.runtime.universe._

case class RealWorldError private (
    messageCode: String,
    arguments: Any*
) {

  def ex[A]: Exception = RealWorldException(Seq(this))
}

object RealWorldError {

  val InvalidToken: RealWorldError       = RealWorldError("token.invalid")
  val Unauthorized: RealWorldError       = RealWorldError("user.unauthorized")
  val InvalidCredentials: RealWorldError = RealWorldError("user.invalid")

  def NotFound[A: TypeTag](id: ActualModelId): RealWorldError = RealWorldError(typeOf[A].getClass.getSimpleName + ".notFound", id.value)
}
