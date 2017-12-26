package com.wyzssw.distributedLock.Test;

/**
 * Created by wm on 2017/12/26.
 */
public class Test {
    public static void main(String[] args) {

        Service service = new Service();

        for (int i = 0; i < 20; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}
