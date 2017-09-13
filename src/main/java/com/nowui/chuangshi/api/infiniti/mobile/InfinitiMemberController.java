package com.nowui.chuangshi.api.infiniti.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.infiniti.model.InfinitiMember;
import com.nowui.chuangshi.api.infiniti.model.InfinitiPrize;
import com.nowui.chuangshi.api.infiniti.service.InfinitiMemberService;
import com.nowui.chuangshi.api.infiniti.service.InfinitiPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ControllerKey("/mobile/infiniti/member")
public class InfinitiMemberController extends Controller {

    @ActionKey("/mobile/infiniti/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/infiniti/member/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/infiniti/member/1/draw")
    public void draw1() {
        String request_app_id = getRequest_app_id();

        Boolean is_prize = false;

        Random random = new Random();
        int number = random.nextInt(3);
        if (number == 0) {
            is_prize = true;
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("is_prize", is_prize);

        renderSuccessJson();
    }

    @ActionKey("/mobile/infiniti/member/draw")
    public void draw() {
        String request_app_id = getRequest_app_id();

        Integer total_probability = 0;
        InfinitiPrize defaultInfinitiPrize = null;
        List<InfinitiPrize> infinitiPrizeList = InfinitiPrizeService.instance.appList(request_app_id);
        for (InfinitiPrize jianglingPrize : infinitiPrizeList) {
            if (jianglingPrize.getPrize_is_default_winning()) {
                defaultInfinitiPrize = jianglingPrize;
            }

            total_probability += jianglingPrize.getPrize_probability();
        }

        Random random = new Random();
        int number = random.nextInt(total_probability) + 1;
        int start = 0;
        int end = 0;
        InfinitiPrize prize = null;
        for (InfinitiPrize jianglingPrize : infinitiPrizeList) {
            end += jianglingPrize.getPrize_probability();

            if (number >= start && number <= end) {
                prize = jianglingPrize;

                break;
            }

            start += jianglingPrize.getPrize_probability();
        }
        Boolean is_save = true;
        Integer total_count = InfinitiMemberService.instance.prizeCount(prize.getPrize_id());

        if (prize == null) {
            prize = defaultInfinitiPrize;
        }
        if (total_count >= prize.getPrize_total_quantity()) {
            is_save = false;
        }

        if (!is_save) {
            prize = defaultInfinitiPrize;
        }


        String member_id = Util.getRandomUUID();
        String member_name = "";
        String member_mobile = "";
        String member_address = "";
        String prize_id = prize.getPrize_id();
        String member_redeem_code = Util.getRandomNumber(6);
        Boolean is_exit = true;
        while (is_exit) {
            Integer count = InfinitiMemberService.instance.redeemCodeCount(member_redeem_code);

            if (count > 0) {
                member_redeem_code = Util.getRandomStringByLength(6);
            } else {
                is_exit = false;
            }

        }
        Boolean member_redeem_code_is_exchange = false;
        Boolean result = InfinitiMemberService.instance.save(member_id, request_app_id, member_name, member_mobile, member_address, prize_id, member_redeem_code, member_redeem_code_is_exchange);

        if (!result) {
            throw new RuntimeException("抽奖不成功");
        }

        validateResponse(InfinitiPrize.PRIZE_ID, InfinitiPrize.PRIZE_NAME, member_redeem_code);

        renderSuccessJson(prize);
    }

    @ActionKey("/mobile/infiniti/member/update")
    public void update() {
        validateRequest(InfinitiMember.MEMBER_NAME, InfinitiMember.MEMBER_MOBILE, InfinitiMember.MEMBER_ADDRESS, InfinitiMember.MEMBER_REDEEM_CODE);

        InfinitiMember model = getModel(InfinitiMember.class);
        String member_name = model.getMember_name();
        String member_mobile = model.getMember_mobile();
        String member_address = model.getMember_address();
        String member_redeem_code = model.getMember_redeem_code();

        Integer count = InfinitiMemberService.instance.redeemCodeCount(member_redeem_code);

        if (count != 1) {
            throw new RuntimeException("该兑换码无效");
        }

        Boolean result = InfinitiMemberService.instance.update(member_name, member_mobile, member_address, member_redeem_code);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/infiniti/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}