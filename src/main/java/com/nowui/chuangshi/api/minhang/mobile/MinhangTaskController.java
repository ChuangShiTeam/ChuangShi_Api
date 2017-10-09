package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/task")
public class MinhangTaskController extends Controller {

    @ActionKey("/mobile/minhang/task/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/task/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/task/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/task/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/task/delete")
    public void delete() {

        renderSuccessJson();
    }

}