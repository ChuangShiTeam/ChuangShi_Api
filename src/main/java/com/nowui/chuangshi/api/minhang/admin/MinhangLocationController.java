package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangLocation;
import com.nowui.chuangshi.api.minhang.service.MinhangLocationService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/location")
public class MinhangLocationController extends Controller {

    @ActionKey("/admin/minhang/location/list")
    public void list() {
        validateRequest(MinhangLocation.LOCATION_TITLE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangLocation model = getModel(MinhangLocation.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangLocationService.instance.adminCount(request_app_id, model.getLocation_title());
        List<MinhangLocation> resultList = MinhangLocationService.instance.adminList(request_app_id, model.getLocation_title(), getM(), getN());

        validateResponse(MinhangLocation.LOCATION_ID, MinhangLocation.LOCATION_TITLE, MinhangLocation.LOCATION_SORT, MinhangLocation.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/location/find")
    public void find() {
        validateRequest(MinhangLocation.LOCATION_ID);

        MinhangLocation model = getModel(MinhangLocation.class);

        MinhangLocation result = MinhangLocationService.instance.find(model.getLocation_id());

        validateResponse(MinhangLocation.TASK_ID, MinhangLocation.LOCATION_TITLE, MinhangLocation.LOCATION_SORT, MinhangLocation.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/location/save")
    public void save() {
        validateRequest(MinhangLocation.TASK_ID, MinhangLocation.LOCATION_TITLE, MinhangLocation.LOCATION_SORT);

        MinhangLocation model = getModel(MinhangLocation.class);
        model.setLocation_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangLocationService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/location/update")
    public void update() {
        validateRequest(MinhangLocation.LOCATION_ID, MinhangLocation.TASK_ID, MinhangLocation.LOCATION_TITLE, MinhangLocation.LOCATION_SORT, MinhangLocation.SYSTEM_VERSION);

        MinhangLocation model = getModel(MinhangLocation.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangLocationService.instance.update(model, model.getLocation_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/location/delete")
    public void delete() {
        validateRequest(MinhangLocation.LOCATION_ID, MinhangLocation.SYSTEM_VERSION);

        MinhangLocation model = getModel(MinhangLocation.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangLocationService.instance.delete(model.getLocation_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}