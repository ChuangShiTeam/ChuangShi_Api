package com.nowui.chuangshi.api.minhang.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/minhang/affiant")
public class MinhangAffiantController extends Controller {

    @ActionKey("/system/minhang/affiant/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/affiant/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/affiant/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/affiant/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/affiant/delete")
    public void delete() {

        renderSuccessJson();
    }

}