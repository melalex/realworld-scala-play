package com.melalex.articles.controller

import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class ArticleController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{

  def createArticle() = ???
}
