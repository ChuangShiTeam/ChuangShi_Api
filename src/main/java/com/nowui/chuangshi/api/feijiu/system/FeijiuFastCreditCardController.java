package com.nowui.chuangshi.api.feijiu.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastCreditCardService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/feijiu/fast/credit/card")
public class FeijiuFastCreditCardController extends Controller {

    private final FeijiuFastCreditCardService feijiuFastCreditCardService = new FeijiuFastCreditCardService();

    @ActionKey("/system/feijiu/fast/credit/card/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/credit/card/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/credit/card/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/credit/card/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/credit/card/delete")
    public void delete() {

        renderSuccessJson();
    }

}