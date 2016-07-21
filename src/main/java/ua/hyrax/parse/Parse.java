package ua.hyrax.parse;

import ua.hyrax.parse.model.Log;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by devian on 21.07.16.
 */
// This is example of Parsing Log in Java and returning Scala object
public class Parse {
    /**
     *
     * @param log - One line of Resource Manager log.
     * @return <b>Log</b> - Scala class with time: java.lang.Long, logLevel: String logClass: String, msg: String
     */
    public static Log parseLogRM(String log){
        int MAX_DATE_TIME = 23;
        DateTimeFormatter formatterRM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
        LocalDateTime dateTime = LocalDateTime.now();
        try{
           dateTime = LocalDateTime.parse(log.substring(0,MAX_DATE_TIME),formatterRM);
        }catch(Exception e) {
            e.printStackTrace();
        }
//        i 0
//        i 1 INFO
//        i 2 org.apache.hadoop.yarn.server.resourcemanager.rmapp.attempt.RMAppAttemptImpl:
//        i 3 appattempt_1465827807080_55135_000001
//        i 4 State
//        i 5 change
//        i 6 from
//        i 7 FINISHING
//        i 8 to
//        i 9 FINISHED
        String[] splitSpace = log.substring(MAX_DATE_TIME,log.length()).split(" ");
       // for(int i = 0; i< splitSpace.length;i++){
        //    System.out.println("i " + i + " " + splitSpace[i]);
       // }
        String logLevel = splitSpace[1];
        String logClass = splitSpace[2];
        // calculating offset of msg
        int size = logLevel.length()+logClass.length()+2;
        String msg = log.substring(MAX_DATE_TIME+size,log.length());

        return new Log(dateTime.toInstant(ZoneOffset.UTC).toEpochMilli(),logLevel,logClass.substring(0,logClass.length()),msg);
    }
}
