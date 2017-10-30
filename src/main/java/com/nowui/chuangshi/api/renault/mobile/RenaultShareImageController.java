package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/renault/share/image")
public class RenaultShareImageController extends Controller {

    @ActionKey("/mobile/renault/share/image/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/image/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/image/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/image/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/image/delete")
    public void delete() {

        renderSuccessJson();
    }

}