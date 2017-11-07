package com.nowui.chuangshi.api.uni.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/uni/apply")
public class UniApplyController extends Controller {

    @ActionKey("/system/uni/apply/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/apply/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/apply/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/apply/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/apply/delete")
    public void delete() {

        renderSuccessJson();
    }

}