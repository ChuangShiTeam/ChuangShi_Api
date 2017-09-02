package com.nowui.chuangshi.api.member.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/member/level")
public class MemberLevelController extends Controller {

    @ActionKey("/admin/member/level/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/level/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/level/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/level/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/level/delete")
    public void delete() {

        renderSuccessJson();
    }

}