package com.nowui.chuangshi.api.guangqi.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/guangqi/game/area/detail")
public class GuangqiGameAreaDetailController extends Controller {

    @ActionKey("/mobile/guangqi/game/area/detail/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/game/area/detail/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/game/area/detail/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/game/area/detail/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/game/area/detail/delete")
    public void delete() {

        renderSuccessJson();
    }

}