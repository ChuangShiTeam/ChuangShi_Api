package com.nowui.chuangshi.api.jiangling.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/jiangling/lottery")
public class JianglingLotteryController extends Controller {

    @ActionKey("/system/jiangling/lottery/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/lottery/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/lottery/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/lottery/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/lottery/delete")
    public void delete() {

        renderSuccessJson();
    }

}