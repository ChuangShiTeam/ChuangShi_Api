package com.nowui.chuangshi.api.renault.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/renault/new/year")
public class RenaultNewYearController extends Controller {

    @ActionKey("/system/renault/new/year/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/new/year/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/new/year/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/new/year/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/new/year/delete")
    public void delete() {

        renderSuccessJson();
    }

}