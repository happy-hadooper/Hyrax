package ua.devian.parse

/**
  * Created by devian on 14.07.16.
  */
import org.apache.spark.{SparkConf, SparkContext}
import ua.devian.parse.util.Print

object SparkTest extends Serializable{

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Spark Test").setMaster("yarn-client")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val logsFile = sc.textFile(args(0))
    println("Running SparkTest...")
    Print.transitionInfo(logsFile)
    //println("Version with no collect")
    //Print.transitionInfo(logsFile)

  }







}
