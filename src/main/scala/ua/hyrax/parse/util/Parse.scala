package ua.hyrax.parse.util

import java.lang.Throwable
import java.time.format.{DateTimeFormatter, DateTimeParseException}
import java.time.{Instant, LocalDateTime, ZoneId, ZoneOffset}

import ua.hyrax.parse.model.{Log, State}

import scala.runtime.Nothing$

/**
  * Created by devian on 14.07.16.
  */

object Parse extends Serializable{
  val formatterRM: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS")

  val CLASS_CONTAINER_IMPL = "RMContainerImpl".intern()
  val CLASS_FAIR_SCHEDULER = "FairScheduler".intern()
  /** the max length of time String */
  val MAX_DATE_TIME: Int = 23

  def parseLogRM(line: String): Log = {
    val dateTime = Option(parseDateTime(line)).orElse(None)
    val splitSpace = line.substring(MAX_DATE_TIME,line.length).split(" ".intern())
    if(splitSpace.length < 2){
      println("NotParseable: " + line)
      return Log(1L,"","",line)
    }
    val logLevel = splitSpace(1).intern()
    val logClass = splitSpace(2).intern()
    // calculating offset of msg
    val size = logLevel.length+logClass.length+2
    val msg = line.substring(MAX_DATE_TIME+size,line.length)

     Log(dateTime.getOrElse(LocalDateTime.now()).toInstant(ZoneOffset.UTC).toEpochMilli(),logLevel,logClass.substring(0,logClass.length-1).intern(),msg)
  }

  def parseDateTime(line: String): LocalDateTime = {
    var dateTime: LocalDateTime = null
    try {
      dateTime = LocalDateTime.parse(line.substring(0, MAX_DATE_TIME), formatterRM)
    } catch {
      case e: DateTimeParseException => println("cannot parse: " + line)
      case _: Throwable => println("Some other shit happened")
    }
    dateTime
  }

  def isLogLine(str: String): Boolean = str.startsWith("2016") || str.startsWith("2015")

  def localDateTime (milis: java.lang.Long) = LocalDateTime.ofInstant(Instant.ofEpochMilli(milis),ZoneId.systemDefault)


  /** generete state*/
  def generateState(time: java.lang.Long,msg: String): State = {
    val tokens = msg.split(" ")
    val containerId = tokens(1)
    val from = tokens(5).intern()
    val to = tokens(7).intern()
    State(containerId,from,to,time)
  }

}
