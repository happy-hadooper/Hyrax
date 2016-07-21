package ua.devian.parse;

import ua.devian.parse.model.Log;

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
        String[] splitSpace = log.substring(MAX_DATE_TIME,log.length()).split(" ");
        //TODO: check if [0] is right
        String logLevel = splitSpace[0];
        //TODO: check if [1] is right
        String logClass = splitSpace[1];
        // calculating offset of msg
        int size = logLevel.length()+logClass.length()+2;
        String msg = log.substring(MAX_DATE_TIME+size,log.length());

        return new Log(dateTime.toInstant(ZoneOffset.UTC).toEpochMilli(),logLevel,logClass.substring(0,logClass.length()-1),msg);
    }
}
