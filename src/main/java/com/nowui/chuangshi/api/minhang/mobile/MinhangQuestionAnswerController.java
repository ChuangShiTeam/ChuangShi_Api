package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/question/answer")
public class MinhangQuestionAnswerController extends Controller {

    @ActionKey("/mobile/minhang/question/answer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/answer/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/answer/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/answer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/answer/delete")
    public void delete() {

        renderSuccessJson();
    }

}