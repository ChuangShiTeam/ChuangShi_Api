package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberHistory;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberHistoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/member/history")
public class MinhangMemberHistoryController extends Controller {

    @ActionKey("/admin/minhang/member/history/list")
    public void list() {
        validateRequest(MinhangMemberHistory.USER_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangMemberHistoryService.instance.adminCount(request_app_id, model.getUser_id(), model.getMember_history_name());
        List<MinhangMemberHistory> resultList = MinhangMemberHistoryService.instance.adminList(request_app_id, model.getUser_id(), model.getMember_history_name(), getM(), getN());

        validateResponse(MinhangMemberHistory.MEMBER_HISTORY_ID, MinhangMemberHistory.USER_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME, MinhangMemberHistory.IS_SAVE_HISTORY, MinhangMemberHistory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/member/history/find")
    public void find() {
        validateRequest(MinhangMemberHistory.MEMBER_HISTORY_ID);

        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);

        MinhangMemberHistory result = MinhangMemberHistoryService.instance.find(model.getMember_history_id());

        validateResponse(MinhangMemberHistory.MEMBER_ID, MinhangMemberHistory.USER_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME, MinhangMemberHistory.IS_SAVE_HISTORY, MinhangMemberHistory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/history/save")
    public void save() {
        validateRequest(MinhangMemberHistory.MEMBER_ID, MinhangMemberHistory.USER_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME, MinhangMemberHistory.IS_SAVE_HISTORY);

        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        model.setMember_history_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberHistoryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/history/update")
    public void update() {
        validateRequest(MinhangMemberHistory.MEMBER_HISTORY_ID, MinhangMemberHistory.MEMBER_ID, MinhangMemberHistory.USER_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME, MinhangMemberHistory.IS_SAVE_HISTORY, MinhangMemberHistory.SYSTEM_VERSION);

        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberHistoryService.instance.update(model, model.getMember_history_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/history/delete")
    public void delete() {
        validateRequest(MinhangMemberHistory.MEMBER_HISTORY_ID, MinhangMemberHistory.SYSTEM_VERSION);

        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberHistoryService.instance.delete(model.getMember_history_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}