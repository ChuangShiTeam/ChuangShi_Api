package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/location")
public class MinhangLocationController extends Controller {

    @ActionKey("/mobile/minhang/location/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/location/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/location/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/location/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/location/delete")
    public void delete() {

        renderSuccessJson();
    }

}