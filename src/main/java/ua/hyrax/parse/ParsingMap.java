package ua.hyrax.parse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mchalaev on 26.07.16.
 * This class should analyze input rows, search log classes in it and provides the relevant parser
 */
public class ParsingMap {

    private String line = null;
    private static Map<String, Parsable> parseMapping = new HashMap<String, Parsable>();

    public ParsingMap(String line) {
        this.line = line;
    }

    // This method should add to Map all possible log classes and parsers
    private static void addMapValue() {
        ClientRMServiceParser clientRMServiceParser = new ClientRMServiceParser();
        parseMapping.put("org.apache.hadoop.yarn.server.resourcemanager.ClientRMService",
                clientRMServiceParser);
    }

    // This method looks for log classes in the row and return relevant parser
    public Parsable getParser() {
        ParsingMap.addMapValue();
        String[] lineOut = line.split(" ");
        String[] clazz =lineOut[3].split(":");
        Parsable parser = parseMapping.get(clazz[0]);
        return parser;
    }
}
