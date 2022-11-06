package io.quarkiverse.jberet.it.programmatic;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.BatchStatus;

@Named
@Dependent
public class ProgrammaticBatchlet extends AbstractBatchlet {
    @Override
    public String process() throws Exception {
        return BatchStatus.COMPLETED.toString();
    }
}
