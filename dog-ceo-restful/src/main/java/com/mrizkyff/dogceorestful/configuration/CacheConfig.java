package com.mrizkyff.dogceorestful.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;


import java.time.Duration;

@Configuration
//@EnableCaching
public class CacheConfig implements CachingConfigurer {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        ObjectMapper myMapper = objectMapper.copy()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(
                        new Hibernate6Module()
                                .enable(Hibernate6Module.Feature.FORCE_LAZY_LOADING)
                                .enable(Hibernate6Module.Feature.REPLACE_PERSISTENT_COLLECTIONS)
                                .enable(Hibernate6Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS)
                                .enable(Hibernate6Module.Feature.USE_TRANSIENT_ANNOTATION)
                                .enable(Hibernate6Module.Feature.FORCE_LAZY_LOADING)
                )
                .activateDefaultTyping(
                        objectMapper.getPolymorphicTypeValidator(),
                        ObjectMapper.DefaultTyping.EVERYTHING,
                        com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY
                );
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(5))
                        .disableCachingNullValues()
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(
                                        new GenericJackson2JsonRedisSerializer(myMapper)
                                )
                        )
                ).build();
    }
}