package com.nowui.chuangshi.api.minhang.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/minhang/timeline")
public class MinhangTimelineController extends Controller {

    @ActionKey("/system/minhang/timeline/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/timeline/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/timeline/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/timeline/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/timeline/delete")
    public void delete() {

        renderSuccessJson();
    }

}