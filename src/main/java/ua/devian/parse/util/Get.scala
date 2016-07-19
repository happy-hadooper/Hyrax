package ua.devian.parse.util

import org.apache.spark.rdd.RDD

/**
  * Created by devian on 15.07.16.
  */
object Get extends Serializable{
  /** returns grouped by containerId states + time */
  def transitionInfo(str: Iterable[String]) = {
    str.filter(Parse.isLogLine).map(Parse.parseLogRM).filter(Container.inTransition).map(log => Parse.generateState(log.time,log.msg)).groupBy(_.containerId).map(a => new Container(a._1,a._2))
  }
  def transitionInfo(str: RDD[String]) = {
    str.filter(Parse.isLogLine).map(Parse.parseLogRM).filter(Container.inTransition).map(log => Parse.generateState(log.time,log.msg)).groupBy(_.containerId).map(a => new Container(a._1,a._2))
  }


}
