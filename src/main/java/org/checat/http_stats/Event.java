package org.checat.http_stats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;


public class Event {
    @JsonProperty
    private final String userid;
    @JsonProperty
    private final String url;
    @JsonProperty
    private final Instant timestamp;

    @JsonCreator
    public Event(@JsonProperty String userid, @JsonProperty String url, @JsonProperty Instant timestamp) {
        this.userid = userid;
        this.url = url;
        this.timestamp = timestamp;
    }

    public String getUserid() {
        return userid;
    }

    public String getUrl() {
        return url;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
