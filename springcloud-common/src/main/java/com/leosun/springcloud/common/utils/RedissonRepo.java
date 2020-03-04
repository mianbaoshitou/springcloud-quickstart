package com.leosun.springcloud.common.utils;

import org.redisson.api.*;

import java.util.concurrent.TimeUnit;

public class RedissonRepo {
    private RedissonClient redissonClient;

    public RedissonRepo(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    // -----------------------------------------------------------------------
    public String getString(String key) {
        RBucket<Object> result = this.redissonClient.getBucket(key);
        return null==result.get() ? null : result.get().toString();
    }

    public void setStringIfNotExist(String key, String value) {
        RBucket<Object> result = this.redissonClient.getBucket(key);
        if (!result.isExists()) {
            result.set(value);
        }
    }
    public void setString(String key, String value) {
        redissonClient.getBucket(key).set(value);
    }
    public void updateString(String key, String value) {
        redissonClient.getBucket(key).set(value);
    }
//    public void setString(String key, Object value, int time, TimeUtil t) {
//        RBucket<Object> result = this.redissonClient.getBucket(key);
//        if (!result.isExists()) {
//            result.set(value, time, t);
//        }
//    }
    public String hgetString(String hashName, String key){
        RMap<Object, Object> redissonHashMap = redissonClient.getMap(hashName);
        return redissonHashMap == null ? null: (String)redissonHashMap.get(key);

    }
    public boolean hasString(String key) {
        RBucket<Object> result = this.redissonClient.getBucket(key);
        if (result.isExists()) {
            return true;
        } else {
            return false;
        }
    }

    public long incr(String key, long delta) {
        return this.redissonClient.getAtomicLong(key).addAndGet(delta);
    }
    // -----------------------------------------------------------------------

    public void lock() {
        RCountDownLatch countDown = redissonClient.getCountDownLatch("aa");
        countDown.trySetCount(1);
        try {
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RCountDownLatch latch = redissonClient.getCountDownLatch("countDownLatchName");
        latch.countDown();
        RReadWriteLock rwlock = redissonClient.getReadWriteLock("lockName");
        rwlock.readLock().lock();
        rwlock.writeLock().lock();
        rwlock.readLock().lock(10, TimeUnit.SECONDS);
        rwlock.writeLock().lock(10, TimeUnit.SECONDS);
        try {
            boolean res = rwlock.readLock().tryLock(100, 10, TimeUnit.SECONDS);
            boolean res1 = rwlock.writeLock().tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void hsetString(String loginjwtholder, String loginkey, String jwtValues) {
        redissonClient.getMap(loginjwtholder).put(loginkey,jwtValues);
    }
}
