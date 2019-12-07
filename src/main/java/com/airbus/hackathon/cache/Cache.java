package com.oyo.restrictions.cache;

import java.util.List;

public interface Cache {

	Object getNativeConnection();

	List<String> getList(List<String> keys);

	List<String> mget(List<String> keys);

	String get(String key);

	String set(String key, String value);

	String set(String key, String value, Integer ttlSeconds);

	long delete(String key);

	long incr(String key);

	void setKeyExpire(String key, Integer ttl);

}
