package com.nowui.chuangshi.api.uni.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.uni.model.UniLottery;
import com.nowui.chuangshi.api.uni.service.UniLotteryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/uni/lottery")
public class UniLotteryController extends Controller {

    @ActionKey("/mobile/uni/lottery/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/lottery/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/lottery/save")
    public void save() {
        validateRequest(UniLottery.LOTTERY_USER_SEX, UniLottery.LOTTERY_USER_MOBILE);

        UniLottery model = getModel(UniLottery.class);
        
        UniLottery bean = UniLotteryService.instance.mobileFind(model.getLottery_user_mobile());
        String user_id = null;
        if (bean != null) {
            user_id = bean.getUser_id();
        } else {
            user_id = Util.getRandomUUID();
            model.setUser_id(user_id);
            model.setLottery_number("");
            model.setLottery_time(0);
            model.setLottery_status(false);
            
            UniLotteryService.instance.save(model, user_id);
        }
        
        
        renderSuccessJson(user_id);
    }

    @ActionKey("/mobile/uni/lottery/update")
    public void update() {
        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/lottery/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/uni/lottery/draw")
    public void draw() {
        validateRequest(UniLottery.USER_ID);
        
        UniLottery model = getModel(UniLottery.class);
        String request_app_id = getRequest_app_id();
        
        String lottery_number = UniLotteryService.instance.draw(request_app_id, model.getUser_id());
        
        renderSuccessJson(lottery_number);
    }

}