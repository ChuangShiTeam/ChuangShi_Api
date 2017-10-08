package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/member/key")
public class MinhangMemberKeyController extends Controller {

    @ActionKey("/system/minhang/member/key/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/key/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/key/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/key/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/key/delete")
    public void delete() {

        renderSuccessJson();
    }

}