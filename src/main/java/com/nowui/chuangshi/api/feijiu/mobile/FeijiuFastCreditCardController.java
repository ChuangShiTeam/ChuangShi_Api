package com.nowui.chuangshi.api.feijiu.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastCreditCard;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastCreditCardService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/feijiu/fast/credit/card")
public class FeijiuFastCreditCardController extends Controller {

    @ActionKey("/mobile/feijiu/fast/credit/card/list")
    public void list() {
        FeijiuFastCreditCard model = getModel(FeijiuFastCreditCard.class);
        Cnd cnd = Cnd.where(FeijiuFastCreditCard.APP_ID, model.getApp_id());

        List<FeijiuFastCreditCard> resultList = FeijiuFastCreditCardService.me.list(cnd);
        
        for (FeijiuFastCreditCard result : resultList) {
            result.put(FeijiuFastCreditCard.CREDIT_CARD_IMAGE, FileService.me.getFile_path(result.getCredit_card_image()));
        }
            
        validateResponse(FeijiuFastCreditCard.CREDIT_CARD_ID, FeijiuFastCreditCard.CREDIT_CARD_NAME, FeijiuFastCreditCard.CREDIT_CARD_IMAGE_FILE, FeijiuFastCreditCard.CREDIT_CARD_LINK, FeijiuFastCreditCard.CREDIT_CARD_CONTENT);
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