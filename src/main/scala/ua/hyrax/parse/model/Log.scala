package ua.hyrax.parse.model

import java.lang.Long

/**
  * Created by devian on 15.07.16.
  */
class Log(val time: java.lang.Long, val logLevel: String,val logClass: String,val msg: String) extends Serializable{

  def tuple ={
    (time,logLevel,logClass.substring(0,logClass.length-1),msg)
  }
}
object Log{
  def apply(time: Long,logLevel: String,logClass: String,msg: String): Log = {
    new Log(time, logLevel, logClass, msg)
  }
}
