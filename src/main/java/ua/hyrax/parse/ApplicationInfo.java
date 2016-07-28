package ua.hyrax.parse;

/**
 *
 */
public class ApplicationInfo {

    private long timestamp;
    private String logLevel;
    private String clazz;
    private long applicationId;
    private String user;

    // This constructor created for ClientRMServiceParser
    public ApplicationInfo (String user, long applicationId,
                            long timestamp, String clazz, String logLevel) {
        this.timestamp = timestamp;
        this.logLevel = logLevel;
        this.clazz = clazz;
        this.applicationId = applicationId;
        this.user = user;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getClazz() {
        return clazz;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public String getUser() {
        return user;
    }
}