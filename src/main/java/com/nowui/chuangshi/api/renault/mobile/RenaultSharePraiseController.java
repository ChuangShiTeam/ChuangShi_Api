package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/renault/share/praise")
public class RenaultSharePraiseController extends Controller {

    @ActionKey("/mobile/renault/share/praise/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/praise/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/praise/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/praise/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/praise/delete")
    public void delete() {

        renderSuccessJson();
    }

}