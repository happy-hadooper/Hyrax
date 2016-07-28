package ua.hyrax.parse;

import org.junit.Test;
import ua.hyrax.parse.model.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by devian on 21.07.16.
 */
public class ParseTest{

    public static final String RM_CONTAINER = "2016-07-07 09:18:18,201 " +
            "INFO " +
            "org.apache.hadoop.yarn.server.resourcemanager.rmcontainer.RMContainerImpl:" +
            " container_1467850995136_39055_01_013533" +
            " Container Transitioned " +
            "from RUNNING" +
            " to COMPLETED";

    @Test
    public void parseLogRMContainer() throws Exception {
        Log logContainer = ua.hyrax.parse.util.Parse.parseLogRM(RM_CONTAINER);
            assertThat(logContainer.logLevel(),is(Level.INFO()));
            assertThat(logContainer.logClass(),is(LogClass.RMContainerImpl()));
            assertThat(logContainer.time(),is(1467883098201L));
            assertThat(logContainer.msg(),is(" container_1467850995136_39055_01_013533 Container Transitioned from RUNNING to COMPLETED"));
    }

   @Test
    public void parseLogRMAppAttempt(){
    Log logAppAttempt = ua.hyrax.parse.util.Parse.parseLogRM("2016-06-28 06:47:40,214 INFO org.apache.hadoop.yarn.server.resourcemanager.rmapp.attempt.RMAppAttemptImpl: appattempt_1465827807080_55135_000001 State change from FINISHING to FINISHED");

        assertThat(logAppAttempt.logLevel(),is(Level.INFO()));
        assertThat(logAppAttempt.logClass(),is(LogClass.RMAppAttemptImpl()));
        assertThat(logAppAttempt.time(),is(1467096460214L));
        assertThat(logAppAttempt.msg(),is(" appattempt_1465827807080_55135_000001 State change from FINISHING to FINISHED"));
    }


    @Test
    public void parseDateTime(){
        LocalDateTime time = ua.hyrax.parse.util.Parse.parseDateTime("2016-06-28 06:47:40,214");
        assertThat(time.toEpochSecond(ZoneOffset.UTC),is(1467096460L));

    }

    @Test
    public void parseState(){
        String logWithoutDate = ua.hyrax.parse.util.Parse.logWithoutDate(RM_CONTAINER);
        String logMsg = ua.hyrax.parse.util.Parse.logWithoutLevelAndClass(logWithoutDate,0);
        State state = ua.hyrax.parse.util.Parse.generateState(1467883098201L,logMsg);
        assertThat(state.time(),is(1467883098201L));
        assertThat(state.containerId(),is("container_1467850995136_39055_01_013533"));
        assertThat(state.from(),is("RUNNING"));
        assertThat(state.to(),is("COMPLETED"));
    }

}