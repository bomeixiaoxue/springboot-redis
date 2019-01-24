package com.example.redis.cluster.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyController {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/getkey")
    public String getValue(String key){
        System.out.println(key + "请求");
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String value = operations.get(key);
        log.info("redis返回结果：{}", value);
        if (value != null && value != ""){
            return value;
        }
        if (value == null){
            return "不存在该key：" + key;
        }
        return key + "的值为空！";
    }

    @RequestMapping(value = "/setkey")
    public String setKey(String key, String value){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        return "添加成功";
    }

    @RequestMapping(value = "/gethkey")
    public String getHValue(String name, String key){
        System.out.println(key + "请求");
        HashOperations hashOperations = redisTemplate.opsForHash();
        Object object = hashOperations.get(name, key);
        log.info("redis返回结果：{}", object);
        if (object != null){
            return object.toString();
        }
        return "不存在该key：" + key;
    }

}
