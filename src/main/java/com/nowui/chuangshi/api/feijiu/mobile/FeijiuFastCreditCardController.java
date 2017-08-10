package com.nowui.chuangshi.api.feijiu.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastCreditCardService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/feijiu/fast/credit/card")
public class FeijiuFastCreditCardController extends Controller {

    private final FeijiuFastCreditCardService feijiuFastCreditCardService = new FeijiuFastCreditCardService();

    @ActionKey("/mobile/feijiu/fast/credit/card/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/credit/card/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/credit/card/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/credit/card/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/credit/card/delete")
    public void delete() {

        renderSuccessJson();
    }

}