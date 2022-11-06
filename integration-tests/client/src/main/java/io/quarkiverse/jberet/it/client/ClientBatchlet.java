package io.quarkiverse.jberet.it.client;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.BatchStatus;

@Named
@Dependent
public class ClientBatchlet extends AbstractBatchlet {
    @Override
    public String process() {
        return BatchStatus.COMPLETED.toString();
    }
}
