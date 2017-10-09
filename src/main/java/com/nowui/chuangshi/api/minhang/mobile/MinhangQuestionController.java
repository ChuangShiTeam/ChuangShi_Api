package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/question")
public class MinhangQuestionController extends Controller {

    @ActionKey("/mobile/minhang/question/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/delete")
    public void delete() {

        renderSuccessJson();
    }

}