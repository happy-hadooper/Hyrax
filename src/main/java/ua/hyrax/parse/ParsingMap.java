package ua.hyrax.parse;

import java.util.HashMap;
import java.util.Map;

/**
 * This class should analyze input rows, search log classes in it and provides the relevant parser
 */
public class ParsingMap {

    private String line = null;
    private static Map<String, Parsable> parseMapping = new HashMap<String, Parsable>();

    public ParsingMap() {
    }

    // This method should add to Map all possible log classes and parsers
    private static void addMapValue() {
        ClientRMServiceParser clientRMServiceParser = new ClientRMServiceParser();
        parseMapping.put("org.apache.hadoop.yarn.server.resourcemanager.ClientRMService",
                clientRMServiceParser);
    }

    // This method looks for log classes and return relevant parser
    public Parsable getParser(String line) {
        ParsingMap.addMapValue();
        Parsable parser = parseMapping.get(line);
        return parser;
    }
}
