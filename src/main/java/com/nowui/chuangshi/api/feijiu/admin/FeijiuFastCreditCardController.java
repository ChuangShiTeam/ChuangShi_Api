package com.nowui.chuangshi.api.feijiu.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastCreditCard;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastCreditCardService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/feijiu/fast/credit/card")
public class FeijiuFastCreditCardController extends Controller {

    @ActionKey("/admin/feijiu/fast/credit/card/list")
    public void list() {
        validateRequest(FeijiuFastCreditCard.CREDIT_CARD_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);
        Cnd cnd = Cnd.where(FeijiuFastCreditCard.APP_ID, model.getApp_id()).andAllowEmpty(FeijiuFastCreditCard.CREDIT_CARD_NAME, model.getCredit_card_name());

        Integer resultCount = FeijiuFastCreditCardService.me.count(cnd);
        List<FeijiuFastCreditCard> resultList = FeijiuFastCreditCardService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(FeijiuFastCreditCard.CREDIT_CARD_ID, FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/feijiu/fast/credit/card/find")
    public void find() {
        validateRequest(FeijiuFastCreditCard.CREDIT_CARD_ID);

        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);

        FeijiuFastCreditCard result = FeijiuFastCreditCardService.me.findById(model.getCredit_card_id());

        validateResponse(FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_IMAGE, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.CREDIT_CARD_CONTENT, FeijiuFastCreditCard.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/credit/card/save")
    public void save() {
        validateRequest(FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_IMAGE, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.CREDIT_CARD_CONTENT);

        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);
        model.setCredit_card_id(Util.getRandomUUID());

        Boolean result = FeijiuFastCreditCardService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/credit/card/update")
    public void update() {
        validateRequest(FeijiuFastCreditCard.CREDIT_CARD_ID, FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_IMAGE, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.CREDIT_CARD_CONTENT, FeijiuFastCreditCard.SYSTEM_VERSION);

        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);

        Boolean result = FeijiuFastCreditCardService.me.update(model, Cnd.where(model.CREDIT_CARD_ID, model.getCredit_card_id()).and(FeijiuFastCreditCard.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/credit/card/delete")
    public void delete() {
        validateRequest(FeijiuFastCreditCard.CREDIT_CARD_ID, FeijiuFastCreditCard.SYSTEM_VERSION);

        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);

        Boolean result = FeijiuFastCreditCardService.me.delete(model, Cnd.where(model.CREDIT_CARD_ID, model.getCredit_card_id()).and(FeijiuFastCreditCard.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}