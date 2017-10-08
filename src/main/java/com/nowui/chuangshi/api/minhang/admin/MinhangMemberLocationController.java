package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberLocation;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberLocationService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/member/location")
public class MinhangMemberLocationController extends Controller {

    @ActionKey("/admin/minhang/member/location/list")
    public void list() {
        validateRequest(MinhangMemberLocation.MEMBER_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangMemberLocation model = getModel(MinhangMemberLocation.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangMemberLocationService.instance.adminCount(request_app_id, model.getMember_id());
        List<MinhangMemberLocation> resultList = MinhangMemberLocationService.instance.adminList(request_app_id, model.getMember_id(), getM(), getN());

        validateResponse(MinhangMemberLocation.MEMBER_LOCATION_ID, MinhangMemberLocation.MEMBER_ID, MinhangMemberLocation.LOCATION_NAME, MinhangMemberLocation.LOCATION_ADDRESS, MinhangMemberLocation.LOCATION_LATITUDE, MinhangMemberLocation.LOCATION_LONGITUDE, MinhangMemberLocation.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/member/location/find")
    public void find() {
        validateRequest(MinhangMemberLocation.MEMBER_LOCATION_ID);

        MinhangMemberLocation model = getModel(MinhangMemberLocation.class);

        MinhangMemberLocation result = MinhangMemberLocationService.instance.find(model.getMember_location_id());

        validateResponse(MinhangMemberLocation.MEMBER_ID, MinhangMemberLocation.USER_ID, MinhangMemberLocation.TASK_ID, MinhangMemberLocation.LOCATION_ID, MinhangMemberLocation.LOCATION_NAME, MinhangMemberLocation.LOCATION_ADDRESS, MinhangMemberLocation.LOCATION_LATITUDE, MinhangMemberLocation.LOCATION_LONGITUDE, MinhangMemberLocation.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/location/save")
    public void save() {
        validateRequest(MinhangMemberLocation.MEMBER_ID, MinhangMemberLocation.USER_ID, MinhangMemberLocation.TASK_ID, MinhangMemberLocation.LOCATION_ID, MinhangMemberLocation.LOCATION_NAME, MinhangMemberLocation.LOCATION_ADDRESS, MinhangMemberLocation.LOCATION_LATITUDE, MinhangMemberLocation.LOCATION_LONGITUDE);

        MinhangMemberLocation model = getModel(MinhangMemberLocation.class);
        model.setMember_location_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberLocationService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/location/update")
    public void update() {
        validateRequest(MinhangMemberLocation.MEMBER_LOCATION_ID, MinhangMemberLocation.MEMBER_ID, MinhangMemberLocation.USER_ID, MinhangMemberLocation.TASK_ID, MinhangMemberLocation.LOCATION_ID, MinhangMemberLocation.LOCATION_NAME, MinhangMemberLocation.LOCATION_ADDRESS, MinhangMemberLocation.LOCATION_LATITUDE, MinhangMemberLocation.LOCATION_LONGITUDE, MinhangMemberLocation.SYSTEM_VERSION);

        MinhangMemberLocation model = getModel(MinhangMemberLocation.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberLocationService.instance.update(model, model.getMember_location_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/location/delete")
    public void delete() {
        validateRequest(MinhangMemberLocation.MEMBER_LOCATION_ID, MinhangMemberLocation.SYSTEM_VERSION);

        MinhangMemberLocation model = getModel(MinhangMemberLocation.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberLocationService.instance.delete(model.getMember_location_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}