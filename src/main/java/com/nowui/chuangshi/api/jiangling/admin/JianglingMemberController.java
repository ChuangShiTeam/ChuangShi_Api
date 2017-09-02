package com.nowui.chuangshi.api.jiangling.admin;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingMember;
import com.nowui.chuangshi.api.jiangling.service.JianglingMemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/jiangling/member")
public class JianglingMemberController extends Controller {

    @ActionKey("/admin/jiangling/member/list")
    public void list() {
        validateRequest(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        JSONObject jsonObject = getParameterJSONObject();
        String request_app_id = getRequest_app_id();
        String user_name = jsonObject.getString(User.USER_NAME);
        String member_redeem_code = jsonObject.getString(JianglingMember.MEMBER_REDEEM_CODE);

        Integer resultCount = JianglingMemberService.instance.adminCount(request_app_id, user_name, member_redeem_code);
        List<JianglingMember> resultList = JianglingMemberService.instance.adminList(request_app_id, user_name, member_redeem_code, getM(), getN());

        validateResponse(User.USER_ID, User.USER_AVATAR, User.USER_NAME, JianglingMember.MEMBER_DIFFENT_POINT, JianglingMember.MEMBER_LIKE_POINT, JianglingMember.MEMBER_REDEEM_CODE, JianglingMember.MEMBER_REDEEM_CODE_IS_EXCHANGE);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/member/like/point/update")
    public void likeUpdate() {
        validateRequest(User.USER_ID);

        JSONObject jsonObject = getParameterJSONObject();
        String user_id = jsonObject.getString(User.USER_ID);

        JianglingMember jianglingMember = new JianglingMember();
        jianglingMember.setMember_like_point(50);
        jianglingMember.setMember_redeem_code_is_exchange(true);

        Boolean result = JianglingMemberService.instance.update(jianglingMember, user_id);

        renderSuccessJson(result);
    }

}