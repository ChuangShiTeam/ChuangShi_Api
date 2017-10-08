package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member/question")
public class MinhangMemberQuestionController extends Controller {

    @ActionKey("/mobile/minhang/member/question/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/question/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/question/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/question/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/question/delete")
    public void delete() {

        renderSuccessJson();
    }

}