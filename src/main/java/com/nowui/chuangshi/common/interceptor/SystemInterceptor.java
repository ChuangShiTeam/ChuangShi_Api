package com.nowui.chuangshi.common.interceptor;

import com.jfinal.aop.Invocation;

public class SystemInterceptor {

    public void intercept(Invocation invocation) {
        invocation.invoke();
    }

}
