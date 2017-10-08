package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/key")
public class MinhangKeyController extends Controller {

    @ActionKey("/system/minhang/key/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/key/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/key/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/key/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/key/delete")
    public void delete() {

        renderSuccessJson();
    }

}