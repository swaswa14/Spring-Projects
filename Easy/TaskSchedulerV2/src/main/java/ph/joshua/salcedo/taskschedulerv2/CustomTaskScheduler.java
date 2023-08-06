package ph.joshua.salcedo.taskschedulerv2;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Duration;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public class CustomTaskScheduler extends ThreadPoolTaskScheduler {
    private final Map<String, ScheduledFuture<?>> scheduledTask = new IdentityHashMap<>();

    public void scheduleAtFixedRate(Runnable task, Duration period, String id) {
        ScheduledFuture<?> future = super.scheduleAtFixedRate(task, period);
        scheduledTask.put(id, future);
    }

    public void cancelScheduledTask(String id) {
        ScheduledFuture<?> future = scheduledTask.get(id);
        if(null != future) {
            future.cancel(true);
        }
    }

}
