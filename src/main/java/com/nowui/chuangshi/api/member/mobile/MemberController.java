package com.nowui.chuangshi.api.member.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/member")
public class MemberController extends Controller {

    private final MemberService articleService = new MemberService();

    @ActionKey("/mobile/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}