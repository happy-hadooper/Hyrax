package ua.hyrax.parse

/**
  * Created by devian on 14.07.16.
  */
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext, rdd}
import ua.hyrax.parse.util.Print

object SparkHyrax extends Serializable{


  def main(args: Array[String]): Unit = {
    if(args.isEmpty){
      println("Add files to run this app.")
      sys.exit(1)
    }
    val conf = new SparkConf().setAppName("Hyrax").setMaster("local[*]")
    val sc = new SparkContext(conf)
    //val sqlContext = new SQLContext(sc)

    val logsFile = args.map(path => sc.textFile(path)).reduce(_ ++ _)
    //import sqlContext.implicits._
    //val dataSet = sqlContext.createDataset(logsFile)

    Print.transitionInfo(logsFile)
    //Print.transitionInfo(dataSet)

  }
//./bin/spark-submit --class ua.hyrax.parse.SparkHyrax --master local[*] parse-1.2-SNAPSHOT.jar file:///opt/mapr/spark/spark-1.6.1/yarn-mapr-resourcemanager-lgpbd0020.gso.aexp.com.log.2 file:///home/mapr/yarn-mapr-resourcemanager-hdprd-c01-r01-01.log.3 > log2filesReduce2.logConnection






}
