package ua.devian.parse

import java.util.concurrent.atomic.AtomicInteger

import ua.devian.parse.util.{Container, Parse, Print}

import scala.io.Source
/**
  * Created by devian on 13.07.16.
  */
/** This class is for running this app without Spark */
object YarnLogg extends Serializable{

  def main(args: Array[String]): Unit = {
    val logs = Source.fromFile(args(0)).getLines.toArray
     Print.transitionInfo(logs)

    }





}
