package io.quarkiverse.jberet.it.scheduler;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.batch.api.Batchlet;

@Dependent
@Named
public class SchedulerBatchlet implements Batchlet {
    @Inject
    @ConfigProperty(name = "status", defaultValue = "FAILED")
    String status;

    @Override
    public String process() {
        return status;
    }

    @Override
    public void stop() {

    }
}
