package ua.hyrax.parse;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mchalaev on 25.07.16.
 */
public class ClientRMServiceParserTest {
    @Test
    public void parseClientRMService() {
        String line = "2016-07-07 09:32:09,631 INFO org.apache.hadoop.yarn.server.resourcemanager.ClientRMService: Application with id 40443 submitted by user user1";
        ClientRMServiceParser clientRMServiceParser = new ClientRMServiceParser();
        ApplicationInfo applicationInfo = clientRMServiceParser.parse(line);

        assertEquals("Error in timestamp parser", applicationInfo.getTimestamp(), 1467873129631L);
        assertEquals("Error in logLevel parser", applicationInfo.getLogLevel(), "INFO");
        assertEquals("Error in class parser", applicationInfo.getClazz(),
                "org.apache.hadoop.yarn.server.resourcemanager.ClientRMService");
        assertEquals("Error in applicationId parser", applicationInfo.getApplicationId(), 40443);
        assertEquals("Error in username parser", applicationInfo.getUser(), "user1");
    }

}
