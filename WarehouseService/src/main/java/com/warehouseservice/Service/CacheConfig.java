package com.warehouseservice.Service;
//package com.warehouseservice.Service;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.util.function.SingletonSupplier;

import lombok.extern.slf4j.Slf4j;

////@Configuration
////@EnableRedisRepositories
////public class RedisConfig 
////{
////	@Bean
////	public JedisConnectionFactory connectionFactory()
////	{
////		RedisStandaloneConfiguration configuration= new RedisStandaloneConfiguration();
//		configuration.setHostName("localhost");
//		configuration.setPort(6379);
//		return new JedisConnectionFactory(configuration);
//	}
//	
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate()
//	{
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(connectionFactory());
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setHashKeySerializer(new StringRedisSerializer());
//		template.setHashKeySerializer(new JdkSerializationRedisSerializer());
//		template.setValueSerializer(new JdkSerializationRedisSerializer());
//		template.setEnableTransactionSupport(true);
//		template.afterPropertiesSet();
//		return template;
//
//		
//	}
//	
//}
