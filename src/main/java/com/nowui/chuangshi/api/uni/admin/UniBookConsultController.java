package com.nowui.chuangshi.api.uni.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.uni.model.UniBookConsult;
import com.nowui.chuangshi.api.uni.service.UniBookConsultService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/uni/book/consult")
public class UniBookConsultController extends Controller {

    @ActionKey("/admin/uni/book/consult/list")
    public void list() {
        validateRequest(UniBookConsult.BOOK_CONSULT_NAME, UniBookConsult.BOOK_CONSULT_MOBILE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        UniBookConsult model = getModel(UniBookConsult.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = UniBookConsultService.instance.adminCount(request_app_id, model.getBook_consult_name(), model.getBook_consult_mobile());
        List<UniBookConsult> resultList = UniBookConsultService.instance.adminList(request_app_id, model.getBook_consult_name(), model.getBook_consult_mobile(), getM(), getN());

        validateResponse(UniBookConsult.BOOK_CONSULT_ID, UniBookConsult.BOOK_CONSULT_NAME, UniBookConsult.BOOK_CONSULT_MOBILE, UniBookConsult.BOOK_CONSULT_JOB, UniBookConsult.BOOK_CONSULT_COMPANY, UniBookConsult.BOOK_CONSULT_ADDRESS, UniBookConsult.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/uni/book/consult/find")
    public void find() {
        validateRequest(UniBookConsult.BOOK_CONSULT_ID);

        UniBookConsult model = getModel(UniBookConsult.class);

        UniBookConsult result = UniBookConsultService.instance.find(model.getBook_consult_id());

        validateResponse(UniBookConsult.BOOK_CONSULT_NAME, UniBookConsult.BOOK_CONSULT_MOBILE, UniBookConsult.BOOK_CONSULT_JOB, UniBookConsult.BOOK_CONSULT_COMPANY, UniBookConsult.BOOK_CONSULT_ADDRESS, UniBookConsult.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/book/consult/save")
    public void save() {
        validateRequest(UniBookConsult.BOOK_CONSULT_NAME, UniBookConsult.BOOK_CONSULT_MOBILE, UniBookConsult.BOOK_CONSULT_JOB, UniBookConsult.BOOK_CONSULT_COMPANY, UniBookConsult.BOOK_CONSULT_ADDRESS);

        UniBookConsult model = getModel(UniBookConsult.class);
        model.setBook_consult_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = UniBookConsultService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/book/consult/update")
    public void update() {
        validateRequest(UniBookConsult.BOOK_CONSULT_ID, UniBookConsult.BOOK_CONSULT_NAME, UniBookConsult.BOOK_CONSULT_MOBILE, UniBookConsult.BOOK_CONSULT_JOB, UniBookConsult.BOOK_CONSULT_COMPANY, UniBookConsult.BOOK_CONSULT_ADDRESS, UniBookConsult.SYSTEM_VERSION);

        UniBookConsult model = getModel(UniBookConsult.class);
        String request_user_id = getRequest_user_id();

        Boolean result = UniBookConsultService.instance.update(model, model.getBook_consult_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/book/consult/delete")
    public void delete() {
        validateRequest(UniBookConsult.BOOK_CONSULT_ID, UniBookConsult.SYSTEM_VERSION);

        UniBookConsult model = getModel(UniBookConsult.class);
        String request_user_id = getRequest_user_id();

        Boolean result = UniBookConsultService.instance.delete(model.getBook_consult_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}