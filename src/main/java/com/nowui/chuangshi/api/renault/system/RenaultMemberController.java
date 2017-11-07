package com.nowui.chuangshi.api.renault.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/renault/member")
public class RenaultMemberController extends Controller {

    @ActionKey("/system/renault/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/member/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/member/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}