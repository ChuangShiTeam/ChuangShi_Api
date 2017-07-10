package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberLevel;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberController extends Controller {

    private final MemberService memberService = new MemberService();
    private final UserService userService = new UserService();

    private List<Map<String, Object>> getChildren(List<Member> memberList, String member_parent_id, String... keys) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Member member : memberList) {
            if (member.getMember_parent_id().equals(member_parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(Member.MEMBER_ID, member.getMember_id());
                map.put(User.USER_NAME, member.getStr(User.USER_NAME));
                map.put(User.USER_AVATAR, member.getStr(User.USER_AVATAR));
                map.put(MemberLevel.MEMBER_LEVEL_NAME, member.getStr(MemberLevel.MEMBER_LEVEL_NAME));

                for (String key : keys) {
                    map.put(key, member.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(memberList, member.getMember_id(), keys);
                if (childrenList.size() > 0) {
                    map.put(Constant.CHILDREN, childrenList);
                }
                list.add(map);
            }
        }
        return list;
    }

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

    @ActionKey(Url.MEMBER_TEAM_LIST)
    public void teamList() {
        validateRequest_app_id();

        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        User user = userService.findByUser_id(request_user_id);

        Member member = memberService.findByMember_id(user.getObject_Id());

        List<Member> memberList = memberService.listByMember_parent_pathLikeMember_parent_id(member.getMember_id());

        List<Map<String, Object>> resultList = getChildren(memberList, member.getMember_id());

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

}