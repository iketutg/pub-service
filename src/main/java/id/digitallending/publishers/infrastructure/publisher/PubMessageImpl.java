package id.digitallending.publishers.infrastructure.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PubMessageImpl implements PubMessage{

    @Autowired
    @Qualifier("redisTemplateJson")
    RedisTemplate redisTemplate;
    @Override
    public void SendMessage(String topic, Object message) {
        log.info("Send Message Publisher Topic={} , Message={}",topic,message);
        redisTemplate.convertAndSend(topic,message);
    }
}
