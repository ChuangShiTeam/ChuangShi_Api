package com.nowui.chuangshi.api.jiangling.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingCustomer;
import com.nowui.chuangshi.api.jiangling.service.JianglingCustomerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/jiangling/customer")
public class JianglingCustomerController extends Controller {

    @ActionKey("/admin/jiangling/customer/list")
    public void list() {
        validateRequest(JianglingCustomer.CUSTOMER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        JianglingCustomer model = getModel(JianglingCustomer.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = JianglingCustomerService.instance.adminCount(request_app_id, model.getCustomer_name());
        List<JianglingCustomer> resultList = JianglingCustomerService.instance.adminList(request_app_id, model.getCustomer_name(), getM(), getN());

        validateResponse(JianglingCustomer.USER_ID, JianglingCustomer.CUSTOMER_NAME, JianglingCustomer.CUSTOMER_MOBILE, JianglingCustomer.CUSTOMER_DISTRIBUTOR, JianglingCustomer.CUSTOMER_CAR, JianglingCustomer.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/customer/find")
    public void find() {
        validateRequest(JianglingCustomer.USER_ID);

        JianglingCustomer model = getModel(JianglingCustomer.class);

        JianglingCustomer result = JianglingCustomerService.instance.find(model.getUser_id());

        validateResponse(JianglingCustomer.CUSTOMER_NAME, JianglingCustomer.CUSTOMER_MOBILE, JianglingCustomer.CUSTOMER_DISTRIBUTOR, JianglingCustomer.CUSTOMER_CAR, JianglingCustomer.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/customer/save")
    public void save() {
        validateRequest(JianglingCustomer.CUSTOMER_NAME, JianglingCustomer.CUSTOMER_MOBILE, JianglingCustomer.CUSTOMER_DISTRIBUTOR, JianglingCustomer.CUSTOMER_CAR);

        JianglingCustomer model = getModel(JianglingCustomer.class);
        model.setUser_id(Util.getRandomUUID());

        Boolean result = JianglingCustomerService.instance.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/customer/update")
    public void update() {
        validateRequest(JianglingCustomer.USER_ID, JianglingCustomer.CUSTOMER_NAME, JianglingCustomer.CUSTOMER_MOBILE, JianglingCustomer.CUSTOMER_DISTRIBUTOR, JianglingCustomer.CUSTOMER_CAR, JianglingCustomer.SYSTEM_VERSION);

        JianglingCustomer model = getModel(JianglingCustomer.class);

        Boolean result = JianglingCustomerService.instance.update(model, model.getUser_id(), model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/customer/delete")
    public void delete() {
        validateRequest(JianglingCustomer.USER_ID, JianglingCustomer.SYSTEM_VERSION);

        JianglingCustomer model = getModel(JianglingCustomer.class);

        Boolean result = JianglingCustomerService.instance.delete(model.getUser_id(), model.getSystem_version());

        renderSuccessJson(result);
    }

}