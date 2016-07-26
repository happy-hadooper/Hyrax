package ua.hyrax.parse;

/**
 * This interface is common for all parsers
 */
public interface Parsable {

    public ApplicationInfo parse(String line);

}
