package com.nowui.chuangshi.api.minhang.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/minhang/company")
public class MinhangCompanyController extends Controller {

    @ActionKey("/system/minhang/company/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/company/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/company/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/company/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/company/delete")
    public void delete() {

        renderSuccessJson();
    }

}