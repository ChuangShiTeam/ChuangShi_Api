package com.nowui.chuangshi.api.jiangling.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingMember;
import com.nowui.chuangshi.api.jiangling.model.JianglingMemberPrize;
import com.nowui.chuangshi.api.jiangling.model.JianglingPrize;
import com.nowui.chuangshi.api.jiangling.service.JianglingMemberPrizeService;
import com.nowui.chuangshi.api.jiangling.service.JianglingMemberService;
import com.nowui.chuangshi.api.jiangling.service.JianglingPrizeService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

import java.util.Date;
import java.util.List;
import java.util.Random;

@ControllerKey("/mobile/jiangling/member")
public class JianglingMemberController extends Controller {

    @ActionKey("/mobile/jiangling/member/diffent/point/update")
    public void diffentUpdate() {
        String request_user_id = getRequest_user_id();

        User user = UserService.me.findById(request_user_id);
        if (user == null) {
            throw new RuntimeException("没有权限");
        }

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

        User user = UserService.me.findById(request_user_id);
        if (user == null) {
            throw new RuntimeException("没有权限");
        }

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
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        JianglingMember jianglingMember = JianglingMemberService.me.find(Cnd.where(JianglingMember.USER_ID, request_user_id));
        if (jianglingMember == null) {
            throw new RuntimeException("没有权限");
        }
        if (!jianglingMember.getMember_diffent_point().equals(50)) {
            throw new RuntimeException("找不同的积分不够");
        }
        if (!jianglingMember.getMember_like_point().equals(50)) {
            throw new RuntimeException("集赞的积分不够");
        }

        Integer count = JianglingMemberPrizeService.me.count(Cnd.where(JianglingMemberPrize.USER_ID, request_user_id));
        if (count > 0) {
            throw new RuntimeException("每人只能抽一次奖品");
        }

        Integer total_probability = 0;
        JianglingPrize defaultJianglingPrize = null;
        List<JianglingPrize> jianglingPrizeList = JianglingPrizeService.me.list(Cnd.where(JianglingPrize.APP_ID, request_app_id));
        for (JianglingPrize jianglingPrize : jianglingPrizeList) {
            if (jianglingPrize.getPrize_is_default_winning()) {
                defaultJianglingPrize = jianglingPrize;
            }

            total_probability += jianglingPrize.getPrize_probability();
        }

        Random random = new Random();
        int number = random.nextInt(total_probability) % (total_probability + 1);
        int start = 0;
        int end = 0;
        JianglingPrize prize = null;
        for (JianglingPrize jianglingPrize : jianglingPrizeList) {
            end += jianglingPrize.getPrize_probability();

            if (number > start && number <= end) {
                prize = jianglingPrize;

                break;
            }

            start += jianglingPrize.getPrize_probability();
        }
        Boolean is_save = true;
        Integer total_count = JianglingMemberPrizeService.me.count(Cnd.where(JianglingMemberPrize.PRIZE_ID, prize.getPrize_id()));
        if (total_count >= prize.getPrize_total_quantity()) {
            is_save = false;
        }

        if (!is_save) {
            prize = defaultJianglingPrize;
        }

        JianglingMemberPrize jianglingMemberPrize = new JianglingMemberPrize();
        jianglingMemberPrize.setUser_id(request_user_id);
        jianglingMemberPrize.setPrize_id(prize.getPrize_id());
        jianglingMemberPrize.setMember_prize_draw_date(DateUtil.getDateString(new Date()));
        Boolean result = JianglingMemberPrizeService.me.save(jianglingMemberPrize);

        if (!result) {
            throw new RuntimeException("抽奖不成功");
        }

        validateResponse(JianglingPrize.PRIZE_ID, JianglingPrize.PRIZE_NAME);

        renderSuccessJson(prize);
    }

}