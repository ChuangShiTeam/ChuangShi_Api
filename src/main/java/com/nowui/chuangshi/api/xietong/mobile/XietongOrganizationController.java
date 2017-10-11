package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/organization")
public class XietongOrganizationController extends Controller {

    @ActionKey("/mobile/xietong/organization/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/organization/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/organization/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/organization/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/organization/delete")
    public void delete() {

        renderSuccessJson();
    }

}