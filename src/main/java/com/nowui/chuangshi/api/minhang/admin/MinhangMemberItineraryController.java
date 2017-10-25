package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberItinerary;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberItineraryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/member/itinerary")
public class MinhangMemberItineraryController extends Controller {

    @ActionKey("/admin/minhang/member/itinerary/list")
    public void list() {
        validateRequest(MinhangMemberItinerary.USER_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangMemberItinerary model = getModel(MinhangMemberItinerary.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangMemberItineraryService.instance.adminCount(request_app_id, model.getUser_id());
        List<MinhangMemberItinerary> resultList = MinhangMemberItineraryService.instance.adminList(request_app_id, model.getUser_id(), getM(), getN());

        validateResponse(MinhangMemberItinerary.MEMBER_ITINERARY_ID, MinhangMemberItinerary.USER_ID, MinhangMemberItinerary.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/member/itinerary/find")
    public void find() {
        validateRequest(MinhangMemberItinerary.MEMBER_ITINERARY_ID);

        MinhangMemberItinerary model = getModel(MinhangMemberItinerary.class);

        MinhangMemberItinerary result = MinhangMemberItineraryService.instance.find(model.getMember_itinerary_id());

        validateResponse(MinhangMemberItinerary.MEMBER_ID, MinhangMemberItinerary.USER_ID, MinhangMemberItinerary.ITINERARY_IS_COMPLETE, MinhangMemberItinerary.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/itinerary/save")
    public void save() {
        validateRequest(MinhangMemberItinerary.MEMBER_ID, MinhangMemberItinerary.USER_ID, MinhangMemberItinerary.ITINERARY_IS_COMPLETE);

        MinhangMemberItinerary model = getModel(MinhangMemberItinerary.class);
        model.setMember_itinerary_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberItineraryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/itinerary/update")
    public void update() {
        validateRequest(MinhangMemberItinerary.MEMBER_ITINERARY_ID, MinhangMemberItinerary.MEMBER_ID, MinhangMemberItinerary.USER_ID, MinhangMemberItinerary.ITINERARY_IS_COMPLETE, MinhangMemberItinerary.SYSTEM_VERSION);

        MinhangMemberItinerary model = getModel(MinhangMemberItinerary.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberItineraryService.instance.update(model, model.getMember_itinerary_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/itinerary/delete")
    public void delete() {
        validateRequest(MinhangMemberItinerary.MEMBER_ITINERARY_ID, MinhangMemberItinerary.SYSTEM_VERSION);

        MinhangMemberItinerary model = getModel(MinhangMemberItinerary.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberItineraryService.instance.delete(model.getMember_itinerary_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}