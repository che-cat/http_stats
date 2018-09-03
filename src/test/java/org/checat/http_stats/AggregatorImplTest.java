package org.checat.http_stats;

import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;


public class AggregatorImplTest {

    @Test
    public void testAggregatorEmpty() {
        Aggregator aggregator = new AggregatorImpl();
        assertThat(aggregator.getUrlCount("absentUrl")).isEqualTo(0);
    }

    @Test
    public void testAggregatorNonEmpty() {
        Aggregator aggregator = new AggregatorImpl();
        aggregator.process(new Event("user1", "url1", 0L));
        aggregator.process(new Event("user2", "url1", 0L));
        aggregator.process(new Event("user1", "url2", 0L));
        assertThat(aggregator.getUrlCount("url1")).isEqualTo(2);
        assertThat(aggregator.getUrlCount("url2")).isEqualTo(1);
        assertThat(aggregator.getUrlCount("url3")).isEqualTo(0);
    }
}
