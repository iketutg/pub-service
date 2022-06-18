package id.digitallending.publishers.infrastructure.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class DataRepositoryImpl implements DataRepositoryService {

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public DataRepositoryImpl(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        //redisTemplate.setConnectionFactory(jedisConnectionFactory);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveData(String key, int hashkey, Object value) {
        hashOperations.put(key, hashkey,value);
    }

    @Override
    public void saveDataTtl(String key, int hashkey, Object value, long ttlSecond) {
        hashOperations.put(key, hashkey,value);
        redisTemplate.expire(key,Duration.ofSeconds(ttlSecond));
    }

    @Override
    public Object getData(String key, int hashkey) {
        return hashOperations.get(key,hashkey);
    }

    @Override
    public void publish(String message) {

    }
}
