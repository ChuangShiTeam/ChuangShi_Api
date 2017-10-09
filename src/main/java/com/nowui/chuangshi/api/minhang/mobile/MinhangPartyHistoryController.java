package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/party/history")
public class MinhangPartyHistoryController extends Controller {

    @ActionKey("/mobile/minhang/party/history/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/history/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/history/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/history/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/history/delete")
    public void delete() {

        renderSuccessJson();
    }

}