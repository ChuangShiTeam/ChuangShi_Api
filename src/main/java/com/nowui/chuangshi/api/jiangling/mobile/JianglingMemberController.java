package com.nowui.chuangshi.api.jiangling.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingMember;
import com.nowui.chuangshi.api.jiangling.service.JianglingMemberService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/jiangling/member")
public class JianglingMemberController extends Controller {

    @ActionKey("/mobile/jiangling/member/diffent/point/update")
    public void diffentUpdate() {
        String request_user_id = getRequest_user_id();

        Integer count = JianglingMemberService.me.count(Cnd.where(JianglingMember.USER_ID, request_user_id));
        Boolean result;
        if (count == 0) {
            String redeem_code = Util.getRandomNumber(6);
            Boolean is_exit = true;

            while (is_exit) {
                count = JianglingMemberService.me.count(Cnd.where(JianglingMember.MEMBER_REDEEM_CODE, redeem_code));

                if (count > 0) {
                    redeem_code = Util.getRandomStringByLength(6);
                } else {
                    is_exit = false;
                }

            }

            JianglingMember jianglingMember = new JianglingMember();
            jianglingMember.setUser_id(request_user_id);
            jianglingMember.setMember_diffent_point(50);
            jianglingMember.setMember_like_point(0);
            jianglingMember.setMember_redeem_code(redeem_code);
            jianglingMember.setMember_redeem_code_is_exchange(false);

            result = JianglingMemberService.me.save(jianglingMember);
        } else {
            JianglingMember jianglingMember = new JianglingMember();
            jianglingMember.setMember_diffent_point(50);

            result = JianglingMemberService.me.update(jianglingMember, Cnd.where(JianglingMember.USER_ID, request_user_id));
        }


        renderSuccessJson(result);
    }

    @ActionKey("/mobile/jiangling/member/like/point/update")
    public void likeUpdate() {
        String request_user_id = getRequest_user_id();

        JianglingMember jianglingMember = JianglingMemberService.me.find(Cnd.where(JianglingMember.USER_ID, request_user_id));
        String redeem_code;
        if (jianglingMember == null) {
            redeem_code = Util.getRandomNumber(6);
            Boolean is_exit = true;

            while (is_exit) {
                Integer count = JianglingMemberService.me.count(Cnd.where(JianglingMember.MEMBER_REDEEM_CODE, redeem_code));

                if (count > 0) {
                    redeem_code = Util.getRandomStringByLength(6);
                } else {
                    is_exit = false;
                }

            }

            jianglingMember = new JianglingMember();
            jianglingMember.setUser_id(request_user_id);
            jianglingMember.setMember_diffent_point(0);
            jianglingMember.setMember_like_point(0);
            jianglingMember.setMember_redeem_code(redeem_code);
            jianglingMember.setMember_redeem_code_is_exchange(false);

            Boolean result = JianglingMemberService.me.save(jianglingMember);

            if (!result) {
                throw new RuntimeException("新增不成功");
            }
        } else {
            redeem_code = jianglingMember.getMember_redeem_code();
        }

        renderSuccessJson(redeem_code);
    }

    @ActionKey("/mobile/jiangling/member/draw")
    public void draw() {
        String request_user_id = getRequest_user_id();

        JianglingMember jianglingMember = JianglingMemberService.me.find(Cnd.where(JianglingMember.USER_ID, request_user_id));



        renderSuccessJson();
    }

}