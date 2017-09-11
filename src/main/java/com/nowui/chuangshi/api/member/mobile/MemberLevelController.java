package com.nowui.chuangshi.api.member.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.model.MemberLevel;
import com.nowui.chuangshi.api.member.service.MemberLevelService;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

import java.util.ArrayList;
import java.util.List;

@ControllerKey("/mobile/member/level")
public class MemberLevelController extends Controller {

    @ActionKey("/mobile/member/level/member/list")
    public void list() {
        validateRequest(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        Member member = MemberService.instance.find(model.getMember_id());

        Member parentMember = MemberService.instance.find(member.getMember_parent_id());
        MemberLevel parentMemberLevel = MemberLevelService.instance.find(parentMember.getMember_level_id());

        List<MemberLevel> resultList = new ArrayList<MemberLevel>();
        List<MemberLevel> memberLevelList = MemberLevelService.instance.appList(member.getApp_id());
        for (MemberLevel memberLevel : memberLevelList) {
            if (memberLevel.getMember_level_value() > parentMemberLevel.getMember_level_value()) {
                if (memberLevel.getMember_level_id().equals(member.getMember_level_id())) {
                    memberLevel.put(Constant.IS_SELECT, true);
                } else {
                    memberLevel.put(Constant.IS_SELECT, false);
                }

                resultList.add(memberLevel);
            }
        }

        validateResponse(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, Constant.IS_SELECT);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/member/level/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/level/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/level/children/update")
    public void childrenUpdate() {
        validateRequest(Member.MEMBER_ID);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        Member member = MemberService.instance.find(model.getMember_id());
        User parentUser = UserService.instance.find(request_user_id);
        Member parentMember = MemberService.instance.find(parentUser.getObject_id());

        if (member.getMember_parent_id().equals(parentMember.getMember_id())) {
            MemberService.instance.childrenUpdate(model.getMember_id(), model.getMember_level_id(), request_user_id);
        } else {
            throw new RuntimeException("您不是上一级");
        }

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/level/delete")
    public void delete() {

        renderSuccessJson();
    }

}