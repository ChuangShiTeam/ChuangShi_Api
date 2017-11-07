package com.nowui.chuangshi.api.minhang.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/minhang/member/history")
public class MinhangMemberHistoryController extends Controller {

    @ActionKey("/system/minhang/member/history/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/history/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/history/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/history/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/history/delete")
    public void delete() {

        renderSuccessJson();
    }

}