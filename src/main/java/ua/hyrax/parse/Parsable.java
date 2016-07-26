package ua.hyrax.parse;

/**
 * Created by mchalaev on 26.07.16.
 * This interface is common for all parsers
 */
public interface Parsable {

    public ApplicationInfo parse(String line);

}
