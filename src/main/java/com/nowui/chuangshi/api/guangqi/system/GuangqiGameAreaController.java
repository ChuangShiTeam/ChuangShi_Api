package com.nowui.chuangshi.api.guangqi.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/guangqi/game/area")
public class GuangqiGameAreaController extends Controller {

    @ActionKey("/system/guangqi/game/area/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/game/area/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/game/area/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/game/area/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/game/area/delete")
    public void delete() {

        renderSuccessJson();
    }

}