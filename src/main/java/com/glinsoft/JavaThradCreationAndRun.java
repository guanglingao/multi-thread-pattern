package com.glinsoft;

/**
 * @author 高广林
 * @version 1.0
 * @date 2020/1/11 17:27
 * @since JDK1.8
 **/
public class JavaThradCreationAndRun {


    public static void main(String[] args){

        System.out.println("The main method was executed by Thread:" +
                Thread.currentThread().getName());

        Helper helper = new Helper("Java Thread Anywhere");

        // 创建一个线程
        Thread thread = new Thread(helper);

        // 设置线程名
        thread.setName("A-Worker-Thread");

        // 启动线程
        thread.start();


    }




    static class Helper implements Runnable{

        private final String message;

        public Helper(String message){
            this.message = message;
        }

        private void doSomething(String message){
            System.out.println("The doSomething method was executed by thread:" +
                     Thread.currentThread().getName());
            System.out.println("Do something with " + message);
        }

        @Override
        public void run() {

        }
    }

}