package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangVideoTask;
import com.nowui.chuangshi.api.minhang.service.MinhangVideoTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/video/task")
public class MinhangVideoTaskController extends Controller {

    @ActionKey("/admin/minhang/video/task/list")
    public void list() {
        validateRequest(MinhangVideoTask.VIDEO_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangVideoTask model = getModel(MinhangVideoTask.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangVideoTaskService.instance.adminCount(request_app_id, model.getVideo_id());
        List<MinhangVideoTask> resultList = MinhangVideoTaskService.instance.adminList(request_app_id, model.getVideo_id(), getM(), getN());

        validateResponse(MinhangVideoTask.VIDEO_TASK_ID, MinhangVideoTask.VIDEO_ID, MinhangVideoTask.TASK_ID, MinhangVideoTask.VIDEO_TASK_TIME, MinhangVideoTask.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/video/task/find")
    public void find() {
        validateRequest(MinhangVideoTask.VIDEO_TASK_ID);

        MinhangVideoTask model = getModel(MinhangVideoTask.class);

        MinhangVideoTask result = MinhangVideoTaskService.instance.find(model.getVideo_task_id());

        validateResponse(MinhangVideoTask.VIDEO_ID, MinhangVideoTask.TASK_ID, MinhangVideoTask.VIDEO_TASK_TIME, MinhangVideoTask.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/video/task/save")
    public void save() {
        validateRequest(MinhangVideoTask.VIDEO_ID, MinhangVideoTask.TASK_ID, MinhangVideoTask.VIDEO_TASK_TIME);

        MinhangVideoTask model = getModel(MinhangVideoTask.class);
        model.setVideo_task_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangVideoTaskService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/video/task/update")
    public void update() {
        validateRequest(MinhangVideoTask.VIDEO_TASK_ID, MinhangVideoTask.VIDEO_ID, MinhangVideoTask.TASK_ID, MinhangVideoTask.VIDEO_TASK_TIME, MinhangVideoTask.SYSTEM_VERSION);

        MinhangVideoTask model = getModel(MinhangVideoTask.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangVideoTaskService.instance.update(model, model.getVideo_task_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/video/task/delete")
    public void delete() {
        validateRequest(MinhangVideoTask.VIDEO_TASK_ID, MinhangVideoTask.SYSTEM_VERSION);

        MinhangVideoTask model = getModel(MinhangVideoTask.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangVideoTaskService.instance.delete(model.getVideo_task_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}