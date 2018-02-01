package com.nowui.chuangshi.api.minhang.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangCompany;
import com.nowui.chuangshi.api.minhang.service.MinhangCompanyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/minhang/company")
public class MinhangCompanyController extends Controller {

    @ActionKey("/admin/minhang/company/list")
    public void list() {
        validateRequest(MinhangCompany.COMPANY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangCompany model = getModel(MinhangCompany.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangCompanyService.instance.adminCount(request_app_id, model.getCompany_name());
        List<MinhangCompany> resultList = MinhangCompanyService.instance.adminList(request_app_id, model.getCompany_name(), getM(), getN());

        validateResponse(MinhangCompany.COMPANY_ID, MinhangCompany.COMPANY_NAME, MinhangCompany.COMPANY_LOGO, MinhangCompany.COMPANY_VIEW_WIDTH, MinhangCompany.COMPNAY_SORT, MinhangCompany.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/company/find")
    public void find() {
        validateRequest(MinhangCompany.COMPANY_ID);

        MinhangCompany model = getModel(MinhangCompany.class);

        MinhangCompany result = MinhangCompanyService.instance.find(model.getCompany_id());

        validateResponse(MinhangCompany.COMPANY_NAME, MinhangCompany.COMPANY_LOGO, MinhangCompany.COMPANY_VIEW_WIDTH, MinhangCompany.COMPNAY_DESCRIPTION, MinhangCompany.COMPNAY_SORT, MinhangCompany.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/company/save")
    public void save() {
        validateRequest(MinhangCompany.COMPANY_NAME, MinhangCompany.COMPANY_LOGO, MinhangCompany.COMPANY_VIEW_WIDTH, MinhangCompany.COMPNAY_DESCRIPTION, MinhangCompany.COMPNAY_SORT);

        MinhangCompany model = getModel(MinhangCompany.class);
        model.setCompany_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangCompanyService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/company/update")
    public void update() {
        validateRequest(MinhangCompany.COMPANY_ID, MinhangCompany.COMPANY_NAME, MinhangCompany.COMPANY_LOGO, MinhangCompany.COMPANY_VIEW_WIDTH, MinhangCompany.COMPNAY_DESCRIPTION, MinhangCompany.COMPNAY_SORT, MinhangCompany.SYSTEM_VERSION);

        MinhangCompany model = getModel(MinhangCompany.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangCompanyService.instance.update(model, model.getCompany_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/company/delete")
    public void delete() {
        validateRequest(MinhangCompany.COMPANY_ID, MinhangCompany.SYSTEM_VERSION);

        MinhangCompany model = getModel(MinhangCompany.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangCompanyService.instance.delete(model.getCompany_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}