package com.nowui.chuangshi.api.minhang.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangPartyHistory;
import com.nowui.chuangshi.api.minhang.service.MinhangPartyHistoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/minhang/party/history")
public class MinhangPartyHistoryController extends Controller {

    @ActionKey("/admin/minhang/party/history/list")
    public void list() {
        validateRequest(MinhangPartyHistory.BOOK_CODE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangPartyHistory model = getModel(MinhangPartyHistory.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangPartyHistoryService.instance.adminCount(request_app_id, model.getBook_code());
        List<MinhangPartyHistory> resultList = MinhangPartyHistoryService.instance.adminList(request_app_id, model.getBook_code(), getM(), getN());

        validateResponse(MinhangPartyHistory.PARTY_HISTORY_ID, MinhangPartyHistory.TASK_ID, MinhangPartyHistory.BOOK_CODE, MinhangPartyHistory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/party/history/find")
    public void find() {
        validateRequest(MinhangPartyHistory.PARTY_HISTORY_ID);

        MinhangPartyHistory model = getModel(MinhangPartyHistory.class);

        MinhangPartyHistory result = MinhangPartyHistoryService.instance.find(model.getParty_history_id());

        validateResponse(MinhangPartyHistory.TASK_ID, MinhangPartyHistory.PARTY_HISTORY_CONTENT, MinhangPartyHistory.BOOK_CODE, MinhangPartyHistory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/party/history/save")
    public void save() {
        validateRequest(MinhangPartyHistory.TASK_ID, MinhangPartyHistory.PARTY_HISTORY_CONTENT, MinhangPartyHistory.BOOK_CODE);

        MinhangPartyHistory model = getModel(MinhangPartyHistory.class);
        model.setParty_history_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangPartyHistoryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/party/history/update")
    public void update() {
        validateRequest(MinhangPartyHistory.PARTY_HISTORY_ID, MinhangPartyHistory.TASK_ID, MinhangPartyHistory.PARTY_HISTORY_CONTENT, MinhangPartyHistory.BOOK_CODE, MinhangPartyHistory.SYSTEM_VERSION);

        MinhangPartyHistory model = getModel(MinhangPartyHistory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangPartyHistoryService.instance.update(model, model.getParty_history_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/party/history/delete")
    public void delete() {
        validateRequest(MinhangPartyHistory.PARTY_HISTORY_ID, MinhangPartyHistory.SYSTEM_VERSION);

        MinhangPartyHistory model = getModel(MinhangPartyHistory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangPartyHistoryService.instance.delete(model.getParty_history_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}