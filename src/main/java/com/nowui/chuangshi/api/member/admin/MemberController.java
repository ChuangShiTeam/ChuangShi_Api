package com.nowui.chuangshi.api.member.admin;


import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;

import java.util.List;


@ControllerKey("/admin/member")
public class MemberController extends Controller {

    @ActionKey("/admin/member/list")
    public void list() {
        validateRequest(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MemberService.instance.adminCount(request_app_id, model.getUser_name());
        List<Member> resultList = MemberService.instance.adminList(request_app_id, model.getUser_name(), getM(), getN());

        validateResponse(Member.MEMBER_ID, Member.USER_ID, User.USER_AVATAR, User.USER_NAME, Member.SYSTEM_CREATE_TIME, Member.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/member/find")
    public void find() {
        validateRequest(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        Member result = MemberService.instance.find(model.getMember_id());

        validateResponse(Member.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/save")
    public void save() {
        validateRequest(Member.MEMBER_PARENT_ID, Member.MEMBER_LEVEL_ID, User.USER_NAME, User.USER_ACCOUNT, User.USER_PASSWORD);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String request_user_id = getRequest_user_id();

        Boolean result = MemberService.instance.accountSave(request_app_id, jsonObject.getString(Member.MEMBER_PARENT_ID), jsonObject.getString(Member.MEMBER_LEVEL_ID), jsonObject.getString(User.USER_NAME), jsonObject.getString(User.USER_ACCOUNT), jsonObject.getString(User.USER_PASSWORD), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}