package com.glinsoft.guardedsuspension;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * 负责连接告警服务器，并发送告警信息至告警服务器
 *
 * @author 高广林
 * @version 1.0
 * @date 2020/1/12 16:51
 * @since JDK1.8
 **/
public class AlarmAgent {

    // 用于记录AlarmAgent是否连接上告警服务器
    private volatile boolean connectedToServer = false;

    // 模拟角色： GuardedSuspension.Predicate
    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    // 模式角色： GuardSuspension.Blocker
    private final Blocker blocker = new ConditionVarBlocker();

    // 心跳定时器
    private final Timer heartbeatTimer = new Timer(true);

    // 省略其他代码


    /**
     * 发送警告信息
     * <p>模拟角色：GuardSuspension.GuardAction</p>
     * <p>可能需要等待，直到AlarmAgent连接上告警服务器</p>
     * @param alarm 告警信息
     * @throws Exception
     */
    public void sendAlarm(final AlarmInfo alarm) throws Exception{
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    private void doSendAlarm(AlarmInfo alarmInfo){
        // 省略其他代码
        System.out.println("sending alarm: "+alarmInfo);
        try {
            Thread.sleep(50);
        }catch (Exception e){}

    }


    public void init(){
        // 省略其他代码

        // 告警连接线程
        Thread connectionThread = new Thread(new ConnectingTask());
        connectionThread.start();
        heartbeatTimer.schedule(new HeartbeatTask(),60000,2000);

    }

    public void disconnect(){
        // 省略其他代码
        System.out.println("disconnected from alarm server.");
        connectedToServer = false;
    }

    protected void onConnected(){
        try{
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    connectedToServer = true;
                    System.out.println("connected to server");
                    return Boolean.TRUE;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    protected void onDisconnected(){
        connectedToServer = false;
    }

    // 负责与告警服务器建立网络连接
    private class ConnectingTask implements Runnable{

        @Override
        public void run() {
            // 省略其他代码

            // 模拟连接操作耗时
            try{
                Thread.sleep(100);
            }catch (Exception e){}

            onConnected();
        }
    }

    private class HeartbeatTask extends TimerTask{

        // 省略其他代码
        @Override
        public void run() {

        }

         private boolean testConnected(){
            return true;
         }

         private void reconnect(){
            ConnectingTask connectingTask = new ConnectingTask();
            connectingTask.run();
         }
    }





}