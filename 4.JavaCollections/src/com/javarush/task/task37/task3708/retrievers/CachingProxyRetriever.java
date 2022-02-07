package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {

    final OriginalRetriever retriever;
    final LRUCache<Long, Object> cache;

    public CachingProxyRetriever(Storage storage) {
        retriever = new OriginalRetriever(storage);
        cache = new LRUCache<>(16);
    }

    @Override
    public Object retrieve(long id) {
        Object object = cache.find(id);

        if (object == null) {
            object = retriever.retrieve(id);
            cache.set(id, object);
        }
        return object;
    }
}
