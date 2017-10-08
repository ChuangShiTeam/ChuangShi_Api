package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/question/option")
public class MinhangQuestionOptionController extends Controller {

    @ActionKey("/system/minhang/question/option/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/option/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/option/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/option/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/option/delete")
    public void delete() {

        renderSuccessJson();
    }

}