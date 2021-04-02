package com.melalex.commons.util

import java.time.Instant

trait InstantProvider {

  def provide(): Instant
}
