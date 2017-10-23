package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/member/itinerary")
public class MinhangMemberItineraryController extends Controller {

    @ActionKey("/system/minhang/member/itinerary/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/itinerary/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/itinerary/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/itinerary/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/member/itinerary/delete")
    public void delete() {

        renderSuccessJson();
    }

}