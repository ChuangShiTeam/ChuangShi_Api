package com.nowui.chuangshi.api.jiangling.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/jiangling/pv")
public class JianglingPvController extends Controller {

    @ActionKey("/system/jiangling/pv/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/pv/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/pv/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/pv/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/pv/delete")
    public void delete() {

        renderSuccessJson();
    }

}