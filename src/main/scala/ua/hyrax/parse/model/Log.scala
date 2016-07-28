package ua.hyrax.parse.model


import ua.hyrax.parse.model.Level._
import ua.hyrax.parse.model.LogClass.LogClass


/**
  * Created by devian on 15.07.16.
  */
// Time: millisc,
case class Log(val time: Long, val logLevel: Level,val logClass: LogClass,val msg: String) extends Serializable{
}
