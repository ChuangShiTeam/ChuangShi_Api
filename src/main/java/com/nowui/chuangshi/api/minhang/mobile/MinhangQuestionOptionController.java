package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/question/option")
public class MinhangQuestionOptionController extends Controller {

    @ActionKey("/mobile/minhang/question/option/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/option/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/option/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/option/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/question/option/delete")
    public void delete() {

        renderSuccessJson();
    }

}