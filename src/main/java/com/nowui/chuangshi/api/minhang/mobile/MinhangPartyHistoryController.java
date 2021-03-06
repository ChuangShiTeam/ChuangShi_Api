package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangPartyHistory;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.service.MinhangPartyHistoryService;
import com.nowui.chuangshi.api.minhang.service.MinhangTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/party/history")
public class MinhangPartyHistoryController extends Controller {

    @ActionKey("/mobile/minhang/party/history/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/history/find")
    public void find() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/minhang/party/history/random/find")
    public void randomFind() {
        String request_app_id = getRequest_app_id();
        
        MinhangPartyHistory minhang_party_history = MinhangPartyHistoryService.instance.randomFind(request_app_id);
        
        MinhangTask minhangTask = MinhangTaskService.instance.find(minhang_party_history.getTask_id());
        minhang_party_history.put(MinhangTask.TASK_QRCODE_URL, minhangTask.getTask_qrcode_url());
        
        validateResponse(MinhangPartyHistory.PARTY_HISTORY_ID, MinhangTask.TASK_QRCODE_URL, MinhangPartyHistory.TASK_ID, MinhangPartyHistory.PARTY_HISTORY_CONTENT, MinhangPartyHistory.BOOK_CODE, MinhangPartyHistory.SYSTEM_VERSION);
        
        renderSuccessJson(minhang_party_history);
    }
    
    /**
     * 上一篇
     */
    @ActionKey("/mobile/minhang/party/history/prev")
    public void prev() {
        validateRequest(MinhangPartyHistory.PARTY_HISTORY_ID);
        String request_app_id = getRequest_app_id();
        
        MinhangPartyHistory model = getModel(MinhangPartyHistory.class);
        
        MinhangPartyHistory bean = MinhangPartyHistoryService.instance.find(model.getParty_history_id());
        
        MinhangPartyHistory minhang_party_history = MinhangPartyHistoryService.instance.prevHistory(request_app_id, bean.getSystem_create_time());
        
        MinhangTask minhangTask = MinhangTaskService.instance.find(minhang_party_history.getTask_id());
        minhang_party_history.put(MinhangTask.TASK_QRCODE_URL, minhangTask.getTask_qrcode_url());
        
        validateResponse(MinhangPartyHistory.PARTY_HISTORY_ID, MinhangTask.TASK_QRCODE_URL, MinhangPartyHistory.TASK_ID, MinhangPartyHistory.PARTY_HISTORY_CONTENT, MinhangPartyHistory.BOOK_CODE, MinhangPartyHistory.SYSTEM_VERSION);
        
        renderSuccessJson(minhang_party_history);
    }
    
    /**
     * 下一篇
     */
    @ActionKey("/mobile/minhang/party/history/next")
    public void next() {
    	validateRequest(MinhangPartyHistory.PARTY_HISTORY_ID);
        String request_app_id = getRequest_app_id();
        
        MinhangPartyHistory model = getModel(MinhangPartyHistory.class);
        
        MinhangPartyHistory bean = MinhangPartyHistoryService.instance.find(model.getParty_history_id());
        
        MinhangPartyHistory minhang_party_history = MinhangPartyHistoryService.instance.nextHistory(request_app_id, bean.getSystem_create_time());
        
        MinhangTask minhangTask = MinhangTaskService.instance.find(minhang_party_history.getTask_id());
        minhang_party_history.put(MinhangTask.TASK_QRCODE_URL, minhangTask.getTask_qrcode_url());
        
        validateResponse(MinhangPartyHistory.PARTY_HISTORY_ID, MinhangTask.TASK_QRCODE_URL, MinhangPartyHistory.TASK_ID, MinhangPartyHistory.PARTY_HISTORY_CONTENT, MinhangPartyHistory.BOOK_CODE, MinhangPartyHistory.SYSTEM_VERSION);
        
        renderSuccessJson(minhang_party_history);
    }


    @ActionKey("/mobile/minhang/party/history/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/history/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/history/delete")
    public void delete() {

        renderSuccessJson();
    }

}