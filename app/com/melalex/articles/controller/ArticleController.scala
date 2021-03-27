package com.melalex.articles.controller

import com.melalex.commons.util.AbstractRealWorldController
import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject.{Inject, Singleton}

class ArticleController(val controllerComponents: ControllerComponents) extends AbstractRealWorldController {

  def createArticle() = ???
}
