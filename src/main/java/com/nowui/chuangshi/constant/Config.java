package com.nowui.chuangshi.constant;

import com.jfinal.kit.PropKit;

public class Config {

    public static String private_key = "";
    public static String driver_class = "";
    public static String jdbc_url = "";
    public static String user = "";
    public static String password = "";
    public static Integer initial_size = 0;
    public static Integer min_idle = 0;
    public static Integer max_activee = 0;
    public static String code_generate_url = "";
    public static String table_schema = "";
    
    public static String body = "";
    public static String notify_url = "";

    static {
        PropKit.clear();
        PropKit.use("config.properties");

        private_key = PropKit.get("private_key");
        driver_class = PropKit.get("driver_class");
        jdbc_url = PropKit.get("jdbc_url");
        user = PropKit.get("user");
        password = PropKit.get("password");
        initial_size = Integer.valueOf(PropKit.get("initial_size"));
        min_idle = Integer.valueOf(PropKit.get("min_idle"));
        max_activee = Integer.valueOf(PropKit.get("max_activee"));
        code_generate_url = PropKit.get("code_generate_url");
        table_schema = PropKit.get("table_schema");
        body = PropKit.get("body");
        notify_url = PropKit.get("notify_url");
    }

}
