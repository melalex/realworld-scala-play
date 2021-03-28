package com.melalex.commons.errors.mappers

import com.melalex.commons.errors.dto.RealWorldErrorDto
import com.melalex.commons.errors.model.RealWorldError
import com.melalex.commons.i18n.I18nService

import java.util.Locale

class RealWorldErrorMapper(
    val i18nService: I18nService
) {

  def map(source: Seq[RealWorldError], locales: Seq[Locale]): RealWorldErrorDto = {
    val errors = source
      .map(it => i18nService.getMessage(locales, it.messageCode, it.arguments))

    RealWorldErrorDto(errors)
  }
}
