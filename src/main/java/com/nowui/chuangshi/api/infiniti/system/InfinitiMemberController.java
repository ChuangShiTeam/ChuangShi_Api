package com.nowui.chuangshi.api.infiniti.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/infiniti/member")
public class InfinitiMemberController extends Controller {

    @ActionKey("/system/infiniti/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/infiniti/member/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/infiniti/member/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/infiniti/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/infiniti/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}