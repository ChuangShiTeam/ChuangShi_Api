package com.nowui.chuangshi.api.member.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/member")
public class MemberController extends Controller {

    @ActionKey("/system/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}