package ua.hyrax.parse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mchalaev on 25.07.16.
 * This class parses the lines of the class org.apache.hadoop.yarn.server.resourcemanager.ClientRMService
 * For example:
 * "2016-07-07 09:32:09,631 INFO org.apache.hadoop.yarn.server.resourcemanager.ClientRMService:
 *          Application with id 40443 submitted by user user1"
 *
 * and takes such information:
 *      timestamp;
 *      logLevel;
 *      class;
 *      applicationId;
 *      user;
 */
public class ClientRMServiceParser implements Parsable {

    public ApplicationInfo parse(String line) {

        String[] parsing = line.split(" ");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date date = new Date();

        try {
            date = dateFormat.parse(parsing[0] + " " + parsing[1]);
        }catch(Exception e) {
            e.printStackTrace();
        }

        long timestamp = date.getTime();
        String logLevel = parsing[2];
        String[] clazz = parsing[3].split(":");
        long applicationId = Integer.parseInt(parsing[7]);
        String user = parsing[11];

        ApplicationInfo applicationInfo = new ApplicationInfo
                    (user, applicationId, timestamp, clazz[0], logLevel);
        return applicationInfo;
    }
}
