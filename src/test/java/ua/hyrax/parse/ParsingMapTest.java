package ua.hyrax.parse;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mchalaev on 26.07.16.
 */
public class ParsingMapTest {
    @Test
    public void getParserTest() {
        String line = "2016-07-07 09:32:09,631 INFO org.apache.hadoop.yarn.server.resourcemanager.ClientRMService: Application with id 40443 submitted by user user1";
        ParsingMap parsingMap = new ParsingMap(line);
        assertNotNull("Parser return NULL value",parsingMap.getParser());
    }
}
