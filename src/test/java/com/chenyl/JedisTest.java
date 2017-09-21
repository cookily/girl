package com.chenyl;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * 测试
 * Created by cookily on 2017/9/21.
 */
public class JedisTest {
    /**
     * 单实例
     */
    @Test
    public void testJedisSingle() {
        //创建一个jedis的对象
        Jedis jedis = new Jedis("172.2.33.22", 6379);
        //调用jedis对象的方法
        jedis.set("key1", "jedis test");
        String string = jedis.get("key1");
        System.out.println(string);
        //关闭jedis
        jedis.close();
    }

    /**
     * 使用连接池
     */
    @Test
    public void testJedisPool() {
        //创建jedis连接池
        JedisPool jedisPool = new JedisPool("172.2.33.22", 6379);
        //从连接池中获得jedis对象
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        //记得关闭资源，不然连接池不久就爆了
        jedis.close();
        jedisPool.close();
    }

    /**
     * 集群版测试
     */
    @Test
    public void testJedisCluster() {
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("172.2.33.22",7001));
        nodes.add(new HostAndPort("172.2.33.22",7002));
        nodes.add(new HostAndPort("172.2.33.22",7003));
        nodes.add(new HostAndPort("172.2.33.22",7004));
        nodes.add(new HostAndPort("172.2.33.22",7005));
        nodes.add(new HostAndPort("172.2.33.22",7006));

        JedisCluster cluster=new JedisCluster(nodes);
        cluster.set("key1","1000");
        String string = cluster.get("key1");
        System.out.println(string);
        cluster.close();
    }
}
