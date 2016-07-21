package ua.hyrax.parse

/**
  * Created by devian on 14.07.16.
  */
import org.apache.spark.{SparkConf, SparkContext}
import ua.hyrax.parse.util.Print

object SparkHyrax extends Serializable{

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Hyrax").setMaster("yarn-client")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val logsFile = sc.textFile(args(0))
    Print.transitionInfo(logsFile)

  }







}
