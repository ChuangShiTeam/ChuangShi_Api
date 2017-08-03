package com.nowui.chuangshi.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class AdminInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        invocation.invoke();
    }

}
