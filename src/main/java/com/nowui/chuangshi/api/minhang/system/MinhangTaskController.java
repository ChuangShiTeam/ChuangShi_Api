package com.nowui.chuangshi.api.minhang.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/minhang/task")
public class MinhangTaskController extends Controller {

    @ActionKey("/system/minhang/task/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/task/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/task/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/task/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/task/delete")
    public void delete() {

        renderSuccessJson();
    }

}