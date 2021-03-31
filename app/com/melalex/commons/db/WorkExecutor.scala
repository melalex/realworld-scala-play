package com.melalex.commons.db

import play.api.db.slick.DatabaseConfigProvider
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

class WorkExecutor(dbConfigProvider: DatabaseConfigProvider) {

  private val dbConfig: DatabaseConfig[JdbcProfile] = dbConfigProvider.get[JdbcProfile]
  private val db                                    = dbConfig.db
  private val driver: JdbcProfile                   = dbConfig.profile

  import driver.api._

  def execute[A](work: DBIO[A]): Future[A] = db.run(work)

  def executeInTransaction[A](work: DBIO[A]): Future[A] = execute(work.transactionally)
}
