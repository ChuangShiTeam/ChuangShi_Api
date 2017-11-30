package com.nowui.chuangshi.api.guangqi.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/guangqi/game/area/detail")
public class GuangqiGameAreaDetailController extends Controller {

    @ActionKey("/system/guangqi/game/area/detail/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/game/area/detail/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/game/area/detail/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/game/area/detail/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/game/area/detail/delete")
    public void delete() {

        renderSuccessJson();
    }

}