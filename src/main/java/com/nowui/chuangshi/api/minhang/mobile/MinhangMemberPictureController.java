package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member/picture")
public class MinhangMemberPictureController extends Controller {

    @ActionKey("/mobile/minhang/member/picture/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/picture/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/picture/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/picture/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/picture/delete")
    public void delete() {

        renderSuccessJson();
    }

}