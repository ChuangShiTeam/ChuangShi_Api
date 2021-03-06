package com.nowui.chuangshi.api.minhang.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/minhang/member/question")
public class MinhangMemberQuestionController extends Controller {

    @ActionKey("/system/minhang/member/question/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/question/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/question/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/question/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/question/delete")
    public void delete() {

        renderSuccessJson();
    }

}