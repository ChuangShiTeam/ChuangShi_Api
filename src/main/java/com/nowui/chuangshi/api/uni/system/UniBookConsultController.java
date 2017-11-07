package com.nowui.chuangshi.api.uni.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/uni/book/consult")
public class UniBookConsultController extends Controller {

    @ActionKey("/system/uni/book/consult/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/book/consult/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/book/consult/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/book/consult/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/book/consult/delete")
    public void delete() {

        renderSuccessJson();
    }

}