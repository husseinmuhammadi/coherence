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
        LOGGER.info("Application started!");

        try {
            CacheFactory.ensureCluster();
            NamedCache<String, String> cache = CacheFactory.getCache("accounts");

            List<Thread> threads = new ArrayList<>();
            IntStream.range(0, 100).forEach(i -> {
                threads.add(new Thread(() -> {
                    LOGGER.info("Thread {} started!", Thread.currentThread().getName());
                    IntStream.range(0, 1000).forEach(entry -> cache.put(Thread.currentThread().getName() + String.format("%3d", entry), String.format("%3d", entry)));
                    LOGGER.info("Thread {} stopped!", Thread.currentThread().getName());
                }, String.format("%02d", i)));
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
}

