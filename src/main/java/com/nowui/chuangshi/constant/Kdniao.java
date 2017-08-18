package com.nowui.chuangshi.constant;

import com.jfinal.kit.PropKit;

public class Kdniao {

    public static String EBusinessID = "";
    public static String AppKey = "";
    public static String ReqURL = "";
    public static String PullReqURL = "";

    static {
        PropKit.clear();
        PropKit.use("Kdniao.properties");

        EBusinessID = PropKit.get("EBusinessID");
        AppKey = PropKit.get("AppKey");
        ReqURL = PropKit.get("ReqURL");
        PullReqURL = PropKit.get("PullReqURL");
    }

}