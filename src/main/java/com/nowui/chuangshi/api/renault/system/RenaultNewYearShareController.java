package com.nowui.chuangshi.api.renault.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/renault/new/year/share")
public class RenaultNewYearShareController extends Controller {

    @ActionKey("/system/renault/new/year/share/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/new/year/share/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/new/year/share/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/new/year/share/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/new/year/share/delete")
    public void delete() {

        renderSuccessJson();
    }

}