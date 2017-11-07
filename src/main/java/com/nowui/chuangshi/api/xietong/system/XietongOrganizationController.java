package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/organization")
public class XietongOrganizationController extends Controller {

    @ActionKey("/system/xietong/organization/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/organization/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/organization/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/organization/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/organization/delete")
    public void delete() {

        renderSuccessJson();
    }

}