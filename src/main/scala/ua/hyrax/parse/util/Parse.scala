package ua.hyrax.parse.util

import java.time.format.{DateTimeFormatter, DateTimeParseException}
import java.time.{Instant, LocalDateTime, ZoneId, ZoneOffset}

import ua.hyrax.parse.model.{Level, Log, LogClass, State}

/**
  * Created by devian on 14.07.16.
  */

object Parse extends Serializable{
  val formatterRM: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS")
  /** the max length of time String */
  val MAX_DATE_TIME: Int = 23

  def parseLogRM(log: String): Log = {
    var dateTime: LocalDateTime = LocalDateTime.now
    try {
      dateTime = LocalDateTime.parse(log.substring(0, MAX_DATE_TIME), formatterRM)
    }
    catch {
      case e: Exception => {
        e.printStackTrace()
      }
    }
    val splitSpace: Array[String] = log.substring(MAX_DATE_TIME, log.length).split(" ")
    val logLevel: String = splitSpace(1)
    val logClass: String = splitSpace(2)
    val classes: Array[String] = logClass.split("\\.")
    // calculating offset of msg
    val size = logLevel.length + logClass.length + 2
    val msg = log.substring(MAX_DATE_TIME + size, log.length)
    val logClassValue = LogClass.withName(classes(classes.length - 1).substring(0, classes(classes.length - 1).length - 1))
    val logLevelValue = Level.withName(logLevel)
    val time = dateTime.toInstant(ZoneOffset.UTC).toEpochMilli
    Log(time, logLevelValue, logClassValue, msg)
  }

  def logWithoutDate(line: String): String = {
    line.substring(MAX_DATE_TIME, line.length)
  }

  def logWithoutLevelAndClass(line: String, size: Int): String = {
    line.substring(MAX_DATE_TIME + size, line.length)
  }

  def parseDateTime(line: String): LocalDateTime = {
    var dateTime: LocalDateTime = null
    try {
      dateTime = LocalDateTime.parse(line.substring(0, MAX_DATE_TIME), formatterRM)
    } catch {
      case e: DateTimeParseException => println("cannot parse: " + line)
      case _: Throwable => println("Some exception happened.")
    }
    dateTime
  }

  def isLogLine(str: String): Boolean = str.startsWith("201")

  def localDateTime (milis: Long) = LocalDateTime.ofInstant(Instant.ofEpochMilli(milis),ZoneId.systemDefault)


  /** generete state*/
  def generateState(time: Long,msg: String): State = {
    val tokens = msg.split(" ")
    val containerId = tokens(1)
    val from = tokens(5).intern()
    val to = tokens(7).intern()
    State(containerId,from,to,time)
  }

}
