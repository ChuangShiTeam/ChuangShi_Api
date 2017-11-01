package com.nowui.chuangshi.api.renault.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultMember;
import com.nowui.chuangshi.api.renault.service.RenaultMemberService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/renault/member")
public class RenaultMemberController extends Controller {

    @ActionKey("/admin/renault/member/list")
    public void list() {
        validateRequest(RenaultMember.USER_ID, RenaultMember.MEMBER_NICK_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        RenaultMember model = getModel(RenaultMember.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultMemberService.instance.adminCount(request_app_id, model.getUser_id(), model.getMember_nick_name());
        List<RenaultMember> resultList = RenaultMemberService.instance.adminList(request_app_id, model.getUser_id(), model.getMember_nick_name(), getM(), getN());

        validateResponse(RenaultMember.MEMBER_ID, RenaultMember.USER_ID, RenaultMember.MEMBER_NICK_NAME, RenaultMember.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/member/find")
    public void find() {
        validateRequest(RenaultMember.MEMBER_ID);

        RenaultMember model = getModel(RenaultMember.class);

        RenaultMember result = RenaultMemberService.instance.find(model.getMember_id());

        validateResponse(RenaultMember.USER_ID, RenaultMember.MEMBER_NICK_NAME, RenaultMember.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/member/save")
    public void save() {
        validateRequest(RenaultMember.USER_ID, RenaultMember.MEMBER_NICK_NAME);

        RenaultMember model = getModel(RenaultMember.class);
        model.setMember_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultMemberService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/member/update")
    public void update() {
        validateRequest(RenaultMember.MEMBER_ID, RenaultMember.USER_ID, RenaultMember.MEMBER_NICK_NAME, RenaultMember.SYSTEM_VERSION);

        RenaultMember model = getModel(RenaultMember.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultMemberService.instance.update(model, model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/member/delete")
    public void delete() {
        validateRequest(RenaultMember.MEMBER_ID, RenaultMember.SYSTEM_VERSION);

        RenaultMember model = getModel(RenaultMember.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultMemberService.instance.delete(model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}