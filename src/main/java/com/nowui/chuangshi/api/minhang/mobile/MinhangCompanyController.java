package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangCompany;
import com.nowui.chuangshi.api.minhang.service.MinhangCompanyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/company")
public class MinhangCompanyController extends Controller {

    @ActionKey("/mobile/minhang/company/list")
    public void list() {

        String request_app_id = getRequest_app_id();

        List<MinhangCompany> resultList = MinhangCompanyService.instance.mobileList(request_app_id);

        validateResponse(MinhangCompany.COMPANY_ID, MinhangCompany.COMPANY_NAME, MinhangCompany.COMPANY_LOGO, MinhangCompany.COMPANY_VIEW_WIDTH, MinhangCompany.COMPNAY_DESCRIPTION, MinhangCompany.COMPNAY_SORT, MinhangCompany.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/minhang/company/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/company/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/company/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/company/delete")
    public void delete() {

        renderSuccessJson();
    }

}