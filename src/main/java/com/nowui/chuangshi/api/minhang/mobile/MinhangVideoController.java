package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/video")
public class MinhangVideoController extends Controller {

    @ActionKey("/mobile/minhang/video/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/delete")
    public void delete() {

        renderSuccessJson();
    }

}