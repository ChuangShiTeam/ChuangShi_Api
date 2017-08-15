package com.nowui.chuangshi.api.jiangling.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.service.JianglingPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/jiangling/prize")
public class JianglingPrizeController extends Controller {

    private final JianglingPrizeService jianglingPrizeService = new JianglingPrizeService();

    @ActionKey("/system/jiangling/prize/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/prize/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/prize/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/prize/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/prize/delete")
    public void delete() {

        renderSuccessJson();
    }

}