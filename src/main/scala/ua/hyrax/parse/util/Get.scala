package ua.hyrax.parse.util

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, Encoder, Encoders, SQLContext}
import ua.hyrax.parse.model.State
/**
  * Created by devian on 15.07.16.
  */
object Get extends Serializable{
  /** returns grouped by containerId states + time */
  //val sqlContext: SQLContext = SQLContext.getOrCreate(SparkContext.getOrCreate())
  //import sqlContext.implicits._

  def transitionInfo(str: Iterable[String]) = {
    str.filter(line => Parse.isLogLine(line)).map(logLine => Parse.parseLogRM(logLine)).
      filter(container => Container.inTransition(container)).map(log => Parse.generateState(log.time,log.msg)).
      groupBy(_.containerId).map(a => new Container(a._1,a._2))
  }
  def transitionInfo(str: RDD[String]) = {
    str.filter(line => Parse.isLogLine(line)).map(logLine => Parse.parseLogRM(logLine)).
      filter(container => Container.inTransition(container)).map(log => Parse.generateState(log.time,log.msg)).
      groupBy(_.containerId).map(a => new Container(a._1,a._2))
  }
//  def transitionInfo(str: Dataset[String]) = {
//
//    str.filter(line => Parse.isLogLine(line)).map(logLine => Parse.parseLogRM(logLine)).
//      filter(container => Container.inTransition(container)).map(log => Parse.generateState(log.time,log.msg)).
//      groupBy(_.containerId).mapGroups((s: String, iterator: Iterator[State]) =>new Container(s,iterator.toList))
//  }

}
