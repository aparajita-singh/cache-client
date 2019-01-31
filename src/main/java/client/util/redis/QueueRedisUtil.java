package client.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueRedisUtil<T> extends BaseRedisUtil<T> {
    @Autowired
    public QueueRedisUtil(RedisTemplate<String, T> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public void addToDS(String key, int position, T value) {
        this.redisTemplate.opsForList().rightPush(key, value);
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
        this.redisTemplate.opsForList().leftPop(key);
        this.redisTemplate.opsForList().leftPush(key, value);
    }
}
