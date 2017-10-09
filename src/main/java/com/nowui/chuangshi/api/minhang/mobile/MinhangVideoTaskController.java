package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/video/task")
public class MinhangVideoTaskController extends Controller {

    @ActionKey("/mobile/minhang/video/task/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/task/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/task/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/task/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/task/delete")
    public void delete() {

        renderSuccessJson();
    }

}