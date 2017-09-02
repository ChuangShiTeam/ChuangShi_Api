package com.nowui.chuangshi.api.feijiu.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastCreditCard;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastCreditCardService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/feijiu/fast/credit/card")
public class FeijiuFastCreditCardController extends Controller {

    @ActionKey("/mobile/feijiu/fast/credit/card/list")
    public void list() {
        String request_app_id = getRequest_app_id();

        List<FeijiuFastCreditCard> resultList = FeijiuFastCreditCardService.instance.appList(request_app_id);
        
        for (FeijiuFastCreditCard result : resultList) {
            result.put(FeijiuFastCreditCard.CREDIT_CARD_IMAGE, FileService.instance.getFile_path(result.getCredit_card_image()));
        }
            
        validateResponse(FeijiuFastCreditCard.CREDIT_CARD_ID, FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_IMAGE, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.CREDIT_CARD_CONTENT);
        renderSuccessJson(resultList);
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