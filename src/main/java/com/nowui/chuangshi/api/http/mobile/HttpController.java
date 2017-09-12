package com.nowui.chuangshi.api.http.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/http")
public class HttpController extends Controller {

    @ActionKey("/mobile/http/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/http/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/http/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/http/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/http/delete")
    public void delete() {

        renderSuccessJson();
    }

}