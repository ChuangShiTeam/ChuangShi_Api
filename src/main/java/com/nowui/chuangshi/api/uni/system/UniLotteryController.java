package com.nowui.chuangshi.api.uni.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/uni/lottery")
public class UniLotteryController extends Controller {

    @ActionKey("/system/uni/lottery/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/lottery/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/lottery/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/lottery/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/lottery/delete")
    public void delete() {

        renderSuccessJson();
    }

}