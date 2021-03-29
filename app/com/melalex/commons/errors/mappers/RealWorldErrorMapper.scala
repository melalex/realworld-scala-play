package com.melalex.commons.errors.mappers

import com.melalex.commons.errors.dto.RealWorldErrorDto
import com.melalex.commons.errors.model.RealWorldError
import play.api.i18n.{Lang, MessagesApi}

class RealWorldErrorMapper(
    messagesApi: MessagesApi
) {

  def map(source: Seq[RealWorldError], languages: Seq[Lang]): RealWorldErrorDto = {
    val errors = source
      .map(it => messagesApi.preferred(languages)(it.messageCode, it.arguments))

    RealWorldErrorDto(errors)
  }
}
