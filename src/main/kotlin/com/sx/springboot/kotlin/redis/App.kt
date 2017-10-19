package com.sx.springboot.kotlin.redis

import com.fasterxml.jackson.databind.ObjectMapper
import com.sx.cache.redis.SxRedis
import com.sx.cache.redis.build.JedisPoolConfigBuilder
import com.sx.cache.redis.build.RedisConfigBuilder
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.view.json.MappingJackson2JsonView
import javax.annotation.PostConstruct

/**
 * Created by Administrator on 2017/10/18 0018.
 */
@SpringBootApplication
class App {
    @Bean
    fun json(): MappingJackson2JsonView {
        return MappingJackson2JsonView(ObjectMapper())
    }

    @PostConstruct
    fun initRedis(){
        var config = RedisConfigBuilder.newBuilder()
                //.with("130.10.9.176", 6000)
                //.with("130.10.9.176", 6001)
                .with("130.10.9.176")
                //.password("rjsjb123")
                .cluster(false)
                .rwseparation(true)
                .poolConfig(JedisPoolConfigBuilder.newBuilder()
                        .maxTotal(9)
                        .maxIdle(9)
                        .minIdle(7)
                        .build()
                ).build()
        SxRedis.init(config)
    }
}

fun main(args: Array<String>) {
    SpringApplicationBuilder(App::class.java).web(true).run(*args)
}