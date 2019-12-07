package com.airbus.hackathon.cache;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseCacheImpl implements Cache {

	protected Cache cache;

	public Cache getCache() {
		return cache;
	}

	public boolean isLocked(String lockKey) {
		return (cache.get(lockKey) != null);
	}

	public boolean checkAndLock(String lockKey, int lockSeconds) {
		if (isLocked(lockKey)) {
			return false;
		} else {
			cache.set(lockKey, "1", lockSeconds);
			return true;
		}
	}

	public boolean checkAndLock(String lockKey) {
		return checkAndLock(lockKey, 120);
	}

	@Override
	public Object getNativeConnection() {
		return cache.getNativeConnection();
	}

	@Override
	public List<String> getList(List<String> keys) {
		return cache.getList(keys);
	}

	@Override
	public List<String> mget(List<String> keys) {
		return cache.mget(keys);
	}

	@Override
	public String get(String key) {
		return cache.get(key);
	}

	@Override
	public long incr(String key) { return cache.incr(key); }

	@Override
	public long delete(String key) {
		return cache.delete(key);
	}

	@Override
	public String set(String key, String value) {
		return cache.set(key, value);
	}

	@Override
	public String set(String key, String value, Integer ttlSeconds) {
		return cache.set(key, value, ttlSeconds);
	}

	@Override
	public void setKeyExpire(String key, Integer ttl) { cache.setKeyExpire(key, ttl); }

}
