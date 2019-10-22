package com.hp.lock;

/*
 * 通过改变加锁顺序（线程按照一定的顺序加锁）避免死锁
 */
public class SyncThread1 implements Runnable{

    private Object obj1;
    private Object obj2;
 
    public SyncThread1(Object o1, Object o2){
        this.obj1=o1;
        this.obj2=o2;
    }
    
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (obj1) {
            System.out.println(name + " acquired lock on "+obj1);
            work();
        }
        System.out.println(name + " released lock on "+obj1);
        synchronized(obj2){
            System.out.println("After, "+ name + " acquired lock on "+obj2);
            work();
        }
        System.out.println(name + " released lock on "+obj2);
        System.out.println(name + " finished execution.");
    }
    
    private void work() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
