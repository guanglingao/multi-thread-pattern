package com.glinsoft.immutableobject;

/**
 * 与运维中心对接的类
 *
 * @author 高广林
 * @version 1.0
 * @date 2020/1/12 11:21
 * @since JDK1.8
 **/
public class OMCAgent extends Thread {

    @Override
    public void run(){

        boolean isTableModificationMsg = false;
        String updateTableName = null;

        while(true){
            // 省略其他代码
            /**
             * 从与OMC连接的Socket中读取消息并进行解析，
             * 解析到数据表更新消息后，重置MMSCRouter实例。
             */
            if(isTableModificationMsg){
                if("MMSCInfo".equals(updateTableName)){
                    MMSCRouter.setInstance(new MMSCRouter());
                }
            }
            // 省略其他代码
        }

    }


}