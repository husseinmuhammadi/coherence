package com.javastudio.tutorial.coherence;

import com.javastudio.tutorial.coherence.model.Account;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("Application started!");

        try {
            CacheFactory.ensureCluster();
            NamedCache<Long, Account> cache = CacheFactory.getCache("accounts");
            long l = random(1, 9999);
            cache.put(l, new Account(l));
            CacheFactory.shutdown();
            LOGGER.info("Application stopped!");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static long random(int min, int max) {
        long range = max - min + 1;
        return (long) (Math.random() * range) + min;
    }
}

