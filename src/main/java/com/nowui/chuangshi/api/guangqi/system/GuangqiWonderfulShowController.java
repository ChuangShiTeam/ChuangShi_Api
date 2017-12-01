package com.nowui.chuangshi.api.guangqi.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/guangqi/wonderful/show")
public class GuangqiWonderfulShowController extends Controller {

    @ActionKey("/system/guangqi/wonderful/show/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/wonderful/show/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/wonderful/show/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/wonderful/show/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/wonderful/show/delete")
    public void delete() {

        renderSuccessJson();
    }

}