package ua.devian.parse.util

import java.util.concurrent.atomic.AtomicInteger

import org.apache.spark.rdd.RDD
import ua.devian.parse.model.{Log, State}

/**
  * Created by devian on 15.07.16.
  */

object Print extends Serializable{
  /** Prints all logs */
//  def transitionInfo(str: Iterable[String]): Unit = {
//    Get.transitionInfo(str).foreach((containerWithStates: (String, Iterable[(String, String, String,Long)])) => {
//      println("Container: " + containerWithStates._1) //
//      val states = containerWithStates._2.map(states => decorate(Parse.localDateTime(states._4).format(Parse.formatterRM)) + states._2 + " to " + states._3).mkString(" \n")
//      println(states)
//      println("=========================================================================================")
//    })
//  }
  def printLog(log: Log){

    if(Container.inTransition(log)){
      val states = Parse.generateState(log.time,log.msg)
      println("Container: " + states.containerId)
      println("Transition: " + states.from + " -> " + states.to)
    }

    //println("TRANSITION: " + Parser.isContainerTransition(log))
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
    val killedContainers = new AtomicInteger(0)
    val completedContainers = new AtomicInteger(0)
    val releasedContainers = new AtomicInteger(0)
    Get.transitionInfo(logs).foreach(container => {
      println("Container: " + container.name) //

      val states = container.states.map(state => {
        if(Container.killed(state.from) || Container.killed(state.to)) killedContainers.getAndIncrement()
        if(Container.completed(state.from) || Container.completed(state.to)) completedContainers.getAndIncrement()
        if(Container.released(state.from) || Container.released(state.to)) releasedContainers.getAndIncrement()
        decorate(Parse.localDateTime(state.time).format(Parse.formatterRM)) + state.from + " to " + state.to
      }).mkString(" \n")
      println(states)
      println("=========================================================================================")
    })
    println("KILLED containers: " +killedContainers.get())
    println("COMPLETED containers: " +completedContainers.get())
    println("RELEASED containers: " +releasedContainers.get())
  }
  /** SPARK
    * Print all logs
    * */
  def transitionInfo(logs: RDD[String]): Unit = {
    val killedContainers = new AtomicInteger(0)
    val completedContainers = new AtomicInteger(0)
    val releasedContainers = new AtomicInteger(0)
    Get.transitionInfo(logs).collect().foreach(container => {
      println("Container: " + container.name) //

      val states = container.states.map(state => {
        if(Container.killed(state.from) || Container.killed(state.to)) killedContainers.getAndIncrement()
        if(Container.completed(state.from) || Container.completed(state.to)) completedContainers.getAndIncrement()
        if(Container.released(state.from) || Container.released(state.to)) releasedContainers.getAndIncrement()
        decorate(Parse.localDateTime(state.time).format(Parse.formatterRM)) + state.from + " to " + state.to
      }).mkString(" \n")
      println(states)
      println("=========================================================================================")
    })
    println("KILLED containers: " +killedContainers.get())
    println("COMPLETED containers: " +completedContainers.get())
    println("RELEASED containers: " +releasedContainers.get())
  }
}
