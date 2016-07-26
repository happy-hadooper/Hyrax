package ua.hyrax.parse;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class ParsingMapTest {
    @Test
    public void getParserTest() {
        String line = "org.apache.hadoop.yarn.server.resourcemanager.ClientRMService";
        ParsingMap parsingMap = new ParsingMap();
        assertNotNull("Parser return NULL value",parsingMap.getParser(line));
    }
}
