package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMember;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/member")
public class MinhangMemberController extends Controller {

    @ActionKey("/admin/minhang/member/list")
    public void list() {
        validateRequest(MinhangMember.MEMBER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangMember model = getModel(MinhangMember.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangMemberService.instance.adminCount(request_app_id, model.getMember_name());
        List<MinhangMember> resultList = MinhangMemberService.instance.adminList(request_app_id, model.getMember_name(), getM(), getN());

        validateResponse(MinhangMember.MEMBER_ID, MinhangMember.MEMBER_NAME, MinhangMember.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/member/find")
    public void find() {
        validateRequest(MinhangMember.MEMBER_ID);

        MinhangMember model = getModel(MinhangMember.class);

        MinhangMember result = MinhangMemberService.instance.find(model.getMember_id());

        validateResponse(MinhangMember.MEMBER_NAME, MinhangMember.USER_ID, MinhangMember.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/save")
    public void save() {
        validateRequest(MinhangMember.MEMBER_NAME, MinhangMember.USER_ID);

        MinhangMember model = getModel(MinhangMember.class);
        model.setMember_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/update")
    public void update() {
        validateRequest(MinhangMember.MEMBER_ID, MinhangMember.MEMBER_NAME, MinhangMember.USER_ID, MinhangMember.SYSTEM_VERSION);

        MinhangMember model = getModel(MinhangMember.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberService.instance.update(model, model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/delete")
    public void delete() {
        validateRequest(MinhangMember.MEMBER_ID, MinhangMember.SYSTEM_VERSION);

        MinhangMember model = getModel(MinhangMember.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberService.instance.delete(model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}