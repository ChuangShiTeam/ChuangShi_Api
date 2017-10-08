package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member")
public class MinhangMemberController extends Controller {

    @ActionKey("/mobile/minhang/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}