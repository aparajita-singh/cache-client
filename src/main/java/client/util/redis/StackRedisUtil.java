package client.util.redis;

import org.springframework.data.redis.core.RedisTemplate;

public class StackRedisUtil<T> extends BaseRedisUtil<T> {
    StackRedisUtil(RedisTemplate<String, T> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public void addToDS(String key, int position, T value) {
        this.redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public void removeFromDS(String key, int position) {
        this.redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public T getFromDS(String key, int position) {
        return (T) this.redisTemplate.opsForList().range(key, 0, 0);
    }

    @Override
    public void updateDSInPlace(String key, int position, T value) {
        updateDS(key, position, value);
    }
}
