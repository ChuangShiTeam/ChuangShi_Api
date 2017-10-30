package com.nowui.chuangshi.api.renault.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultCustomer;
import com.nowui.chuangshi.api.renault.service.RenaultCustomerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/renault/customer")
public class RenaultCustomerController extends Controller {

    @ActionKey("/admin/renault/customer/list")
    public void list() {
        validateRequest(RenaultCustomer.CUSTOMER_NAME, RenaultCustomer.CUSTOMER_PHONE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        RenaultCustomer model = getModel(RenaultCustomer.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultCustomerService.instance.adminCount(request_app_id, model.getCustomer_name(), model.getCustomer_phone());
        List<RenaultCustomer> resultList = RenaultCustomerService.instance.adminList(request_app_id, model.getCustomer_name(), model.getCustomer_phone(), getM(), getN());

        validateResponse(RenaultCustomer.CUSTOMER_ID, RenaultCustomer.CUSTOMER_NAME, RenaultCustomer.CUSTOMER_PHONE, RenaultCustomer.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/customer/find")
    public void find() {
        validateRequest(RenaultCustomer.CUSTOMER_ID);

        RenaultCustomer model = getModel(RenaultCustomer.class);

        RenaultCustomer result = RenaultCustomerService.instance.find(model.getCustomer_id());

        validateResponse(RenaultCustomer.CUSTOMER_NAME, RenaultCustomer.CUSTOMER_PHONE, RenaultCustomer.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/customer/save")
    public void save() {
        validateRequest(RenaultCustomer.CUSTOMER_NAME, RenaultCustomer.CUSTOMER_PHONE);

        RenaultCustomer model = getModel(RenaultCustomer.class);
        model.setCustomer_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultCustomerService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/customer/update")
    public void update() {
        validateRequest(RenaultCustomer.CUSTOMER_ID, RenaultCustomer.CUSTOMER_NAME, RenaultCustomer.CUSTOMER_PHONE, RenaultCustomer.SYSTEM_VERSION);

        RenaultCustomer model = getModel(RenaultCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultCustomerService.instance.update(model, model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/customer/delete")
    public void delete() {
        validateRequest(RenaultCustomer.CUSTOMER_ID, RenaultCustomer.SYSTEM_VERSION);

        RenaultCustomer model = getModel(RenaultCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultCustomerService.instance.delete(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}