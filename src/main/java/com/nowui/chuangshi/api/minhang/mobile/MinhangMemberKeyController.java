package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member/key")
public class MinhangMemberKeyController extends Controller {

    @ActionKey("/mobile/minhang/member/key/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/key/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/key/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/key/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/key/delete")
    public void delete() {

        renderSuccessJson();
    }

}