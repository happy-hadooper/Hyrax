package ua.hyrax.parse

/**
  * Created by devian on 14.07.16.
  */
import org.apache.spark.{SparkConf, SparkContext, rdd}
import ua.hyrax.parse.util.Print

object SparkHyrax extends Serializable{

  def main(args: Array[String]): Unit = {
    if(args.isEmpty){
      println("Add files to run this app.")
      sys.exit(1)
    }
    val conf = new SparkConf().setAppName("Hyrax").setMaster("yarn-client")
    val sc = new SparkContext(conf)
    val logsFile = args.map(path => sc.textFile(path)).reduce(_ ++ _)
    Print.transitionInfo(logsFile)

  }







}
