package com.nowui.chuangshi.api.jiangling.admin;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingMember;
import com.nowui.chuangshi.api.jiangling.service.JianglingMemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/jiangling/member")
public class JianglingMemberController extends Controller {

    @ActionKey("/admin/jiangling/member/list")
    public void list() {
        validateRequest(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);
//        Cnd cnd = Cnd.whereAllowEmpty(Member.APP_ID, model.getApp_id());
        Cnd cnd = Cnd.where(User.APP_ID, model.getApp_id()).and(User.USER_TYPE, "MEMBER");

        JSONObject jsonObject = getParameterJSONObject();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select \n");
        stringBuffer.append("table_user.user_id, \n");
        stringBuffer.append("table_user.user_avatar, \n");
        stringBuffer.append("table_user.user_name, \n");
        stringBuffer.append("ifnull(table_jiangling_member.member_diffent_point, 0) as member_diffent_point, \n");
        stringBuffer.append("ifnull(table_jiangling_member.member_like_point, 0) as member_like_point, \n");
        stringBuffer.append("ifnull(table_jiangling_member.member_redeem_code, '') as member_redeem_code, \n");
        stringBuffer.append("ifnull(table_jiangling_member.member_redeem_code_is_exchange, false) as member_redeem_code_is_exchange \n");
        stringBuffer.append("from table_user \n");
        stringBuffer.append("left join table_jiangling_member on table_jiangling_member.user_id = table_user.user_id \n");
        stringBuffer.append("where table_user.app_id = 'dad947bec0c54ff8b12586eda1c8e146' and table_user.user_type='MEMBER' \n");
        if (!ValidateUtil.isNullOrEmpty(model.getUser_name())) {
            stringBuffer.append("and table_user.user_name like '%" + model.getUser_name() + "%' \n");
        }
        if (!ValidateUtil.isNullOrEmpty(jsonObject.getString(JianglingMember.MEMBER_REDEEM_CODE))) {
            stringBuffer.append("and table_jiangling_member.member_redeem_code = '" + jsonObject.getString(JianglingMember.MEMBER_REDEEM_CODE) + "' \n");
        }
        stringBuffer.append(" \n");

        System.out.println(stringBuffer.toString());

        Integer resultCount = UserService.me.count(cnd);
        List<User> resultList = model.find(stringBuffer.toString());

//        validateResponse(JianglingMember.MEMBER_ID, JianglingMember.MEMBER_DIFFENT_POINT, JianglingMember.MEMBER_LIKE_POINT, JianglingMember.MEMBER_REDEEM_CODE, JianglingMember.MEMBER_REDEEM_CODE_IS_EXCHANGE, JianglingMember.SYSTEM_VERSION);
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

        Boolean result = JianglingMemberService.me.update(jianglingMember, Cnd.where(JianglingMember.USER_ID, user_id));

        renderSuccessJson(result);
    }

}