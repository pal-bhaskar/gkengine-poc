package com.demo.userservice.redis;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.demo.userservice.model.Customer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Value("${redis.host:localhost}")
	private String redisHost;

	@Value("${redis.port:6379}")
	private int redisPort;
	
	private Logger log = LoggerFactory.getLogger(RedisConfig.class);

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(20);
		poolConfig.setMinIdle(2);
		poolConfig.setMaxIdle(5);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setMinEvictableIdleTime(Duration.ofSeconds(60));
		poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(30));
		poolConfig.setNumTestsPerEvictionRun(3);
		poolConfig.setBlockWhenExhausted(true);
		JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
		log.info("*********** redis host: {}", redisHost);
		factory.setHostName(redisHost);
		factory.setUsePool(true);
		factory.setPort(redisPort);
		return factory;

	}

	@Bean
	public RedisTemplate<Long, Customer> redisTemplate() {
		RedisTemplate<Long, Customer> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}
}
