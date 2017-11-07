package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/clazz")
public class XietongClazzController extends Controller {

    @ActionKey("/system/xietong/clazz/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/clazz/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/clazz/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/clazz/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/clazz/delete")
    public void delete() {

        renderSuccessJson();
    }

}