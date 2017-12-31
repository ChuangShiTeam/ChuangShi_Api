package com.nowui.chuangshi.api.guangqi.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearCustomer;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearCustomerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/guangqi/new/year/customer")
public class GuangqiNewYearCustomerController extends Controller {

    @ActionKey("/admin/guangqi/new/year/customer/list")
    public void list() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GuangqiNewYearCustomerService.instance.adminCount(request_app_id, model.getNew_year_customer_car_model(), model.getNew_year_customer_name(), model.getNew_year_customer_phone(), model.getNew_year_customer_province(), model.getNew_year_customer_city(), model.getNew_year_customer_dealer(), model.getNew_year_customer_from());
        List<GuangqiNewYearCustomer> resultList = GuangqiNewYearCustomerService.instance.adminList(request_app_id, model.getNew_year_customer_car_model(), model.getNew_year_customer_name(), model.getNew_year_customer_phone(), model.getNew_year_customer_province(), model.getNew_year_customer_city(), model.getNew_year_customer_dealer(), model.getNew_year_customer_from(), getM(), getN());

        validateResponse(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ADDRESS, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, GuangqiNewYearCustomer.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/guangqi/new/year/customer/find")
    public void find() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);

        GuangqiNewYearCustomer result = GuangqiNewYearCustomerService.instance.find(model.getNew_year_customer_id());

        validateResponse(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ADDRESS, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, GuangqiNewYearCustomer.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/customer/save")
    public void save() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ADDRESS, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
        model.setNew_year_customer_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearCustomerService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/customer/update")
    public void update() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ADDRESS, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, GuangqiNewYearCustomer.SYSTEM_VERSION);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearCustomerService.instance.update(model, model.getNew_year_customer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/customer/delete")
    public void delete() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomer.SYSTEM_VERSION);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearCustomerService.instance.delete(model.getNew_year_customer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/guangqi/new/year/customer/all/export")
    public void allExport() {
    	
    }

}