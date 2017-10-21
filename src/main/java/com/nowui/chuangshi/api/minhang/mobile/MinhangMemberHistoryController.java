package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberHistory;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberTask;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberHistoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member/history")
public class MinhangMemberHistoryController extends Controller {

    /**
     * 用户已保存纪念册列表
     */
    @ActionKey("/mobile/minhang/member/history/list")
    public void list() {

        renderSuccessJson();
    }

    /**
     * 用户纪念册信息
     */
    @ActionKey("/mobile/minhang/member/history/find")
    public void find() {

        renderSuccessJson();
    }

    /**
     * 保存用户纪念册
     */
    @ActionKey("/mobile/minhang/member/history/save")
    public void save() {

        renderSuccessJson();
    }

    /**
     * 用户纪念册名称更新
     */
    @ActionKey("/mobile/minhang/member/history/name/update")
    public void nameUpdate() {
        validateRequest(MinhangMemberHistory.MEMBER_HISTORY_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME);
        
        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        
        MinhangMemberHistory bean = MinhangMemberHistoryService.instance.find(model.getMember_history_id());
        
        renderSuccessJson();
    }

    /**
     * 重启寻钥之旅
     */
    @ActionKey("/mobile/minhang/member/history/restart")
    public void restart() {

        renderSuccessJson();
    }
    
}