package com.wondersgroup.thread;


public class ThreadTest {

    public static void main(String[] args) {
//        Account account = new Account("123456", 0);
//
//        Thread drawMoneyThread = new DrawMoneyThread("取钱线程", account, 700);
//        Thread depositeMoneyThread = new DepositeMoneyThread("存钱线程", account, 700);
//
//        drawMoneyThread.start();
//        depositeMoneyThread.start();
    	
    	 for (int i = 0; i < 100; i++) {
             System.out.println(Thread.currentThread().getName() + " " + i);
             if (i == 30) {
//                 Thread myThread1 = new MyThread();     // 创建一个新的线程  myThread1  此线程进入新建状态
//                 Thread myThread2 = new MyThread();     // 创建一个新的线程 myThread2 此线程进入新建状态
//            	 myThread1.start();                     // 调用start()方法使得线程进入就绪状态
//            	 myThread2.start();                     // 调用start()方法使得线程进入就绪状态
            	 Runnable myRunnable = new MyRunnable(); // 创建一个Runnable实现类的对象
                 Thread thread1 = new Thread(myRunnable); // 将myRunnable作为Thread target创建新的线程
                 Thread thread2 = new Thread(myRunnable);
                 thread1.start(); // 调用start()方法使得线程进入就绪状态
                 thread2.start();
             }
         }
    }

}

class MyThread extends Thread {
    
    private int i = 0;

    @Override
    public void run() {
        for (i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class MyRunnable implements Runnable {
    private int i = 0;

    public void run() {
        for (i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class DrawMoneyThread extends Thread {

    private Account account;
    private double amount;

    public DrawMoneyThread(String threadName, Account account, double amount) {
        super(threadName);
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            account.draw(amount, i);
        }
    }
}

class DepositeMoneyThread extends Thread {

    private Account account;
    private double amount;

    public DepositeMoneyThread(String threadName, Account account, double amount) {
        super(threadName);
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            account.deposite(amount, i);
        }
    }
}

class Account {

    private String accountNo;
    private double balance;
    // 标识账户中是否已有存款
    private boolean flag = false;

    public Account() {

    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 存钱
     * 
     * @param depositeAmount
     */
    public synchronized void deposite(double depositeAmount, int i) {

        if (flag) {
            // 账户中已有人存钱进去，此时当前线程需要等待阻塞
            try {
                System.out.println(Thread.currentThread().getName() + " 开始要执行wait操作" + " -- i=" + i);
                wait();
                // 1
                System.out.println(Thread.currentThread().getName() + " 执行了wait操作" + " -- i=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // 开始存钱
            System.out.println(Thread.currentThread().getName() + " 存款:" + depositeAmount + " -- i=" + i);
            setBalance(balance + depositeAmount);
            flag = true;

            // 唤醒其他线程
            notifyAll();

            // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-- 存钱 -- 执行完毕" + " -- i=" + i);
        }
    }

    /**
     * 取钱
     * 
     * @param drawAmount
     */
    public synchronized void draw(double drawAmount, int i) {
        if (!flag) {
            // 账户中还没人存钱进去，此时当前线程需要等待阻塞
            try {
                System.out.println(Thread.currentThread().getName() + " 开始要执行wait操作" + " 执行了wait操作" + " -- i=" + i);
                wait();
                System.out.println(Thread.currentThread().getName() + " 执行了wait操作" + " 执行了wait操作" + " -- i=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // 开始取钱
            System.out.println(Thread.currentThread().getName() + " 取钱：" + drawAmount + " -- i=" + i);
            setBalance(getBalance() - drawAmount);

            flag = false;

            // 唤醒其他线程
            notifyAll();

            System.out.println(Thread.currentThread().getName() + "-- 取钱 -- 执行完毕" + " -- i=" + i); // 3
        }
    }


}
