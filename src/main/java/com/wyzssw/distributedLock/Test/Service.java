package com.wyzssw.distributedLock.Test;

import com.wyzssw.distributedLock.DistributedLock;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by wm on 2017/12/26.
 */
public class Service {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "192.168.1.36", 7003, 3000);
    }


    DistributedLock lock = new DistributedLock(pool ,3000, 10);
    int n = 20;

    public void seckill() {
//        // 返回锁的value值，供释放锁时候进行判断
        long indentifier = 0;
        try {
            indentifier = lock.lock("resource");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        System.out.println(--n);
        lock.unlock("resource", indentifier);
    }
}
