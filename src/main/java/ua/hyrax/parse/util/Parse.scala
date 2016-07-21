package ua.hyrax.parse.util

import java.time.format.{DateTimeFormatter, DateTimeParseException}
import java.time.{Instant, LocalDateTime, ZoneId, ZoneOffset}

import ua.hyrax.parse.model.{Log, State}

/**
  * Created by devian on 14.07.16.
  */




object Parse extends Serializable{
  val formatterRM: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS")


  val CLASS_CONTAINER_IMPL = "RMContainerImpl".intern()
  val CLASS_FAIR_SCHEDULER = "FairScheduler".intern()
  /** the max length of time String */
  val MAX_DATE_TIME: Int = 23



  def parseLogRM(line: String)= {
    val dateTime = LocalDateTime.now()
    try{
    val dateTime = LocalDateTime.parse(line.substring(0,MAX_DATE_TIME),formatterRM)
    }catch {
      case e: DateTimeParseException => println("cannot parse: " + line)
      case _ => println("Some other shit happened")
    }
    val splitSpace = line.substring(MAX_DATE_TIME,line.length).split(" ")
    val logLevel = splitSpace(1)
    val logClass = splitSpace(2)
    // calculating offset of msg
    val size = logLevel.length+logClass.length+2
    val msg = line.substring(MAX_DATE_TIME+size,line.length)

     Log(dateTime.toInstant(ZoneOffset.UTC).toEpochMilli(),logLevel,logClass.substring(0,logClass.length-1),msg)
  }

  def isLogLine(str: String) ={
    str.startsWith("2016") || str.startsWith("2015")
  }


  def localDateTime (milis: java.lang.Long) ={
    val r = LocalDateTime.ofInstant(Instant.ofEpochMilli(milis),ZoneId.systemDefault)
    r
  }

  /** generete state*/
  def generateState(time: java.lang.Long,msg: String)={
    val tokens = msg.split(" ")
    val containerId = tokens(1)
    val from = tokens(5)
    val to = tokens(7)
    new State(containerId,from,to,time)
  }

}
