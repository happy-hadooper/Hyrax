package ua.hyrax.parse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
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

        //The parser ClientRMServiceParser.class was created for certain classes,
        // so we don't need to extract class names
        Pattern patternTimestamp = Pattern.compile
                ("(20[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}\\ [0-9]{2}\\:[0-9]{2}\\:" +
                        "[0-9]{2}\\,[0-9]{3}) ([A-Z]{4,5}) " +
                        "org.apache.hadoop.yarn.server.resourcemanager.ClientRMService: " +
                        "Application with id ([0-9]{1,9}) submitted by user ([a-z0-9_-]{1,64})");
        Matcher matcher = patternTimestamp.matcher(line);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date date = new Date();

        long timestamp = 0L;
        String logLevel = null;
        String clazz = "org.apache.hadoop.yarn.server.resourcemanager.ClientRMService";
        long applicationId = 0L;
        String user = null;

        if (matcher.find()) {
            try {
                date = dateFormat.parse(matcher.group(1));
            } catch (Exception e) {
                e.printStackTrace();
            }

            timestamp = date.getTime();
            logLevel = matcher.group(2);
            applicationId = Integer.parseInt(matcher.group(3));
            user = matcher.group(4);
        }

        ApplicationInfo applicationInfo = new ApplicationInfo
                (user, applicationId, timestamp, clazz, logLevel);
        return applicationInfo;
    }
}
