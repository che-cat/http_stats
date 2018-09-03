package org.checat.http_stats;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public interface Aggregator {
    default void process(String eventJson) throws IOException {
        process(new ObjectMapper().readValue(eventJson, Event.class));
    }
    void process(Event event);
    int getUrlCount(String url);
}
