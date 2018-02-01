package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/company")
public class MinhangCompanyController extends Controller {

    @ActionKey("/mobile/minhang/company/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/company/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/company/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/company/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/company/delete")
    public void delete() {

        renderSuccessJson();
    }

}