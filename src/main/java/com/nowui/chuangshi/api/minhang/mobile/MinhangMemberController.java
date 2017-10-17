package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/mobile/minhang/member")
public class MinhangMemberController extends Controller {
    
    @ActionKey("/mobile/minhang/member/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        String request_app_id = getRequest_app_id();
        
        List<Member> resultList = MemberService.instance.appList(request_app_id, getM(), getN());
        
        validateResponse(Member.MEMBER_ID, User.USER_ID, User.USER_NAME, User.USER_AVATAR);

        renderSuccessJson(resultList);
    }

}
