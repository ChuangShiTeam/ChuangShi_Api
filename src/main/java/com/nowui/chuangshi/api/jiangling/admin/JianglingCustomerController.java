package com.nowui.chuangshi.api.jiangling.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingCustomer;
import com.nowui.chuangshi.api.jiangling.service.JianglingCustomerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
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
        Cnd cnd = Cnd.where(JianglingCustomer.APP_ID, model.getApp_id()).andAllowEmpty(JianglingCustomer.CUSTOMER_NAME, model.getCustomer_name());

        Integer resultCount = JianglingCustomerService.me.count(cnd);
        List<JianglingCustomer> resultList = JianglingCustomerService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(JianglingCustomer.USER_ID, JianglingCustomer.CUSTOMER_NAME, JianglingCustomer.CUSTOMER_MOBILE, JianglingCustomer.CUSTOMER_DISTRIBUTOR, JianglingCustomer.CUSTOMER_CAR, JianglingCustomer.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/customer/find")
    public void find() {
        validateRequest(JianglingCustomer.USER_ID);

        JianglingCustomer model = getModel(JianglingCustomer.class);

        JianglingCustomer result = JianglingCustomerService.me.findById(model.getUser_id());

        validateResponse(JianglingCustomer.CUSTOMER_NAME, JianglingCustomer.CUSTOMER_MOBILE, JianglingCustomer.CUSTOMER_DISTRIBUTOR, JianglingCustomer.CUSTOMER_CAR, JianglingCustomer.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/customer/save")
    public void save() {
        validateRequest(JianglingCustomer.CUSTOMER_NAME, JianglingCustomer.CUSTOMER_MOBILE, JianglingCustomer.CUSTOMER_DISTRIBUTOR, JianglingCustomer.CUSTOMER_CAR);

        JianglingCustomer model = getModel(JianglingCustomer.class);
        model.setUser_id(Util.getRandomUUID());

        Boolean result = JianglingCustomerService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/customer/update")
    public void update() {
        validateRequest(JianglingCustomer.USER_ID, JianglingCustomer.CUSTOMER_NAME, JianglingCustomer.CUSTOMER_MOBILE, JianglingCustomer.CUSTOMER_DISTRIBUTOR, JianglingCustomer.CUSTOMER_CAR, JianglingCustomer.SYSTEM_VERSION);

        JianglingCustomer model = getModel(JianglingCustomer.class);

        Boolean result = JianglingCustomerService.me.update(model, Cnd.where(model.USER_ID, model.getUser_id()).and(JianglingCustomer.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/customer/delete")
    public void delete() {
        validateRequest(JianglingCustomer.USER_ID, JianglingCustomer.SYSTEM_VERSION);

        JianglingCustomer model = getModel(JianglingCustomer.class);

        Boolean result = JianglingCustomerService.me.delete(model, Cnd.where(model.USER_ID, model.getUser_id()).and(JianglingCustomer.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}