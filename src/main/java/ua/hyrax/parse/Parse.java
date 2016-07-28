package ua.hyrax.parse;

import scala.Enumeration;
import ua.hyrax.parse.model.Level;
import ua.hyrax.parse.model.Log;
import ua.hyrax.parse.model.LogClass;

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
        final int MAX_DATE_TIME = 23;
        DateTimeFormatter formatterRM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
        LocalDateTime dateTime = LocalDateTime.now();
        try{
           dateTime = LocalDateTime.parse(log.substring(0,MAX_DATE_TIME),formatterRM);
        }catch(Exception e) {
            e.printStackTrace();
        }
        String[] splitSpace = log.substring(MAX_DATE_TIME,log.length()).split(" ");
        String logLevel = splitSpace[1];
        String logClass = splitSpace[2];

        String[] classes = logClass.split("\\.");
        // calculating offset of msg
        int size = logLevel.length()+logClass.length()+2;
        String msg = log.substring(MAX_DATE_TIME+size,log.length());

        Enumeration.Value logClassValue = LogClass.withName(classes[classes.length-1].substring(0,classes[classes.length-1].length()-1));
        Enumeration.Value logLevelValue = Level.withName(logLevel);
        Long time = dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        return new Log(time, logLevelValue, logClassValue,msg);
    }




}
