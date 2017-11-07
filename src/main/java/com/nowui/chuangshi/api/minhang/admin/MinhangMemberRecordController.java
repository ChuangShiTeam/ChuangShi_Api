package com.nowui.chuangshi.api.minhang.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberRecord;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberRecordService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/minhang/member/record")
public class MinhangMemberRecordController extends Controller {

    @ActionKey("/admin/minhang/member/record/list")
    public void list() {
        validateRequest(MinhangMemberRecord.MEMBER_ID, MinhangMemberRecord.TASK_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangMemberRecord model = getModel(MinhangMemberRecord.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangMemberRecordService.instance.adminCount(request_app_id, model.getMember_id(), model.getTask_id());
        List<MinhangMemberRecord> resultList = MinhangMemberRecordService.instance.adminList(request_app_id, model.getMember_id(), model.getTask_id(), getM(), getN());

        validateResponse(MinhangMemberRecord.MEMBER_RECORD_ID, MinhangMemberRecord.MEMBER_ID, MinhangMemberRecord.TASK_ID, MinhangMemberRecord.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/member/record/find")
    public void find() {
        validateRequest(MinhangMemberRecord.MEMBER_RECORD_ID);

        MinhangMemberRecord model = getModel(MinhangMemberRecord.class);

        MinhangMemberRecord result = MinhangMemberRecordService.instance.find(model.getMember_record_id());

        validateResponse(MinhangMemberRecord.MEMBER_ID, MinhangMemberRecord.USER_ID, MinhangMemberRecord.TASK_ID, MinhangMemberRecord.RECORD_FILE, MinhangMemberRecord.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/record/save")
    public void save() {
        validateRequest(MinhangMemberRecord.MEMBER_ID, MinhangMemberRecord.USER_ID, MinhangMemberRecord.TASK_ID, MinhangMemberRecord.RECORD_FILE);

        MinhangMemberRecord model = getModel(MinhangMemberRecord.class);
        model.setMember_record_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberRecordService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/record/update")
    public void update() {
        validateRequest(MinhangMemberRecord.MEMBER_RECORD_ID, MinhangMemberRecord.MEMBER_ID, MinhangMemberRecord.USER_ID, MinhangMemberRecord.TASK_ID, MinhangMemberRecord.RECORD_FILE, MinhangMemberRecord.SYSTEM_VERSION);

        MinhangMemberRecord model = getModel(MinhangMemberRecord.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberRecordService.instance.update(model, model.getMember_record_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/record/delete")
    public void delete() {
        validateRequest(MinhangMemberRecord.MEMBER_RECORD_ID, MinhangMemberRecord.SYSTEM_VERSION);

        MinhangMemberRecord model = getModel(MinhangMemberRecord.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberRecordService.instance.delete(model.getMember_record_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}