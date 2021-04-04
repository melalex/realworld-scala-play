package com.melalex.commons.model

import slick.jdbc.MySQLProfile.api.{DBIO => _, MappedTo => _, Rep => _, TableQuery => _, _}

sealed trait ModelId {

  def value: Long
}

object ModelId {

  implicit val ModelIdMapping: BaseColumnType[ModelId] =
    MappedColumnType.base[ModelId, Long](
      vo => vo.value,
      id => ActualModelId(id)
    )
}

case class ActualModelId(value: Long) extends AnyVal with ModelId

object UnSaved extends ModelId {

  override def value: Long = throw new IllegalStateException("Model should be saved!")
}
