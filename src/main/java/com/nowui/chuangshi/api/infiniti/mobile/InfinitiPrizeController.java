package com.nowui.chuangshi.api.infiniti.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/infiniti/prize")
public class InfinitiPrizeController extends Controller {

    @ActionKey("/mobile/infiniti/prize/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/infiniti/prize/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/infiniti/prize/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/infiniti/prize/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/infiniti/prize/delete")
    public void delete() {

        renderSuccessJson();
    }

}