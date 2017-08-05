package com.nowui.chuangshi.api.member.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/member")
public class MemberController extends Controller {

    private final MemberService memberService = MemberService.me;

    @ActionKey("/admin/member/list")
    public void list() {
        validateRequest(Member.MEMBER_LEVEL_ID, Member.MEMBER_STATUS, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Member model = getModel(Member.class);
        model.where(Member.APP_ID).andEmpty(Member.USER_ID).andEmpty(Member.MEMBER_LEVEL_ID).andEmpty(Member.MEMBER_STATUS);

        Integer resultCount = memberService.count(model);
        List<Member> resultList = memberService.list(model.paginate());

        validateResponse(Member.MEMBER_ID, Member.USER_ID, Member.MEMBER_PARENT_ID, Member.QRCODE_ID, Member.MEMBER_LEVEL_ID, Member.MEMBER_STATUS, Member.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/member/find")
    public void find() {
        validateRequest(Member.MEMBER_ID);

        Member model = getModel(Member.class);
        model.where(Member.MEMBER_ID);

        Member result = memberService.find(model);

        validateResponse(Member.USER_ID, Member.MEMBER_PARENT_ID, Member.FROM_QRCODE_ID, Member.QRCODE_ID, Member.MEMBER_LEVEL_ID, Member.MEMBER_PARENT_PATH, Member.MEMBER_STATUS, Member.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/member/save")
    public void save() {
        validateRequest(Member.USER_ID, Member.MEMBER_PARENT_ID, Member.FROM_QRCODE_ID, Member.QRCODE_ID, Member.MEMBER_LEVEL_ID, Member.MEMBER_PARENT_PATH, Member.MEMBER_STATUS);

        Member model = getModel(Member.class);
        model.setMember_id(Util.getRandomUUID());

        Boolean result = memberService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/update")
    public void update() {
        validateRequest(Member.MEMBER_ID, Member.USER_ID, Member.MEMBER_PARENT_ID, Member.FROM_QRCODE_ID, Member.QRCODE_ID, Member.MEMBER_LEVEL_ID, Member.MEMBER_PARENT_PATH, Member.MEMBER_STATUS, Member.SYSTEM_VERSION);

        Member model = getModel(Member.class);
        model.where(model.MEMBER_ID).and(Member.SYSTEM_VERSION);

        Boolean result = memberService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/delete")
    public void delete() {
        validateRequest(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        Member model = getModel(Member.class);
        model.where(model.MEMBER_ID).and(Member.SYSTEM_VERSION);

        Boolean result = memberService.delete(model);

        renderSuccessJson(result);
    }

}