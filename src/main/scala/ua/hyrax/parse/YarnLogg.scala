package ua.hyrax.parse

import ua.hyrax.parse.util.Print

import scala.io.Source
/**
  * Created by devian on 13.07.16.
  */
/** This class is for running this app without Spark */
object YarnLogg extends Serializable{

  def main(args: Array[String]): Unit = {
    if(args.length < 1){
      println("first argument should be file to parse")
      sys.exit(1)
    }
    val logs = Source.fromFile(args(0)).getLines.toArray

     Print.transitionInfo(logs)

    }





}
