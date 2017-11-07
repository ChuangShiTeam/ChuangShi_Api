package com.nowui.chuangshi.api.minhang.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberTask;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/minhang/member/task")
public class MinhangMemberTaskController extends Controller {

    @ActionKey("/admin/minhang/member/task/list")
    public void list() {
        validateRequest(MinhangMemberTask.MEMBER_ID, MinhangMemberTask.TASK_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangMemberTask model = getModel(MinhangMemberTask.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangMemberTaskService.instance.adminCount(request_app_id, model.getMember_id(), model.getTask_id());
        List<MinhangMemberTask> resultList = MinhangMemberTaskService.instance.adminList(request_app_id, model.getMember_id(), model.getTask_id(), getM(), getN());

        validateResponse(MinhangMemberTask.MEMBER_TASK_ID, MinhangMemberTask.MEMBER_ID, MinhangMemberTask.TASK_ID, MinhangMemberTask.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/member/task/find")
    public void find() {
        validateRequest(MinhangMemberTask.MEMBER_TASK_ID);

        MinhangMemberTask model = getModel(MinhangMemberTask.class);

        MinhangMemberTask result = MinhangMemberTaskService.instance.find(model.getMember_task_id());

        validateResponse(MinhangMemberTask.MEMBER_ID, MinhangMemberTask.USER_ID, MinhangMemberTask.TASK_ID, MinhangMemberTask.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/task/save")
    public void save() {
        validateRequest(MinhangMemberTask.MEMBER_ID, MinhangMemberTask.USER_ID, MinhangMemberTask.TASK_ID);

        MinhangMemberTask model = getModel(MinhangMemberTask.class);
        model.setMember_task_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberTaskService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/task/update")
    public void update() {
        validateRequest(MinhangMemberTask.MEMBER_TASK_ID, MinhangMemberTask.MEMBER_ID, MinhangMemberTask.USER_ID, MinhangMemberTask.TASK_ID, MinhangMemberTask.SYSTEM_VERSION);

        MinhangMemberTask model = getModel(MinhangMemberTask.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberTaskService.instance.update(model, model.getMember_task_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}