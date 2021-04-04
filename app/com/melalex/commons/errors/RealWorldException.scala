package com.melalex.commons.errors

import com.melalex.commons.errors.model.RealWorldError

case class RealWorldException(errors: Seq[RealWorldError]) extends Exception
