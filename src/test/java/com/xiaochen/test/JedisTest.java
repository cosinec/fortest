package com.xiaochen.test;


import com.xiaochen.jedis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
//        jedis = new Jedis("localhost", 6379);
        jedis = JedisConnectionFactory.getJedis();
//        jedis.auth("123456");
        jedis.select(0);
    }

    @Test
    void testString() {
        String res = jedis.set("name", "cyx");
        System.out.println("res = " + res);
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testHash() {
        jedis.hset("user:1", "name", "jack");
        jedis.hset("user:1", "age", "21");

        //获取
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println("map = " + map);
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }

    }
}
