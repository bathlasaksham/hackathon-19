package com.airbus.hackathon.cache.jedis;

import com.airbus.hackathon.cache.Cache;
import com.airbus.hackathon.util.TransformUtil;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

public class JedisCache implements Cache {

	private JedisPool pool;

	private String namespace;

	private JedisPoolConfig poolConfig;

	private Integer poolConfigMaxTotal = 100;

	private Integer poolConfigMaxIdle = 50;

	public JedisCache(String host, Integer port, String namespace) {
		poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(poolConfigMaxTotal);
		poolConfig.setMaxIdle(poolConfigMaxIdle);
		pool = new JedisPool(poolConfig, host, port);
		this.namespace = StringUtils.trimToNull(namespace);
	}

	public Jedis getJedis() {
		return pool.getResource();
	}

	/**
	 * Same as <strong>getJedis()</strong>
	 * @return jedis
	 */
	public Jedis getNativeJedis() {
		return getJedis();
	}

	/**
	 * Same as <strong>getJedis()</strong>
	 * @return jedis
	 */
	@Override
	public Object getNativeConnection() {
		return getJedis();
	}

	public JedisPool getPool() {
		return pool;
	}

	public List<String> getList(List<String> keys) {
		return mget(keys);
	}

	public List<String> mget(List<String> keys) {
		try (Jedis jedis = getJedis()) {
			String[] namespacedKeys = null;
			if (namespace != null && keys != null) {
				List<String> keysList = new ArrayList<String>();
				for (String key : keys) {
					keysList.add(keyWithNamespace(key));
				}
				namespacedKeys = TransformUtil.collectionOfStringsToArrayOfStrings(keysList);
			} else if (keys != null) {
				namespacedKeys = TransformUtil.collectionOfStringsToArrayOfStrings(keys);
			}
			return jedis.mget(namespacedKeys);
		}
	}

	public long incr(String key) {
		try (Jedis jedis = getJedis()) {
			return jedis.incr(keyWithNamespace(key));
		}
	}

	public long delete(String key) {
		try (Jedis jedis = getJedis()) {
			key = keyWithNamespace(key);
			return jedis.del(key);
		}
	}

	public String get(String key) {
		try (Jedis jedis = getJedis()) {
			key = keyWithNamespace(key);
			return jedis.get(key);
		}
	}

	public String set(String key, String value) {
		return set(key, value, null);
	}

	public String set(String key, String value, Integer ttlSeconds) {
		try (Jedis jedis = getJedis()) {
			key = keyWithNamespace(key);
			String res = jedis.set(key, value);
			if (ttlSeconds != null) {
				jedis.expire(key, ttlSeconds.intValue());
			}
			return res;
		}
	}

	private String keyWithNamespace(String key) {
		String keyWithNamespace = key;
		if (namespace != null && key != null) {
			keyWithNamespace = namespace + ":" + key;
		}
		return keyWithNamespace;
	}

	public void setKeyExpire(String key, Integer ttl) {
		try (Jedis jedis = getJedis()) {
			jedis.expire(keyWithNamespace(key), ttl);
		}
	}

}
