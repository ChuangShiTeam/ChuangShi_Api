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

    @ActionKey("/mobile/infiniti/member/draw")
    public void draw() {
        validateRequest(InfinitiMember.PRIZE_ID);

        InfinitiPrize model = getModel(InfinitiPrize.class);
        String request_app_id = getRequest_app_id();

        Boolean is_prize = false;
        Boolean is_over = false;

        Random random = new Random();
        int number = random.nextInt(3);
        if (number == 0) {
            is_prize = true;
        }

        Map<String, Object> result = new HashMap<String, Object>();
        if (is_prize) {
            InfinitiPrize infinitiPrize = InfinitiPrizeService.instance.find(model.getPrize_id());

            Integer count = InfinitiMemberService.instance.prizeCount(model.getPrize_id());
            if (count >= infinitiPrize.getPrize_total_quantity()) {
                is_prize = false;
                is_over = true;
            } else {
                String member_id = Util.getRandomUUID();
                String member_name = "";
                String member_mobile = "";
                String member_address = "";
                String prize_id = infinitiPrize.getPrize_id();
                String member_redeem_code = Util.getRandomNumber(6);
                Boolean is_exit = true;
                while (is_exit) {
                    count = InfinitiMemberService.instance.redeemCodeCount(member_redeem_code);

                    if (count > 0) {
                        member_redeem_code = Util.getRandomStringByLength(6);
                    } else {
                        is_exit = false;
                    }

                }
                Boolean member_redeem_code_is_exchange = false;
                Boolean success = InfinitiMemberService.instance.save(member_id, request_app_id, member_name, member_mobile, member_address, prize_id, member_redeem_code, member_redeem_code_is_exchange);

                if (success) {
                    result.put(InfinitiPrize.PRIZE_ID, infinitiPrize.getPrize_id());
                    result.put(InfinitiPrize.PRIZE_NAME, infinitiPrize.getPrize_name());
                    result.put(InfinitiMember.MEMBER_REDEEM_CODE, member_redeem_code);
                } else {
                    is_prize = false;
                }
            }
        }
        result.put("is_prize", is_prize);
        result.put("is_over", is_over);

        renderSuccessJson(result);
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