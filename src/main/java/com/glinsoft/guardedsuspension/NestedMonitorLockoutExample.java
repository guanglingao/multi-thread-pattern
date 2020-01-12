package com.glinsoft.guardedsuspension;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * @author 高广林
 * @version 1.0
 * @date 2020/1/12 18:09
 * @since JDK1.8
 **/
public class NestedMonitorLockoutExample {

    public static void main(String... args){
        final Helper helper = new Helper();
        System.out.println("Before calling guardedMethod");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String result;
                result = helper.xGuardedMethod("test");
                System.out.println(result);
            }
        });
        t.start();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                helper.xStateChanged();
                timer.cancel();
            }
        },50,10);
    }

    private static class Helper{
        private volatile boolean isStateOK = false;
        private final Predicate stateOK = new Predicate() {
            @Override
            public boolean evaluate() {
                return isStateOK;
            }
        };

        private final Blocker blocker = new ConditionVarBlocker();

        public synchronized String xGuardedMethod(final String message){
            GuardedAction<String> ga = new GuardedAction<String>(stateOK) {
                @Override
                public String call() throws Exception {
                    return message+"-> received.";
                }
            };
            String result = null;
            try{
                result = blocker.callWithGuard(ga);
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }


        public synchronized void xStateChanged(){
            try {
                blocker.signalAfter(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        isStateOK = true;
                        System.out.println("state ok.");
                        return true;
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}