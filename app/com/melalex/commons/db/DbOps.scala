package com.melalex.commons.db

import com.melalex.commons.errors.model.RealWorldError
import com.melalex.commons.model.ActualModelId
import slick.dbio.DBIO

import scala.reflect.runtime.universe._

object DbOps {

  def foundById[A: TypeTag](id: ActualModelId)(result: Option[A]): DBIO[A] = result match {
    case Some(value) => DBIO.successful(value)
    case None        => DBIO.failed(RealWorldError.NotFound[A](id).ex)
  }

  def assertPresent[A: TypeTag](result: Option[A]): DBIO[A] = result match {
    case Some(value) => DBIO.successful(value)
    case None        => DBIO.failed(new IllegalStateException("Expected"))
  }
}
