package com.melalex

import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}

class RealWorldApplicationLoader  extends ApplicationLoader {

  override def load(context: ApplicationLoader.Context): Application = RealWorldComponents(context).application
}

case class RealWorldComponents(context: ApplicationLoader.Context) extends BuiltInComponentsFromContext(context) {

  override def router: Router = ???

  override def httpFilters: Seq[EssentialFilter] = ???
}
