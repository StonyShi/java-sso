package com.stony.sso.cache.redis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import redis.clients.jedis.JedisCluster;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * <p>Java-SSO
 * <p>com.stony.sso.cache.redis
 *
 * @author stony
 * @version 下午2:42
 * @since 2017/6/23
 */
public class JedisClusterTemplate implements JedisTemplate,InitializingBean, Closeable {

    JedisCluster jedisCluster;

    RedisSerializer<Object> valueSerializer = new JdkSerializationRedisSerializer();
    RedisSerializer<String> keySerializer = new StringRedisSerializer();

    @Override
    public String setStr(String key, String value, Integer seconds) {
        if(seconds != null && seconds > 0) {
            return jedisCluster.setex(key, seconds, value);
        }
        return jedisCluster.set(key, value);
    }

    @Override
    public String set(String key, Object value) {
        return jedisCluster.set(keySerializer.serialize(key), valueSerializer.serialize(value));
    }

    @Override
    public String set(String key, Object value, Integer seconds) {
        if(seconds != null && seconds > 0) {
            return jedisCluster.setex(keySerializer.serialize(key), seconds, valueSerializer.serialize(value));
        }
        return jedisCluster.set(keySerializer.serialize(key), valueSerializer.serialize(value));
    }

    @Override
    public Object get(String key) {
        return valueSerializer.deserialize(jedisCluster.get(keySerializer.serialize(key)));
    }

    @Override
    public String getStr(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public Long sadd(String key, Integer seconds, String... members) {
        try {
            return jedisCluster.sadd(key, members);
        }finally {
            expire(key, seconds);
        }
    }

    @Override
    public Set<String> smembers(String key) {
        return jedisCluster.smembers(key);
    }

    @Override
    public Long sadd(String key, String member) {
        return jedisCluster.sadd(key, member);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public Long del(String... keys) {
        Long re = 0L;
        for(String key : keys){
            re += del(key);
        }
        return re;
    }

    @Override
    public Long del(Collection<String> keys) {
        Long re = 0L;
        for(String key : keys){
            re += del(key);
        }
        return re;
    }

    @Override
    public long expire(String key) {
        return jedisCluster.expire(key, 0);
    }

    @Override
    public long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public boolean exists(String key) {
        return jedisCluster.exists(key);
    }



    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    @Override
    public void close() throws IOException {
        if(jedisCluster != null) {
            jedisCluster.close();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(jedisCluster, "jedisCluster must not null.");
    }
}
