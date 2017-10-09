package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/timeline/event")
public class MinhangTimelineEventController extends Controller {

    @ActionKey("/system/minhang/timeline/event/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/timeline/event/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/timeline/event/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/timeline/event/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/timeline/event/delete")
    public void delete() {

        renderSuccessJson();
    }

}