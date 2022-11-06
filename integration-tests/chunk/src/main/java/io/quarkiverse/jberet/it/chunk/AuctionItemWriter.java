package io.quarkiverse.jberet.it.chunk;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import jakarta.batch.api.chunk.AbstractItemWriter;

@Dependent
@Named
public class AuctionItemWriter extends AbstractItemWriter {
    @Inject
    AuctionDatabase database;

    @Override
    public void writeItems(List<Object> items) {
        items.stream().map(Auction.class::cast).forEach(database::put);
    }
}
