package com.javastudio.tutorial.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Application {

    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        sleep(3000);
        LOGGER.info("Application started!");

        final int totalEntries = 100000;
        final int numberOfThreads = 10;
        final int n = totalEntries / numberOfThreads;

        try {
            CacheFactory.ensureCluster();
            NamedCache<String, String> cache = CacheFactory.getCache("accounts");

            List<Thread> threads = new ArrayList<>();
            int randomId = random(1, 9999);
            IntStream.range(0, numberOfThreads).forEach(i -> {
                threads.add(new Thread(() -> {
                    String threadName = Thread.currentThread().getName();
                    LOGGER.info("Thread {} started!", threadName);
                    IntStream.range(0, n).forEach(entry -> {
                        if (entry % (n / 10) == 0)
                            LOGGER.info("Thread {} is in progress... {}%", threadName, entry / (n / 100));
                        cache.put(
                                String.format("%4d-%s-%4d",
                                        randomId,
                                        threadName,
                                        entry
                                ),
                                String.format("%4d", entry)
                        );
                    });
                    LOGGER.info("Thread {} completed!", threadName);
                }, String.format("%03d", i)));
            });

            threads.forEach(Thread::start);
            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });

            LOGGER.info("Cache size: {}", cache.size());

            CacheFactory.shutdown();
            LOGGER.info("Application stopped!");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static void sleep(long millis) {
        try {
            LOGGER.info("Wait for coherence nodes to be ready ...!");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    private static int random(int min, int max) {
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }
}

