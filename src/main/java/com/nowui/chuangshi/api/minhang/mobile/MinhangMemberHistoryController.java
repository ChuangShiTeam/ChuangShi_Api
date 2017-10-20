package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member/history")
public class MinhangMemberHistoryController extends Controller {

    @ActionKey("/mobile/minhang/member/history/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/history/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/history/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/history/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/history/delete")
    public void delete() {

        renderSuccessJson();
    }

}