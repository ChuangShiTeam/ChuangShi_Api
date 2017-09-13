package com.nowui.chuangshi.api.infiniti.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.infiniti.model.InfinitiMember;
import com.nowui.chuangshi.api.infiniti.service.InfinitiMemberService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/infiniti/member")
public class InfinitiMemberController extends Controller {

    @ActionKey("/admin/infiniti/member/list")
    public void list() {
        validateRequest(InfinitiMember.MEMBER_REDEEM_CODE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        InfinitiMember model = getModel(InfinitiMember.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = InfinitiMemberService.instance.adminCount(request_app_id, model.getMember_redeem_code());
        List<InfinitiMember> resultList = InfinitiMemberService.instance.adminList(request_app_id, model.getMember_redeem_code(), getM(), getN());

        validateResponse(InfinitiMember.MEMBER_ID, InfinitiMember.MEMBER_NAME, InfinitiMember.MEMBER_MOBILE, InfinitiMember.MEMBER_ADDRESS, InfinitiMember.MEMBER_REDEEM_CODE, InfinitiMember.MEMBER_REDEEM_CODE_IS_EXCHANGE, InfinitiMember.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/infiniti/member/find")
    public void find() {
        validateRequest(InfinitiMember.MEMBER_ID);

        InfinitiMember model = getModel(InfinitiMember.class);

        InfinitiMember result = InfinitiMemberService.instance.find(model.getMember_id());

        validateResponse(InfinitiMember.MEMBER_NAME, InfinitiMember.MEMBER_MOBILE, InfinitiMember.MEMBER_ADDRESS, InfinitiMember.MEMBER_REDEEM_CODE, InfinitiMember.MEMBER_REDEEM_CODE_IS_EXCHANGE, InfinitiMember.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/member/save")
    public void save() {
        validateRequest(InfinitiMember.MEMBER_NAME, InfinitiMember.MEMBER_MOBILE, InfinitiMember.MEMBER_ADDRESS, InfinitiMember.MEMBER_REDEEM_CODE, InfinitiMember.MEMBER_REDEEM_CODE_IS_EXCHANGE);

        InfinitiMember model = getModel(InfinitiMember.class);
        model.setMember_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = InfinitiMemberService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/member/update")
    public void update() {
        validateRequest(InfinitiMember.MEMBER_ID, InfinitiMember.MEMBER_NAME, InfinitiMember.MEMBER_MOBILE, InfinitiMember.MEMBER_ADDRESS, InfinitiMember.MEMBER_REDEEM_CODE, InfinitiMember.MEMBER_REDEEM_CODE_IS_EXCHANGE, InfinitiMember.SYSTEM_VERSION);

        InfinitiMember model = getModel(InfinitiMember.class);
        String request_user_id = getRequest_user_id();

        Boolean result = InfinitiMemberService.instance.update(model, model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/member/delete")
    public void delete() {
        validateRequest(InfinitiMember.MEMBER_ID, InfinitiMember.SYSTEM_VERSION);

        InfinitiMember model = getModel(InfinitiMember.class);
        String request_user_id = getRequest_user_id();

        Boolean result = InfinitiMemberService.instance.delete(model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}