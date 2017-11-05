package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/renault/share/comment/praise")
public class RenaultShareCommentPraiseController extends Controller {

    @ActionKey("/mobile/renault/share/comment/praise/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/praise/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/praise/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/praise/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/praise/delete")
    public void delete() {

        renderSuccessJson();
    }

}