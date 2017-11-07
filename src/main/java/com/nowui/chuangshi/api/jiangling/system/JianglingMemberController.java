package com.nowui.chuangshi.api.jiangling.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.service.JianglingMemberService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/jiangling/member")
public class JianglingMemberController extends Controller {

    private final JianglingMemberService jianglingMemberService = new JianglingMemberService();

    @ActionKey("/system/jiangling/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/member/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/member/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}