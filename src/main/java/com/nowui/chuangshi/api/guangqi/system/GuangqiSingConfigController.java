package com.nowui.chuangshi.api.guangqi.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/guangqi/sing/config")
public class GuangqiSingConfigController extends Controller {

    @ActionKey("/system/guangqi/sing/config/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/sing/config/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/sing/config/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/sing/config/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/sing/config/delete")
    public void delete() {

        renderSuccessJson();
    }

}