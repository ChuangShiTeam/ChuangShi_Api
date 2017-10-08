package com.nowui.chuangshi.api.minhang.admin;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.api.minhang.model.MinhangLocation;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.service.MinhangLocationService;
import com.nowui.chuangshi.api.minhang.service.MinhangTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.MinhangTaskType;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/task")
public class MinhangTaskController extends Controller {

    @ActionKey("/admin/minhang/task/list")
    public void list() {
        validateRequest(MinhangTask.KEY_ID, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangTask model = getModel(MinhangTask.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangTaskService.instance.adminCount(request_app_id, model.getKey_id(), model.getTask_name(), model.getTask_type());
        List<MinhangTask> resultList = MinhangTaskService.instance.adminList(request_app_id, model.getKey_id(), model.getTask_name(), model.getTask_type(), getM(), getN());
        
        validateResponse(MinhangTask.TASK_ID, MinhangTask.KEY_ID, MinhangKey.KEY_NAME, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, MinhangTask.TASK_QRCODE_URL, MinhangTask.TASK_SORT, MinhangTask.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/task/find")
    public void find() {
        validateRequest(MinhangTask.TASK_ID);

        MinhangTask model = getModel(MinhangTask.class);

        MinhangTask result = MinhangTaskService.instance.find(model.getTask_id());
        
        if (MinhangTaskType.LOCATION.getKey().equals(result.getTask_type())) {
        	List<MinhangLocation> location_list = MinhangLocationService.instance.taskList(model.getTask_id());
        	result.put(MinhangTask.LOCATION_LIST, location_list);
        } else if (MinhangTaskType.QUESTION.getKey().equals(result.getTask_type())) {
        	
        }

        validateResponse(MinhangTask.KEY_ID, MinhangTask.TASK_NAME, MinhangTask.LOCATION_LIST, MinhangTask.QUESTION, MinhangTask.TASK_TYPE, MinhangTask.TASK_QRCODE_URL, MinhangTask.TASK_SORT, MinhangTask.TASK_DESCRIPTION, MinhangTask.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/task/save")
    public void save() {
        validateRequest(MinhangTask.KEY_ID, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, MinhangTask.TASK_SORT, MinhangTask.TASK_DESCRIPTION);

        MinhangTask model = getModel(MinhangTask.class);
        model.setTask_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTaskService.instance.save(model, request_user_id);
        
        if (result) {
        	JSONObject jsonObject = getParameterJSONObject();
        	if (MinhangTaskType.LOCATION.getKey().equals(model.getTask_type())) { //保存任务标记位置信息
            	JSONArray locationList = jsonObject.getJSONArray("locationList");
                for (int i = 0; i < locationList.size(); i++) {
                    JSONObject bean = locationList.getJSONObject(i);
                    MinhangLocation minhangLocation = bean.toJavaObject(MinhangLocation.class);
                    minhangLocation.setApp_id(model.getApp_id());
                    minhangLocation.setLocation_id(Util.getRandomUUID());
                    minhangLocation.setTask_id(model.getTask_id());
                    MinhangLocationService.instance.save(minhangLocation, request_user_id);
                    
                }
            } else if (MinhangTaskType.QUESTION.getKey().equals(model.getTask_type())) {  //保存答题信息
            	
            }
        }

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/task/update")
    public void update() {
        validateRequest(MinhangTask.TASK_ID, MinhangTask.KEY_ID, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, MinhangTask.TASK_QRCODE_URL, MinhangTask.TASK_SORT, MinhangTask.TASK_DESCRIPTION, MinhangTask.SYSTEM_VERSION);

        MinhangTask model = getModel(MinhangTask.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTaskService.instance.update(model, model.getTask_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/task/delete")
    public void delete() {
        validateRequest(MinhangTask.TASK_ID, MinhangTask.SYSTEM_VERSION);

        MinhangTask model = getModel(MinhangTask.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTaskService.instance.delete(model.getTask_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}