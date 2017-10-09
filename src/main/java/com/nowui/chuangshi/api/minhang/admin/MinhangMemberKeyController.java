package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberKeyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/member/key")
public class MinhangMemberKeyController extends Controller {

    @ActionKey("/admin/minhang/member/key/list")
    public void list() {
        validateRequest(MinhangMemberKey.MEMBER_ID, MinhangMemberKey.KEY_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangMemberKey model = getModel(MinhangMemberKey.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangMemberKeyService.instance.adminCount(request_app_id, model.getMember_id(), model.getKey_id());
        List<MinhangMemberKey> resultList = MinhangMemberKeyService.instance.adminList(request_app_id, model.getMember_id(), model.getKey_id(), getM(), getN());

        validateResponse(MinhangMemberKey.MEMBER_KEY_ID, MinhangMemberKey.MEMBER_ID, MinhangMemberKey.KEY_ID, MinhangMemberKey.TASK_COMPLETE_QUANTITY, MinhangMemberKey.TASK_QUANTITY, MinhangMemberKey.KEY_IS_ACTIVATED, MinhangMemberKey.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/member/key/find")
    public void find() {
        validateRequest(MinhangMemberKey.MEMBER_KEY_ID);

        MinhangMemberKey model = getModel(MinhangMemberKey.class);

        MinhangMemberKey result = MinhangMemberKeyService.instance.find(model.getMember_key_id());

        validateResponse(MinhangMemberKey.MEMBER_ID, MinhangMemberKey.USER_ID, MinhangMemberKey.KEY_ID, MinhangMemberKey.TASK_COMPLETE_QUANTITY, MinhangMemberKey.TASK_QUANTITY, MinhangMemberKey.KEY_IS_ACTIVATED, MinhangMemberKey.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/key/save")
    public void save() {
        validateRequest(MinhangMemberKey.MEMBER_ID, MinhangMemberKey.USER_ID, MinhangMemberKey.KEY_ID, MinhangMemberKey.TASK_COMPLETE_QUANTITY, MinhangMemberKey.TASK_QUANTITY, MinhangMemberKey.KEY_IS_ACTIVATED);

        MinhangMemberKey model = getModel(MinhangMemberKey.class);
        model.setMember_key_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberKeyService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/key/update")
    public void update() {
        validateRequest(MinhangMemberKey.MEMBER_KEY_ID, MinhangMemberKey.MEMBER_ID, MinhangMemberKey.USER_ID, MinhangMemberKey.KEY_ID, MinhangMemberKey.TASK_COMPLETE_QUANTITY, MinhangMemberKey.TASK_QUANTITY, MinhangMemberKey.KEY_IS_ACTIVATED, MinhangMemberKey.SYSTEM_VERSION);

        MinhangMemberKey model = getModel(MinhangMemberKey.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberKeyService.instance.update(model, model.getMember_key_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/key/delete")
    public void delete() {
        validateRequest(MinhangMemberKey.MEMBER_KEY_ID, MinhangMemberKey.SYSTEM_VERSION);

        MinhangMemberKey model = getModel(MinhangMemberKey.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberKeyService.instance.delete(model.getMember_key_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}