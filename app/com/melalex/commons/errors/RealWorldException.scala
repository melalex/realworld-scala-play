package com.melalex.commons.errors

import com.melalex.commons.errors.model.RealWorldError

case class RealWorldException(errors: List[RealWorldError]) extends Exception
