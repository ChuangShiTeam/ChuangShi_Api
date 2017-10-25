package com.nowui.chuangshi.api.uni.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.uni.model.UniBookConsult;
import com.nowui.chuangshi.api.uni.service.UniBookConsultService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/uni/book/consult")
public class UniBookConsultController extends Controller {

    @ActionKey("/mobile/uni/book/consult/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/book/consult/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/book/consult/save")
    public void save() {
        validateRequest(UniBookConsult.BOOK_CONSULT_NAME, UniBookConsult.BOOK_CONSULT_MOBILE, UniBookConsult.BOOK_CONSULT_JOB, UniBookConsult.BOOK_CONSULT_COMPANY, UniBookConsult.BOOK_CONSULT_ADDRESS);

        UniBookConsult model = getModel(UniBookConsult.class);
        model.setBook_consult_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();
        /*String request_app_id = getRequest_app_id();
        
        UniBookConsult uniBookConsult = UniBookConsultService.instance.mobileFind(request_app_id, model.getBook_consult_mobile());

        if (uniBookConsult != null) {
            throw new RuntimeException("该手机号码已经被使用过了");
        }*/
        Boolean result = UniBookConsultService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/uni/book/consult/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/book/consult/delete")
    public void delete() {

        renderSuccessJson();
    }

}