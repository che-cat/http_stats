package org.checat.http_stats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;


public class Event {
    @JsonProperty
    private final String userid;
    @JsonProperty
    private final String url;
    @JsonProperty("timestampMillis")
    private final long timestampMillis;

    @JsonCreator
    public Event(@JsonProperty("userid") String userid,
                 @JsonProperty("url") String url,
                 @JsonProperty("timestampMillis") long timestampMillis) {
        this.userid = userid;
        this.url = url;
        this.timestampMillis = timestampMillis;
    }

    public String getUserid() {
        return userid;
    }

    public String getUrl() {
        return url;
    }

    public long getTimestampMillis() {
        return timestampMillis;
    }
}
