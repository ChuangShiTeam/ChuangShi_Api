package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/key")
public class MinhangKeyController extends Controller {

    @ActionKey("/mobile/minhang/key/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/key/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/key/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/key/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/key/delete")
    public void delete() {

        renderSuccessJson();
    }

}