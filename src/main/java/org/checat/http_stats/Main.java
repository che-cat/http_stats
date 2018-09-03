package org.checat.http_stats;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Aggregator aggregator = new AggregatorImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream in = System.in;
        String targetUrl;
        if (args.length > 1) {
            try {
                in = new FileInputStream(args[0]);
            } catch (FileNotFoundException e) {
                System.out.format("File %s not found", args[0]);
                return;
            }
            targetUrl = args[1];
        } else if (args.length == 1){
            targetUrl = args[0];
        } else {
            System.out.println("Usage:");
            System.out.println("http_stats [stats.json] target_url");
            System.out.println("If stats.json is omitted it is read from standard input.");
            return;
        }
        MappingIterator<Event> eventIterator;
        try {
            eventIterator = objectMapper
                    .readerFor(Event.class)
                    .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .readValues(in);
        } catch (IOException e) {
            System.out.println("Invalid JSON data.");
            e.printStackTrace();
            return;
        }
        while(eventIterator.hasNext()) {
            Event event = eventIterator.next();
            aggregator.process(event);
        }
        int urlCount = aggregator.getUrlCount(targetUrl);
        System.out.format("Url %s was visited by %d unique users.\n", targetUrl, urlCount);
    }
}
