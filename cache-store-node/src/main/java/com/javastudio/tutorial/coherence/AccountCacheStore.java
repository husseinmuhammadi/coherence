package com.javastudio.tutorial.coherence;

import com.javastudio.tutorial.coherence.model.Account;
import com.tangosol.net.cache.AbstractCacheStore;
import com.tangosol.net.cache.CacheLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

public class AccountCacheStore extends AbstractCacheStore<Long, Account> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCacheStore.class);

    public AccountCacheStore() {
    }

    @Override
    public Account load(Long key) {
        LOGGER.info("Loading account {} from storage", key);
        return new Account(key);
    }

//    @Override
//    public Map<Long, Account> loadAll(Collection<? extends Long> colKeys) {
//        LOGGER.info("AccountCacheStore#loadAll ...");
//        return null;
//    }

//    @Override
//    public void storeAll(Map<? extends Long, ? extends Account> mapEntries) {
//        LOGGER.info("AccountCacheStore#storeAll ...");
//    }

//    @Override
//    public void eraseAll(Collection<? extends Long> colKeys) {
//        LOGGER.info("AccountCacheStore#eraseAll ...");
//    }

    @Override
    public void store(Long key, Account value) {
        LOGGER.info("AccountCacheStore#store ...");
    }

    @Override
    public void erase(Long key) {
        LOGGER.info("AccountCacheStore#erase ...");
    }
}
