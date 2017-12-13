package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultCustomer;
import com.nowui.chuangshi.api.renault.service.RenaultCustomerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/renault/customer")
public class RenaultCustomerController extends Controller {

    @ActionKey("/mobile/renault/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/customer/find")
    public void find() {

        renderSuccessJson();
    }

    //用户信息保存
    //add by lyn 2017.11.1
    @ActionKey("/mobile/renault/customer/save")
    public void save() {
        validateRequest(RenaultCustomer.CUSTOMER_NAME, RenaultCustomer.CUSTOMER_PHONE);
        
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        
        //判断用户是否保存过信息
        RenaultCustomer bean = RenaultCustomerService.instance.userFind(request_user_id);
        
        if (bean != null) {
        	throw new RuntimeException("您已经提交过了，每个用户只能提交一次");
        }
        
        RenaultCustomer renault_customer = getModel(RenaultCustomer.class);

        renault_customer.setCustomer_id(Util.getRandomUUID());
        renault_customer.setApp_id(request_app_id);
        Boolean result = RenaultCustomerService.instance.save(renault_customer, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/renault/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/customer/delete")
    public void delete() {

        renderSuccessJson();
    }

}