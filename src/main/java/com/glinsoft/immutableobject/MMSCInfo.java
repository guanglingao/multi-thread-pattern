package com.glinsoft.immutableobject;

/**
 * 彩信中心信息
 *
 * @author 高广林
 * @version 1.0
 * @date 2020/1/12 10:53
 * @since JDK1.8
 **/
public class MMSCInfo {

    /** 设备编号 **/
    private final String deviceID;

    /** 彩信中心URL **/
    private final String url;

    /** 此彩信允许的zui'dai **/
    private final int maxAttachmentSizeInBytes;

    public MMSCInfo(String deviceID,String url, int maxAttachmentSizeInBytes){
        this.deviceID = deviceID;
        this.url = url;
        this.maxAttachmentSizeInBytes = maxAttachmentSizeInBytes;
    }

    public MMSCInfo(MMSCInfo prototype){
        this.deviceID = prototype.deviceID;
        this.url = prototype.url;
        this.maxAttachmentSizeInBytes = prototype.maxAttachmentSizeInBytes;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getUrl() {
        return url;
    }

    public int getMaxAttachmentSizeInBytes() {
        return maxAttachmentSizeInBytes;
    }


}