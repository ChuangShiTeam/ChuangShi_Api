package com.nowui.chuangshi.api.minhang.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/minhang/poster")
public class MinhangPosterController extends Controller {

    @ActionKey("/system/minhang/poster/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/poster/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/poster/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/poster/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/poster/delete")
    public void delete() {

        renderSuccessJson();
    }

}