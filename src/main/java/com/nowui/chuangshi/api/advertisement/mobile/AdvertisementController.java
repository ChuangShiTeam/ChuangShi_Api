package com.nowui.chuangshi.api.advertisement.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/advertisement")
public class AdvertisementController extends Controller {

    @ActionKey("/mobile/advertisement/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/advertisement/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/advertisement/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/advertisement/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/advertisement/delete")
    public void delete() {

        renderSuccessJson();
    }

}