package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/question/answer")
public class MinhangQuestionAnswerController extends Controller {

    @ActionKey("/system/minhang/question/answer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/answer/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/answer/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/answer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/question/answer/delete")
    public void delete() {

        renderSuccessJson();
    }

}