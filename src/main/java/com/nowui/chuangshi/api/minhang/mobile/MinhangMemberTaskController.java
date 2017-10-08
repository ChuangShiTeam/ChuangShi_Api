package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member/task")
public class MinhangMemberTaskController extends Controller {

    @ActionKey("/mobile/minhang/member/task/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/task/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/task/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/task/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/task/delete")
    public void delete() {

        renderSuccessJson();
    }

}