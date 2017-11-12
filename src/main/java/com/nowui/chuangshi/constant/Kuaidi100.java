package com.nowui.chuangshi.constant;

import com.jfinal.kit.PropKit;

public class Kuaidi100 {
    
    public static String KUAIDI100_POLL_KEY = "";
    public static String KUAIDI100_POLL_URL = "";
    public static String KUAIDI100_CALLBACK_URL = "";
    public static String KUAIDI100_REAL_TIME_QUERY_KEY = "";
    public static String KUAIDI100_REAL_TIME_QUERY_CUSTOMER = "";
    public static String KUAIDI100_REAL_TIME_QUESY_URL = "";

    static {
        PropKit.clear();
        PropKit.use("Kuaidi100.properties");

        KUAIDI100_POLL_KEY = PropKit.get("kuaidi100_poll_key");
        KUAIDI100_POLL_URL = PropKit.get("kuaidi100_poll_url");
        KUAIDI100_CALLBACK_URL = PropKit.get("kuaidi100_callback_url");
        KUAIDI100_REAL_TIME_QUERY_KEY = PropKit.get("kuaidi100_real_time_query_key");
        KUAIDI100_REAL_TIME_QUERY_CUSTOMER = PropKit.get("kuaidi100_real_time_query_customer");
        KUAIDI100_REAL_TIME_QUESY_URL = PropKit.get("kuaidi100_real_time_quesy_url");
    }

}
