package com.melalex.commons.i18n

import com.melalex.commons.request.RequestContext

import java.util.Locale

trait I18nService {

  def getMessage(locales: Seq[Locale], code: String, args: Any*): String

  def getMessage(code: String, args: Any*)(implicit session: RequestContext): String = getMessage(session.locales, code, args)
}
