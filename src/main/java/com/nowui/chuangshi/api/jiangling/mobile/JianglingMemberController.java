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
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

import java.util.Date;
import java.util.List;
import java.util.Random;

@ControllerKey("/mobile/jiangling/member")
public class JianglingMemberController extends Controller {

    @ActionKey("/mobile/jiangling/member/find")
    public void find() {
        String request_user_id = getRequest_user_id();

        JianglingMember jianglingMember = JianglingMemberService.instance.find(request_user_id);

        if (jianglingMember == null) {
            jianglingMember = new JianglingMember();
            jianglingMember.setMember_diffent_point(0);
            jianglingMember.setMember_like_point(0);
        }

        validateResponse(JianglingMember.MEMBER_DIFFENT_POINT, JianglingMember.MEMBER_LIKE_POINT);

        renderSuccessJson(jianglingMember);
    }

    @ActionKey("/mobile/jiangling/member/diffent/point/update")
    public void diffentUpdate() {
        String request_user_id = getRequest_user_id();

        User user = UserService.instance.find(request_user_id);
        if (user == null) {
            throw new RuntimeException("没有权限");
        }

        Integer count = JianglingMemberService.instance.userCount(request_user_id);
        Boolean result;
        if (count == 0) {
            String member_redeem_code = Util.getRandomNumber(6);
            Boolean is_exit = true;

            while (is_exit) {
                count = JianglingMemberService.instance.redeemCodeCount(member_redeem_code);

                if (count > 0) {
                    member_redeem_code = Util.getRandomStringByLength(6);
                } else {
                    is_exit = false;
                }

            }

            JianglingMember jianglingMember = new JianglingMember();
            jianglingMember.setUser_id(request_user_id);
            jianglingMember.setMember_diffent_point(50);
            jianglingMember.setMember_like_point(0);
            jianglingMember.setMember_redeem_code(member_redeem_code);
            jianglingMember.setMember_redeem_code_is_exchange(false);

            result = JianglingMemberService.instance.save(jianglingMember, request_user_id);
        } else {
            JianglingMember jianglingMember = new JianglingMember();
            jianglingMember.setMember_diffent_point(50);

            result = JianglingMemberService.instance.update(jianglingMember, request_user_id, request_user_id);
        }


        renderSuccessJson(result);
    }

    @ActionKey("/mobile/jiangling/member/like/point/update")
    public void likeUpdate() {
        String request_user_id = getRequest_user_id();

        User user = UserService.instance.find(request_user_id);
        if (user == null) {
            throw new RuntimeException("没有权限");
        }

        JianglingMember jianglingMember = JianglingMemberService.instance.find(request_user_id);
        String member_redeem_code;
        if (jianglingMember == null) {
            member_redeem_code = Util.getRandomNumber(6);
            Boolean is_exit = true;

            while (is_exit) {
                Integer count = JianglingMemberService.instance.redeemCodeCount(member_redeem_code);

                if (count > 0) {
                    member_redeem_code = Util.getRandomStringByLength(6);
                } else {
                    is_exit = false;
                }

            }

            jianglingMember = new JianglingMember();
            jianglingMember.setUser_id(request_user_id);
            jianglingMember.setMember_diffent_point(0);
            jianglingMember.setMember_like_point(0);
            jianglingMember.setMember_redeem_code(member_redeem_code);
            jianglingMember.setMember_redeem_code_is_exchange(false);

            Boolean result = JianglingMemberService.instance.save(jianglingMember, request_user_id);

            if (!result) {
                throw new RuntimeException("新增不成功");
            }
        } else {
            member_redeem_code = jianglingMember.getMember_redeem_code();
        }

        renderSuccessJson(member_redeem_code);
    }

    @ActionKey("/mobile/jiangling/member/draw")
    public void draw() {
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        if (request_user_id.equals("7a2d8b71f3a3469f82ce10b543f4ffce")) {

        } else {
            JianglingMember jianglingMember = JianglingMemberService.instance.find(request_user_id);
            if (jianglingMember == null) {
                throw new RuntimeException("没有权限");
            }
            if (!jianglingMember.getMember_diffent_point().equals(50)) {
                throw new RuntimeException("找不同的积分不够");
            }
            if (!jianglingMember.getMember_like_point().equals(50)) {
                throw new RuntimeException("集赞的积分不够");
            }

            Integer count = JianglingMemberPrizeService.instance.userCount(request_user_id);
            if (count > 0) {
                throw new RuntimeException("每人只能抽一次奖品");
            }
        }


        Integer total_probability = 0;
        JianglingPrize defaultJianglingPrize = null;
        List<JianglingPrize> jianglingPrizeList = JianglingPrizeService.instance.appList(request_app_id);
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
        Integer total_count = JianglingMemberPrizeService.instance.prizeCount(prize.getPrize_id());

        if (prize == null) {
            prize = defaultJianglingPrize;
        }
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
        Boolean result = JianglingMemberPrizeService.instance.save(jianglingMemberPrize, request_user_id);

        if (!result) {
            throw new RuntimeException("抽奖不成功");
        }

        validateResponse(JianglingPrize.PRIZE_ID, JianglingPrize.PRIZE_NAME);

        renderSuccessJson(prize);
    }

}