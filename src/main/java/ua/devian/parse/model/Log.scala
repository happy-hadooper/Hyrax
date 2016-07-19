package ua.devian.parse.model

/**
  * Created by devian on 15.07.16.
  */
class Log(val time: java.lang.Long, val logLevel: String,val logClass: String,val msg: String) extends Serializable{

  def tuple ={
    (time,logLevel,logClass.substring(0,logClass.length-1),msg)
  }
}
