package com.oyo.restrictions.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestrictionsCache extends BaseCacheImpl {

	public RestrictionsCache(@Value("${redis.initialization.enabled}") Boolean redisInitializationEnabled, @Value("${redis.restrictions.host}") String host,
			@Value("${redis.restrictions.namespace}") String namespace) {
		if (redisInitializationEnabled) {
			cache = CacheFactory.getCache(host, namespace);
		}
	}

}
