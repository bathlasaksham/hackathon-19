package com.airbus.hackathon.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HackathonCache extends BaseCacheImpl {

	public HackathonCache(@Value("${redis.initialization.enabled}") Boolean redisInitializationEnabled, @Value("${redis.restrictions.host}") String host,
						  @Value("${redis.restrictions.namespace}") String namespace) {
		if (redisInitializationEnabled) {
			cache = CacheFactory.getCache(host, namespace);
		}
	}

}
