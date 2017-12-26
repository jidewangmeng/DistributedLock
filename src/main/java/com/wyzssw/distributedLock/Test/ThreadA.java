package com.wyzssw.distributedLock.Test;

/**
 * Created by wm on 2017/12/26.
 */
public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}
