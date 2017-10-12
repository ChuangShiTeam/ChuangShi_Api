package com.nowui.chuangshi.api.minhang.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberTask;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestion;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberTaskService;
import com.nowui.chuangshi.api.minhang.service.MinhangQuestionService;
import com.nowui.chuangshi.api.minhang.service.MinhangTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.type.MinhangTaskType;

@ControllerKey("/mobile/minhang/task")
public class MinhangTaskController extends Controller {

    @ActionKey("/mobile/minhang/task/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/task/find")
    public void find() {
    	validateRequest(MinhangTask.TASK_ID);

        MinhangTask model = getModel(MinhangTask.class);
        
        String request_user_id = getRequest_user_id();

        MinhangTask minhangTask = MinhangTaskService.instance.find(model.getTask_id());
        
        validateResponse(MinhangTask.KEY_ID, MinhangTask.TASK_NAME, MinhangTask.SCREEN_ID, MinhangTask.TASK_TYPE, MinhangTask.TASK_QRCODE_URL, MinhangTask.TASK_SORT, MinhangTask.TASK_DESCRIPTION, MinhangTask.SYSTEM_VERSION);

        MinhangMemberTask minhangMemberTask = MinhangMemberTaskService.instance.userAndTaskFind(request_user_id, model.getTask_id());
        
        Map<String, Object> result = new HashMap<String, Object>();
        
        if (minhangMemberTask == null) {
        	//如果为答题任务查询题目列表
        	if (MinhangTaskType.QUESTION.getKey().equals(minhangTask.getTask_type())) {
        		List<MinhangQuestion> minhang_question_list = MinhangQuestionService.instance.taskList(minhangTask.getTask_id());
        		minhangTask.put(MinhangTask.QUESTION_LIST, minhang_question_list);
        		validateResponse(MinhangTask.QUESTION_LIST);
        	}
        } else {
        	validateResponse(MinhangMemberTask.MEMBER_TASK_ID, MinhangMemberTask.MEMBER_ID, MinhangMemberTask.TASK_ID);
        	result.put("member_task", minhangMemberTask);
        }
        result.put("task", minhangTask);
        
        renderSuccessJson(result);
    }

    @ActionKey("/mobile/minhang/task/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/task/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/task/delete")
    public void delete() {

        renderSuccessJson();
    }

}