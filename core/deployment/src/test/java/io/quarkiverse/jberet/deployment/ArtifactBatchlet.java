package io.quarkiverse.jberet.deployment;

import com.google.inject.Inject;

import jakarta.batch.api.BatchProperty;
import jakarta.batch.api.Batchlet;
import jakarta.batch.runtime.BatchStatus;

public class ArtifactBatchlet implements Batchlet {
    @Inject
    @BatchProperty(name = "name")
    String name;

    @Override
    public String process() {
        if (!name.equals("david")) {
            throw new RuntimeException("Unexpected value injected to 'name': " + name);
        }
        return BatchStatus.COMPLETED.toString();
    }

    @Override
    public void stop() {
    }
}
