package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/question")
public class MinhangQuestionController extends Controller {

    @ActionKey("/system/minhang/question/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/delete")
    public void delete() {

        renderSuccessJson();
    }

}