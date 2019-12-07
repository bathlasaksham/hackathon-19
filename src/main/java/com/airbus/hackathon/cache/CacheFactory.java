package com.oyo.restrictions.cache;

import com.oyo.restrictions.cache.jedis.JedisCache;
import com.oyo.restrictions.util.MathUtil;

public class CacheFactory {

	public static Cache getCache(String host, int port, String namespace) {
		if (host.contains("redis") || port == 6379) {
			return new JedisCache(host, port, namespace);
		}
		return null;
	}

	public static Cache getCache() {
		return getCache("127.0.0.1", 6379, null);
	}

	public static Cache getCache(String hostPort, String namespace) {
		String[] hostPortArr = hostPort.split(":");
		String host = hostPortArr[0];
		Integer port = null;
		if (hostPortArr.length > 1) {
			port = MathUtil.parseInt(hostPortArr[1]);
		}
		if (port == null) {
			port = 6379;
		}
		return getCache(host, port, namespace);
	}

	public static Cache getCache(String host, int port) {
		return getCache(host, port, null);
	}

}
