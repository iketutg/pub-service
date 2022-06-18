package id.digitallending.publishers.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Configuration
public class RedisConfig {

    @Value("${spring.redis.cluster-on:true}")
    private boolean redisCluster ;

    @Value("${spring.redis.host:127.0.0.1:6379}")
    private String redisHost;

    @Value("${spring.redis.port:6379}")
    private int port;
    @Value("${spring.redis.cluster.nodes:127.0.0.1:6370,127.0.0.1:6371,127.0.0.1:6372,127.0.0.1:5370,127.0.0.1:5371,127.0.0.1:5372}")
    private String nodes;
    @Value("${spring.redis.cluster.max-redirects:3}")
    private Integer maxRedirects;
    @Value("${spring.redis.password:}")
    private String password;
    @Value("${spring.redis.database:0}")
    private Integer database;
    @Value("${spring.redis.jedis.pool.max-active:8}")
    private Integer maxActive;
    @Value("${spring.redis.jedis.pool.max-idle:8}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait:-1}")
    private Long maxWait;
    @Value("${spring.redis.jedis.pool.min-idle:0}")
    private Integer minIdle;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setBlockWhenExhausted(false);
        jedisPoolConfig.setMaxWait(Duration.ofMillis(maxWait));
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        return jedisPoolConfig;
    }


    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){

        if (redisCluster) {
            //log.info("Jedis Connection Factory : RedisCluster");
            RedisClusterConfiguration config = new RedisClusterConfiguration();

            String[] sub = nodes.split(",");
            List<RedisNode> nodeList = new ArrayList<>(sub.length);
            String[] tmp;
            for (String s : sub) {
                tmp = s.split(":");
                //Fixme does not consider the case of exception configuration
                nodeList.add(new RedisNode(tmp[0], Integer.valueOf(tmp[1])));
            }
            config.setClusterNodes(nodeList);
            config.setMaxRedirects(maxRedirects);
            config.setPassword(RedisPassword.of(password));
            return new JedisConnectionFactory(config);
        }
        //log.info("Jedis Connection Factory : Redis Single");
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisHost);
        configuration.setPort(port);
        configuration.setPassword(RedisPassword.of(password));
        return new JedisConnectionFactory(configuration);

    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return redisTemplate;
    }

    @Bean
    public RedisTemplate redisTemplateJson(){
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }


}
