package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/oath")
public class MinhangOathController extends Controller {

    @ActionKey("/system/minhang/oath/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/oath/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/oath/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/oath/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/oath/delete")
    public void delete() {

        renderSuccessJson();
    }

}