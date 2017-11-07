package com.nowui.chuangshi.api.enchashment.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.enchashment.service.EnchashmentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/enchashment")
public class EnchashmentController extends Controller {

    private final EnchashmentService enchashmentService = new EnchashmentService();

    @ActionKey("/system/enchashment/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/enchashment/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/enchashment/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/enchashment/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/enchashment/delete")
    public void delete() {

        renderSuccessJson();
    }

}