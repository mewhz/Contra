package test;

import javax.swing.*;

public class MyThread extends Thread {

    private final Object lock = new Object();

    private boolean pause = false;
    private Bullet bulletclass = new Bullet();
    private JPanel bullet = bulletclass.getBullet();
    private int i = 0;

    /**
      调用该方法实现线程的暂停
     */
    void pauseThread(){
        pause = true;
    }


    /**
    调用该方法实现恢复线程的运行
     */
    void resumeThread(){
        pause =false;
        synchronized (lock){
            lock.notify();
        }
    }

    /**
     * 这个方法只能在run 方法中实现，不然会阻塞主线程，导致页面无响应
     */
    void onPause() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        super.run();
        int index = 0;
        while(true){
            while (pause){
                onPause();
            }
            try {
                System.out.println(pause);
                    if(i!=500){
                        i++;
                        bullet.setBounds(i,166,500,500);          //i是x轴
                        System.out.println(i);
                        Thread.sleep(50);
                    }
                if(i==500){
                    onPause();
                }
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }
}

