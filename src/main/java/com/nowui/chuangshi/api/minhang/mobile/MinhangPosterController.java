package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/poster")
public class MinhangPosterController extends Controller {

    @ActionKey("/mobile/minhang/poster/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/poster/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/poster/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/poster/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/poster/delete")
    public void delete() {

        renderSuccessJson();
    }

}