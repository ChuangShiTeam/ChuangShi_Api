package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberHistory;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberHistoryService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
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
    
    @ActionKey("/mobile/minhang/member/find")
    public void find() {
        String request_user_id = getRequest_user_id();
        
        User result = UserService.instance.find(request_user_id);
        
        List<MinhangMemberHistory> minhangMemberHistoryList = MinhangMemberHistoryService.instance.userList(request_user_id);
        
        result.put("history_list", minhangMemberHistoryList);
        
        result.put(User.USER_AVATAR, FileService.instance.getFile_path(result.getUser_avatar()));
        
        validateResponse(User.USER_NAME, User.USER_AVATAR, "history_list");

        renderSuccessJson(result);
    }
    
    /**
     * 会员签到
     */
    @ActionKey("/mobile/minhang/member/sign")
    public void sign() {
        String request_user_id = getRequest_user_id();
        
        User user = UserService.instance.find(request_user_id);
        
        Boolean result = UserService.instance.systemUpdateTimeUpdate(user.getUser_id(), request_user_id, user.getSystem_version());
        
        renderSuccessJson(result);
    }

}
