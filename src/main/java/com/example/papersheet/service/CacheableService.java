package com.example.papersheet.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheableService {
    @Cacheable("dataCache")
    public String getCachedData() {
        // Simulate a time-consuming operation
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Cached Data";
    }
}