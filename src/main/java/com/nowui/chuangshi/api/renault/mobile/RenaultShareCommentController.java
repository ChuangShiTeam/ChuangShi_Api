package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/renault/share/comment")
public class RenaultShareCommentController extends Controller {

    @ActionKey("/mobile/renault/share/comment/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/delete")
    public void delete() {

        renderSuccessJson();
    }

}