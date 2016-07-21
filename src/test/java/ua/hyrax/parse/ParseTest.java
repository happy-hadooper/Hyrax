package ua.hyrax.parse;

import org.junit.Test;
import ua.hyrax.parse.model.Log;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by devian on 21.07.16.
 */
public class ParseTest {
    @Test
    public void parseLogRM() throws Exception {
        Log log = Parse.parseLogRM("2016-06-28 06:47:40,214 INFO org.apache.hadoop.yarn.server.resourcemanager.rmapp.attempt.RMAppAttemptImpl: appattempt_1465827807080_55135_000001 State change from FINISHING to FINISHED");
            assertThat(log.logLevel().intern(),is("INFO".intern()));
            assertThat(log.logClass().contains("RMAppAttemptImpl"),is(true));
    }

}