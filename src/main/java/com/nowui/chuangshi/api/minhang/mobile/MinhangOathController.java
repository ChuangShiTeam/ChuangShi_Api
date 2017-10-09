package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/oath")
public class MinhangOathController extends Controller {

    @ActionKey("/mobile/minhang/oath/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/oath/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/oath/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/oath/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/oath/delete")
    public void delete() {

        renderSuccessJson();
    }

}