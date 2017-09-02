package com.nowui.chuangshi.api.member.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/member")
public class MemberController extends Controller {

    @ActionKey("/admin/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}