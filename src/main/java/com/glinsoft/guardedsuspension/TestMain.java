package com.glinsoft.guardedsuspension;

/**
 * @author 高广林
 * @version 1.0
 * @date 2020/1/12 17:22
 * @since JDK1.8
 **/
public class TestMain {

    public static void main(String... args) throws Exception{

        AlarmAgent alarmAgent = new AlarmAgent();
        alarmAgent.init();
        alarmAgent.sendAlarm(new AlarmInfo());
        alarmAgent.disconnect();
        Thread.sleep(3000);
        alarmAgent.sendAlarm(new AlarmInfo());
        Thread.sleep(30000);

    }

}