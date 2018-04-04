package com.nowui.chuangshi.api.jiangling.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingNewCustomer;
import com.nowui.chuangshi.api.jiangling.service.JianglingNewCustomerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/jiangling/new/customer")
public class JianglingNewCustomerController extends Controller {

    @ActionKey("/admin/jiangling/new/customer/list")
    public void list() {
        validateRequest(JianglingNewCustomer.CUSTOMER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        JianglingNewCustomer model = getModel(JianglingNewCustomer.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = JianglingNewCustomerService.instance.adminCount(request_app_id, model.getCustomer_name());
        List<JianglingNewCustomer> resultList = JianglingNewCustomerService.instance.adminList(request_app_id, model.getCustomer_name(), getM(), getN());

        validateResponse(JianglingNewCustomer.USER_ID, JianglingNewCustomer.CUSTOMER_NAME, JianglingNewCustomer.CUSTOMER_MOBILE, JianglingNewCustomer.CUSTOMER_DISTRIBUTOR, JianglingNewCustomer.CUSTOMER_CAR, JianglingNewCustomer.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/customer/find")
    public void find() {
        validateRequest(JianglingNewCustomer.USER_ID);

        JianglingNewCustomer model = getModel(JianglingNewCustomer.class);

        JianglingNewCustomer result = JianglingNewCustomerService.instance.find(model.getUser_id());

        validateResponse(JianglingNewCustomer.CUSTOMER_NAME, JianglingNewCustomer.CUSTOMER_MOBILE, JianglingNewCustomer.CUSTOMER_DISTRIBUTOR, JianglingNewCustomer.CUSTOMER_CAR, JianglingNewCustomer.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/customer/save")
    public void save() {
        validateRequest(JianglingNewCustomer.CUSTOMER_NAME, JianglingNewCustomer.CUSTOMER_MOBILE, JianglingNewCustomer.CUSTOMER_DISTRIBUTOR, JianglingNewCustomer.CUSTOMER_CAR);

        JianglingNewCustomer model = getModel(JianglingNewCustomer.class);
        model.setUser_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingNewCustomerService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/customer/update")
    public void update() {
        validateRequest(JianglingNewCustomer.USER_ID, JianglingNewCustomer.CUSTOMER_NAME, JianglingNewCustomer.CUSTOMER_MOBILE, JianglingNewCustomer.CUSTOMER_DISTRIBUTOR, JianglingNewCustomer.CUSTOMER_CAR, JianglingNewCustomer.SYSTEM_VERSION);

        JianglingNewCustomer model = getModel(JianglingNewCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingNewCustomerService.instance.update(model, model.getUser_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/customer/delete")
    public void delete() {
        validateRequest(JianglingNewCustomer.USER_ID, JianglingNewCustomer.SYSTEM_VERSION);

        JianglingNewCustomer model = getModel(JianglingNewCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingNewCustomerService.instance.delete(model.getUser_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}