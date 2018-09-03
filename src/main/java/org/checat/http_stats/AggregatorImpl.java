package org.checat.http_stats;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AggregatorImpl implements Aggregator {
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Map<String, Set<String>> stats = new HashMap<>();

    @Override
    public void process(Event event) {
        lock.writeLock().lock();
        try {
            stats.computeIfAbsent(event.getUrl(), unused -> new HashSet<>()).add(event.getUserid());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public int getUrlCount(String url) {
        lock.readLock().lock();
        try {
            Set<String> users = stats.get(url);
            if (users != null) {
                return users.size();
            } else {
                return 0;
            }
        } finally {
            lock.readLock().unlock();
        }
    }
}
