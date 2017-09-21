package com.nowui.chuangshi.api.jiangling.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingLottery;
import com.nowui.chuangshi.api.jiangling.service.JianglingLotteryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/jiangling/lottery")
public class JianglingLotteryController extends Controller {

    @ActionKey("/mobile/jiangling/lottery/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/lottery/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/lottery/save")
    public void save() {
        validateRequest(JianglingLottery.LOTTERY_USER_SEX, JianglingLottery.LOTTERY_USER_MOBILE);

        JianglingLottery model = getModel(JianglingLottery.class);
        
        JianglingLottery bean = JianglingLotteryService.instance.mobileFind(model.getLottery_user_mobile());
        String user_id = null;
        if (bean != null) {
            user_id = bean.getUser_id();
        } else {
            user_id = Util.getRandomUUID();
            model.setUser_id(user_id);
            model.setLottery_number("");
            model.setLottery_time(0);
            model.setLottery_status(false);
            
            JianglingLotteryService.instance.save(model, user_id);
        }
        
        
        renderSuccessJson(user_id);
    }

    @ActionKey("/mobile/jiangling/lottery/update")
    public void update() {
        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/lottery/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/jiangling/lottery/draw")
    public void draw() {
        validateRequest(JianglingLottery.USER_ID);
        
        JianglingLottery model = getModel(JianglingLottery.class);
        String request_app_id = getRequest_app_id();
        
        String lottery_number = JianglingLotteryService.instance.draw(request_app_id, model.getUser_id());
        
        renderSuccessJson(lottery_number);
    }

}