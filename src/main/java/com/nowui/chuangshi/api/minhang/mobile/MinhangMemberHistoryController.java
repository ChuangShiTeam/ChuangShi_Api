package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

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
        
        String request_user_id = getRequest_user_id();
        
        List<MinhangMemberHistory> minhangMemberHistoryList = MinhangMemberHistoryService.instance.userAndSaveList(request_user_id);
        
        validateResponse(MinhangMemberHistory.MEMBER_HISTORY_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME);
        
        renderSuccessJson(minhangMemberHistoryList);
    }

    /**
     * 用户纪念册信息
     */
    @ActionKey("/mobile/minhang/member/history/find")
    public void find() {
        validateRequest(MinhangMemberHistory.MEMBER_HISTORY_ID);
        
        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        String request_user_id = getRequest_user_id();
        
        MinhangMemberHistory bean = MinhangMemberHistoryService.instance.find(model.getMember_history_id());
        if (bean == null) {
            throw new RuntimeException("纪念册不存在");
        }
        //查询纪念册对应的任务记录
        
        renderSuccessJson();
    }

    /**
     * 保存用户纪念册
     */
    @ActionKey("/mobile/minhang/member/history/save")
    public void save() {
        validateRequest(MinhangMemberHistory.MEMBER_HISTORY_ID);
        
        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        String request_user_id = getRequest_user_id();
        
        MinhangMemberHistory bean = MinhangMemberHistoryService.instance.find(model.getMember_history_id());
        
        if (bean.getIs_save_history()) {
            throw new RuntimeException("纪念册已生成");
        }
        
        bean.setIs_save_history(true);
        
        Boolean result = MinhangMemberHistoryService.instance.update(bean, bean.getMember_history_id(), request_user_id, bean.getSystem_version());
        
        renderSuccessJson(result);
    }

    /**
     * 用户纪念册名称更新
     */
    @ActionKey("/mobile/minhang/member/history/name/update")
    public void nameUpdate() {
        validateRequest(MinhangMemberHistory.MEMBER_HISTORY_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME);
        
        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        String request_user_id = getRequest_user_id();
        
        MinhangMemberHistory bean = MinhangMemberHistoryService.instance.find(model.getMember_history_id());
        
        if (bean == null) {
            throw new RuntimeException("纪念册不存在");
        }
        bean.setMember_history_name(model.getMember_history_name());
        
        Boolean result = MinhangMemberHistoryService.instance.update(bean, bean.getMember_history_id(), request_user_id, bean.getSystem_version());
       
        renderSuccessJson(result);
    }

    /**
     * 重启寻钥之旅
     */
    @ActionKey("/mobile/minhang/member/history/restart")
    public void restart() {
        
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        //判断用户上一次寻钥之旅是否生成纪念册
        MinhangMemberHistory member_history = MinhangMemberHistoryService.instance.userLatestFind(request_user_id);

        if (member_history == null) {
            throw new RuntimeException("还没有开启寻钥之旅");
        }
        
        if (!member_history.getIs_save_history()) {
            throw new RuntimeException("请先保存纪念册");
        }
        
        Boolean result = MinhangMemberHistoryService.instance.restart(request_user_id, request_app_id);
        

        renderSuccessJson(result);
    }
    
}