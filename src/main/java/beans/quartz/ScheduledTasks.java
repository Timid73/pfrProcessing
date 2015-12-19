package beans.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Timid on 19.12.2015.
 */
@Component
public class ScheduledTasks {
    @Scheduled(fixedRate = 5000)
    protected void executeInternal() {
        System.out.println("hello");
    }
}
