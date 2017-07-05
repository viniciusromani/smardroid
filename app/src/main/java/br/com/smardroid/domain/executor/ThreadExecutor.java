package br.com.smardroid.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by viniciusromani on 05/07/17.
 */

public class ThreadExecutor {
    private Scheduler scheduler;
    public ThreadExecutor(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }
    public Scheduler getScheduler() {
        return scheduler;
    }
}
