package com.nowui.chuangshi.constant;

import com.jfinal.kit.PropKit;

public class Kdniao {

    public static String EBusinessID = "1287320";
    public static String AppKey = "3fa44734-a85f-4725-9d19-2f0a1c6a2f3b";
    public static String ReqURL = "http://testapi.kdniao.cc:8081/api/dist";

    static {
        PropKit.clear();
        PropKit.use("Kdniao.properties");

        EBusinessID = PropKit.get("EBusinessID");
        AppKey = PropKit.get("AppKey");
        ReqURL = PropKit.get("ReqURL");
    }

}