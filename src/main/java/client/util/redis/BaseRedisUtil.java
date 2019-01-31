package client.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class BaseRedisUtil<T> {
    protected RedisTemplate<String, T> redisTemplate;

    BaseRedisUtil(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public abstract void addToDS(String key, int position, T value);

    public abstract void removeFromDS(String key, int position);

    public abstract T getFromDS(String key, int position);

    public void updateDS(String key, int position, T value) {
        removeFromDS(key, position);
        addToDS(key, position, value);
    }

    public abstract void updateDSInPlace(String key, int position, T value);
}
