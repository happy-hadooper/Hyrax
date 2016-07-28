package ua.hyrax.parse.util

import java.util.concurrent.atomic.AtomicInteger

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Dataset
import ua.hyrax.parse.model.{HyraxLog, Log}

/**
  * Created by devian on 15.07.16.
  */

object Print extends Serializable{

  def printLog(log: Log){
    if(Container.inTransition(log)){
      val states = Parse.generateState(log.time,log.msg)
      println("Container: " + states.containerId)
      println("Transition: " + states.from + " -> " + states.to)
    }

    println("Time: " +  log.time)
    println("Level: " + log.logLevel)
    println("Class: " + log.logClass)
    println("Msg: " + log.msg)
    println("==============================================================================================================")

  }

  def all(logs: Iterable[String]): Unit ={
    logs.filter(Parse.isLogLine).map(Parse.parseLogRM).foreach{printLog}
  }
  def container(logs: Iterable[String],container: String): Unit ={
    logs.filter(Parse.isLogLine).map(Parse.parseLogRM).foreach{printLog}
  }

  def decorate(string: String,left: String = "[",  right: String = "] ") = left + string + right

  /** Prints all logs */
  def transitionInfo(logs: Iterable[String] ): Unit = {
    val appLog = HyraxLog()
    Get.transitionInfo(logs).foreach(container => {
      println("Container: " + container.name)
      val states = container.states.toList.sortBy(_.time).map(state => {
        if(Container.killed(state.from) || Container.killed(state.to)) appLog.killedContainers.getAndIncrement()
        if(Container.completed(state.from) || Container.completed(state.to)) appLog.completedContainers.getAndIncrement()
        if(Container.released(state.from) || Container.released(state.to)) appLog.releasedContainers.getAndIncrement()
        decorate(Parse.localDateTime(state.time).format(Parse.formatterRM)) + state.from + " to " + state.to
      }).mkString(" \n")
      println(states)
      println("=========================================================================================")
    })
    println("KILLED containers: " +appLog.killedContainers.get())
    println("COMPLETED containers: " +appLog.completedContainers.get())
    println("RELEASED containers: " +appLog.releasedContainers.get())
  }
  /** SPARK
    * Print all logs
    * */
  def transitionInfo(logs: RDD[String]): Unit = {
    val appLog = HyraxLog()
    Get.transitionInfo(logs).collect().foreach(container => {
      println("Container: " + container.name) //

      val states = container.states.map(state => {
        if(Container.killed(state.from) || Container.killed(state.to)) appLog.killedContainers.getAndIncrement()
        if(Container.completed(state.from) || Container.completed(state.to)) appLog.completedContainers.getAndIncrement()
        if(Container.released(state.from) || Container.released(state.to)) appLog.releasedContainers.getAndIncrement()
        decorate(Parse.localDateTime(state.time).format(Parse.formatterRM)) + state.from + " to " + state.to
      }).mkString(" \n")
      println(states)
      println("=========================================================================================")
    })
    println("KILLED containers: " +appLog.killedContainers.get())
    println("COMPLETED containers: " +appLog.completedContainers.get())
    println("RELEASED containers: " +appLog.releasedContainers.get())
  }

//  def transitionInfo(logs: Dataset[String]): Unit = {
//    val appLog = HyraxLog()
//    Get.transitionInfo(logs).collect().foreach(container => {
//      println("Container: " + container.name) //
//
//      val states = container.states.map(state => {
//        if(Container.killed(state.from) || Container.killed(state.to)) appLog.killedContainers.getAndIncrement()
//        if(Container.completed(state.from) || Container.completed(state.to)) appLog.completedContainers.getAndIncrement()
//        if(Container.released(state.from) || Container.released(state.to)) appLog.releasedContainers.getAndIncrement()
//        decorate(Parse.localDateTime(state.time).format(Parse.formatterRM)) + state.from + " to " + state.to
//      }).mkString(" \n")
//      println(states)
//      println("=========================================================================================")
//    })
//    println("KILLED containers: " +appLog.killedContainers.get())
//    println("COMPLETED containers: " +appLog.completedContainers.get())
//    println("RELEASED containers: " +appLog.releasedContainers.get())
//  }
}
