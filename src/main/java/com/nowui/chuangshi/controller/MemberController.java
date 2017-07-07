package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class MemberController extends Controller {

    private final MemberService memberService = new MemberService();

    @ActionKey(Url.MEMBER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Member> resultList = memberService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());
        authenticateSystem_create_user_id(member.getSystem_create_user_id());

        member.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        renderSuccessJson(member);
    }

    @ActionKey(Url.MEMBER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Member.USER_ID, Member.MEMBER_PARENT_ID, Member.FROM_QRCODE_ID, Member.QRCODE_ID, Member.MEMBER_LEVEL_ID, Member.MEMBER_PARENT_PATH, Member.MEMBER_STATUS);

        Member model = getModel(Member.class);
        String member_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = memberService.save(member_id, request_app_id, model.getUser_id(), model.getMember_parent_id(), model.getFrom_qrcode_id(), model.getQrcode_id(), model.getMember_level_id(), model.getMember_parent_path(), model.getMember_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID, Member.USER_ID, Member.MEMBER_PARENT_ID, Member.FROM_QRCODE_ID, Member.QRCODE_ID, Member.MEMBER_LEVEL_ID, Member.MEMBER_PARENT_PATH, Member.MEMBER_STATUS, Member.SYSTEM_VERSION);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());
        authenticateSystem_create_user_id(member.getSystem_create_user_id());

        Boolean result = memberService.updateValidateSystem_version(model.getMember_id(), model.getUser_id(), model.getMember_parent_id(), model.getFrom_qrcode_id(), model.getQrcode_id(), model.getMember_level_id(), model.getMember_parent_path(), model.getMember_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());
        authenticateSystem_create_user_id(member.getSystem_create_user_id());

        Boolean result = memberService.deleteByMember_idAndSystem_update_user_idValidateSystem_version(model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberService.countByApp_idOrLikeUser_name(request_app_id, model.getUser_name());
        List<Member> resultList = memberService.listByApp_idOrLikeUser_nameAndLimit(request_app_id, model.getUser_name(), getM(), getN());

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }
    
    @ActionKey(Url.MEMBER_ADMIN_ALL_LIST)
    public void adminAllList() {
    	validateRequest_app_id();
    	
    	String request_app_id = getRequest_app_id();
    	
    	authenticateRequest_app_idAndRequest_user_id();
    	
    	List<Member> resultList = memberService.listByApp_id(request_app_id);
    	
    	for (Member result : resultList) {
    		result.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);
    	}
    	
    	renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());

        member.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        renderSuccessJson(member);
    }

    @ActionKey(Url.MEMBER_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.MEMBER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID, Member.USER_ID, Member.MEMBER_PARENT_ID, Member.FROM_QRCODE_ID, Member.QRCODE_ID, Member.MEMBER_LEVEL_ID, Member.MEMBER_PARENT_PATH, Member.MEMBER_STATUS, Member.SYSTEM_VERSION);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());

        Boolean result = memberService.updateValidateSystem_version(model.getMember_id(), model.getUser_id(), model.getMember_parent_id(), model.getFrom_qrcode_id(), model.getQrcode_id(), model.getMember_level_id(), model.getMember_parent_path(), model.getMember_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());

        Boolean result = memberService.deleteByMember_idAndSystem_update_user_idValidateSystem_version(model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Member.APP_ID, User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);

        Integer total = memberService.countByOrApp_idOrLikeUser_name(model.getApp_id(), model.getUser_name());
        List<Member> resultList = memberService.listByOrApp_idOrLikeUser_nameAndLimit(model.getApp_id(), model.getUser_name(), getM(), getN());

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }
    
    @ActionKey(Url.MEMBER_SYSTEM_ALL_LIST)
    public void systemAllList() {
    	validateRequest_app_id();
    	
    	JSONObject jsonObject = getParameterJSONObject();
    	String app_id = jsonObject.getString("app_id");
    	
    	List<Member> resultList = memberService.listByOrApp_id(app_id);
    	
    	for (Member result : resultList) {
    		result.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);
    	}
    	
    	renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        Member member = memberService.findByMember_id(model.getMember_id());

        member.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        renderSuccessJson(member);
    }

    @ActionKey(Url.MEMBER_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Member.APP_ID, Member.USER_ID, Member.MEMBER_PARENT_ID, Member.FROM_QRCODE_ID, Member.QRCODE_ID, Member.MEMBER_LEVEL_ID, Member.MEMBER_PARENT_PATH, Member.MEMBER_STATUS);

        Member model = getModel(Member.class);
        String member_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = memberService.save(member_id, model.getApp_id(), model.getUser_id(), model.getMember_parent_id(), model.getFrom_qrcode_id(), model.getQrcode_id(), model.getMember_level_id(), model.getMember_parent_path(), model.getMember_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID, Member.USER_ID, Member.MEMBER_PARENT_ID, Member.FROM_QRCODE_ID, Member.QRCODE_ID, Member.MEMBER_LEVEL_ID, Member.MEMBER_PARENT_PATH, Member.MEMBER_STATUS, Member.SYSTEM_VERSION);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberService.updateValidateSystem_version(model.getMember_id(), model.getUser_id(), model.getMember_parent_id(), model.getFrom_qrcode_id(), model.getQrcode_id(), model.getMember_level_id(), model.getMember_parent_path(), model.getMember_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberService.deleteByMember_idAndSystem_update_user_idValidateSystem_version(model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}