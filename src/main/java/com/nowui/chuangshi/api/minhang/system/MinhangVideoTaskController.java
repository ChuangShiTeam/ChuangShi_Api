package com.nowui.chuangshi.api.minhang.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/minhang/video/task")
public class MinhangVideoTaskController extends Controller {

    @ActionKey("/system/minhang/video/task/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/video/task/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/video/task/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/video/task/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/video/task/delete")
    public void delete() {

        renderSuccessJson();
    }

}