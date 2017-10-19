package com.sx.springboot.kotlin.redis.action

import com.sx.cache.redis.SxRedis;
import org.springframework.web.bind.annotation.*

/**
 *
 * Created by Administrator on 2017/10/17 0017.
 */
@RestController
class RedisResource {
    @RequestMapping("/get/{key}",method = arrayOf(RequestMethod.GET))
     fun get(@PathVariable key: String): String{
        return SxRedis.get(key)?:"the type of this key is not string"
    }

    @GetMapping("/set/{key}/{value}")
     fun set(@PathVariable key: String, @PathVariable value: String): String{
        return SxRedis.set(key,value)
    }

    @RequestMapping("/keys/{pattern}")
     fun keys(@PathVariable pattern: String): String{
        return SxRedis.keys(pattern)
                .map { "<a target=\"blank\" href=\"http://130.10.7.123:8080/get/$it\">$it</a>" }
                .joinToString("<br />")
    }

    @GetMapping("/")
    fun hello(): String{
        return "hello world"
    }

}
