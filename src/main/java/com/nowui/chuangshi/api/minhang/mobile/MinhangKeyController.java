package com.nowui.chuangshi.api.minhang.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberHistory;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberTask;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.service.MinhangKeyService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberHistoryService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberKeyService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberTaskService;
import com.nowui.chuangshi.api.minhang.service.MinhangTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/key")
public class MinhangKeyController extends Controller {

    @ActionKey("/mobile/minhang/key/list")
    public void list() {
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        
        List<MinhangKey> resultList = MinhangKeyService.instance.appList(request_app_id);
        for (MinhangKey result : resultList) {
            result.put(MinhangKey.KEY_IMAGE_FILE, FileService.instance.getFile(result.getKey_image()));
        }
        
        //用户开始寻钥之旅，开始过不会再开始
        MinhangMemberHistoryService.instance.start(request_user_id, request_app_id);
        
        validateResponse(MinhangKey.KEY_ID, MinhangKey.KEY_NAME, MinhangKey.KEY_IMAGE_FILE, MinhangKey.KEY_ACTIVATED_TASK_QUANTITY, MinhangKey.KEY_DESCRIPTION, MinhangKey.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/minhang/key/find")
    public void find() {
        validateRequest(MinhangKey.KEY_ID);
        
        MinhangKey model = getModel(MinhangKey.class);
        String request_user_id = getRequest_user_id();
        
        MinhangKey minhangKey = MinhangKeyService.instance.find(model.getKey_id());

        minhangKey.put(MinhangKey.KEY_IMAGE_FILE, FileService.instance.getFile(minhangKey.getKey_image()));

        validateResponse(MinhangKey.KEY_NAME, MinhangKey.KEY_IMAGE_FILE, MinhangKey.KEY_ACTIVATED_TASK_QUANTITY, MinhangKey.KEY_SORT, MinhangKey.KEY_DESCRIPTION, MinhangKey.SYSTEM_VERSION);

        //查询用户最近的寻钥之旅记录
        MinhangMemberHistory member_history = MinhangMemberHistoryService.instance.userLatestFind(request_user_id);
        
        if (member_history == null) {
            throw new RuntimeException("还没有开启寻钥之旅");
        }
        
        MinhangMemberKey member_key = MinhangMemberKeyService.instance.userAndKeyAndHistoryFind(request_user_id, model.getKey_id(), member_history.getMember_history_id());
       
        validateResponse(MinhangMemberKey.MEMBER_KEY_ID, MinhangMemberKey.KEY_ID, MinhangMemberKey.TASK_QUANTITY, MinhangMemberKey.TASK_COMPLETE_QUANTITY, MinhangMemberKey.KEY_IS_ACTIVATED);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("key", minhangKey);
        result.put("member_key", member_key);
        
        if (!member_key.getKey_is_activated()) {
        	List<MinhangMemberTask> member_task_list = MinhangMemberTaskService.instance.userAndKeyAndHistoryList(request_user_id, minhangKey.getKey_id(), member_history.getMember_history_id());
        	if (member_task_list != null && member_task_list.size() > 0) {
        	    for (MinhangMemberTask minhangMemberTask : member_task_list) {
                    MinhangTask task = MinhangTaskService.instance.find(minhangMemberTask.getTask_id());
                    minhangMemberTask.put(MinhangTask.TASK_NAME, task.getTask_name());
                    minhangMemberTask.put(MinhangTask.TASK_TYPE, task.getTask_type());
                }
                validateResponse(MinhangMemberTask.TASK_ID, MinhangMemberTask.KEY_ACTIVATED_STEP, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE);
                result.put("member_task_list", member_task_list);
        	}
        }
        renderSuccessJson(result);
    }

}