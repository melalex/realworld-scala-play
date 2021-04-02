package com.melalex.commons.util.impl

import com.melalex.commons.util.InstantProvider

import java.time.{Clock, Instant}

class ClockInstantProvider(clock: Clock) extends InstantProvider {

  override def provide(): Instant = clock.instant()
}
