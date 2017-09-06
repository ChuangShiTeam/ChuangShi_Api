package com.nowui.chuangshi.api.feijiu.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastCreditCard;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastCreditCardService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
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
        String request_app_id = getRequest_app_id();

        Integer resultCount = FeijiuFastCreditCardService.instance.adminCount(request_app_id, model.getCredit_card_name());
        List<FeijiuFastCreditCard> resultList = FeijiuFastCreditCardService.instance.adminList(request_app_id, model.getCredit_card_name(), getM(), getN());

        validateResponse(FeijiuFastCreditCard.CREDIT_CARD_ID, FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/feijiu/fast/credit/card/find")
    public void find() {
        validateRequest(FeijiuFastCreditCard.CREDIT_CARD_ID);

        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);

        FeijiuFastCreditCard result = FeijiuFastCreditCardService.instance.find(model.getCredit_card_id());

        result.put(FeijiuFastCreditCard.CREDIT_CARD_IMAGE_FILE, FileService.instance.getFile(result.getCredit_card_image()));

        validateResponse(FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_IMAGE_FILE, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.CREDIT_CARD_CONTENT, FeijiuFastCreditCard.CREDIT_CARD_SORT, FeijiuFastCreditCard.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/credit/card/save")
    public void save() {
        validateRequest(FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_IMAGE, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.CREDIT_CARD_CONTENT);

        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);
        model.setCredit_card_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = FeijiuFastCreditCardService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/credit/card/update")
    public void update() {
        validateRequest(FeijiuFastCreditCard.CREDIT_CARD_ID, FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_IMAGE, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.CREDIT_CARD_CONTENT, FeijiuFastCreditCard.SYSTEM_VERSION);

        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);
        String request_user_id = getRequest_user_id();

        Boolean result = FeijiuFastCreditCardService.instance.update(model, model.getCredit_card_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/credit/card/delete")
    public void delete() {
        validateRequest(FeijiuFastCreditCard.CREDIT_CARD_ID, FeijiuFastCreditCard.SYSTEM_VERSION);

        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);
        String request_user_id = getRequest_user_id();

        Boolean result = FeijiuFastCreditCardService.instance.delete(model.getCredit_card_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}