package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberItinerary;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberItineraryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member/itinerary")
public class MinhangMemberItineraryController extends Controller {

    @ActionKey("/mobile/minhang/member/itinerary/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/itinerary/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/itinerary/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/itinerary/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/itinerary/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    /**
     * 重启寻钥之旅
     */
    @ActionKey("/mobile/minhang/member/itinerary/restart")
    public void restart() {
        
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        //判断用户是否开启过寻钥之旅
        MinhangMemberItinerary member_itinerary = MinhangMemberItineraryService.instance.userLatestFind(request_user_id);

        if (member_itinerary == null) {
            throw new RuntimeException("还没有开启寻钥之旅");
        }
        
        Boolean result = MinhangMemberItineraryService.instance.restart(request_user_id, request_app_id);
        

        renderSuccessJson(result);
    }

}