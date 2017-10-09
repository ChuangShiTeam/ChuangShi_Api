package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/party/song")
public class MinhangPartySongController extends Controller {

    @ActionKey("/mobile/minhang/party/song/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/song/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/song/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/song/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/song/delete")
    public void delete() {

        renderSuccessJson();
    }

}