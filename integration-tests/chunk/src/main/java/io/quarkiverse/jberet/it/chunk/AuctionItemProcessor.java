package io.quarkiverse.jberet.it.chunk;

import java.time.LocalDate;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import jakarta.batch.api.chunk.ItemProcessor;

@Dependent
@Named
public class AuctionItemProcessor implements ItemProcessor {
    @Override
    public Object processItem(Object item) {
        Auction auction = (Auction) item;

        auction.setProcessedAt(LocalDate.now());

        return auction;
    }
}
