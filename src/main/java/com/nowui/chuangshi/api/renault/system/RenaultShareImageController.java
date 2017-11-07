package com.nowui.chuangshi.api.renault.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/renault/share/image")
public class RenaultShareImageController extends Controller {

    @ActionKey("/system/renault/share/image/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/image/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/image/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/image/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/image/delete")
    public void delete() {

        renderSuccessJson();
    }

}