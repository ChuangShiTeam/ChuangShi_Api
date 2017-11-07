package com.nowui.chuangshi.api.minhang.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/minhang/video")
public class MinhangVideoController extends Controller {

    @ActionKey("/system/minhang/video/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/video/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/video/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/video/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/video/delete")
    public void delete() {

        renderSuccessJson();
    }

}