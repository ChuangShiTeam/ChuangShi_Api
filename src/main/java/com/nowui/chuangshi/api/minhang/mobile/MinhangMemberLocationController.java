package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member/location")
public class MinhangMemberLocationController extends Controller {

    @ActionKey("/mobile/minhang/member/location/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/location/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/location/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/location/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/location/delete")
    public void delete() {

        renderSuccessJson();
    }

}