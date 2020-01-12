package com.glinsoft.immutableobject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 彩信中心路由规则管理器
 *
 * @author 高广林
 * @version 1.0
 * @date 2020/1/12 10:49
 * @since JDK1.8
 **/
public final class MMSCRouter {

    // 使用volatile关键字修饰，保证多线程环境下的此变量的可见性
    private static volatile MMSCRouter instance = new MMSCRouter();

    // 维护手机号码前缀到彩信中心的映射关系
    private final Map<String,MMSCInfo> routeMap;

    public MMSCRouter(){
        // 将数据库表中的数据加载到内存，存为Map
        this.routeMap = retrieveRouterMapFromDB();
    }

    private static Map<String,MMSCInfo> retrieveRouterMapFromDB(){
        Map<String,MMSCInfo> map = new HashMap();
        // 省略其他代码
        return map;
    }

    public static MMSCRouter getInstance(){
        return instance;
    }

    /**
     * 根据手机号前缀获取彩信中心信息
     *
     * @param msisdnPrefix 手机号前缀
     * @return 返回彩信中心信息
     */
    public MMSCInfo getMMSC(String msisdnPrefix){
        return routeMap.get(msisdnPrefix);
    }

    /**
     * 将当前MMSCRouter的实例更新为指定的新实例
     *
     * @param newInstance 新的MMSCRouter实例
     */
    public static void setInstance(MMSCRouter newInstance){
        instance = newInstance;
    }

    private static Map<String,MMSCInfo> deepCopy(Map<String,MMSCInfo> m){
        Map<String,MMSCInfo> result = new HashMap();
        for(String key : m.keySet()){
            result.put(key,new MMSCInfo(m.get(key)));
        }
        return result;
    }


    public Map<String,MMSCInfo> getRouteMap(){
        // 做防御性复制
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }





}