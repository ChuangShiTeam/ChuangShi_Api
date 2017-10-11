package com.nowui.chuangshi.api.minhang.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.api.minhang.service.MinhangKeyService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberKeyService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/minhang/key")
public class MinhangKeyController extends Controller {

    @ActionKey("/mobile/minhang/key/list")
    public void list() {
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        
        List<MinhangKey> resultList = MinhangKeyService.instance.appList(request_app_id);
        for (MinhangKey result : resultList) {
            result.put(MinhangKey.KEY_IMAGE_FILE, FileService.instance.getFile(result.getKey_image()));
        }
        
        List<MinhangMemberKey> member_key_list = MinhangMemberKeyService.instance.userList(request_user_id);

        if (member_key_list == null || member_key_list.size() == 0) {
            User user = UserService.instance.find(request_user_id);
            for (MinhangKey result : resultList) {
                MinhangMemberKey minhangMemberKey = new MinhangMemberKey();
                minhangMemberKey.setMember_key_id(Util.getRandomUUID());
                minhangMemberKey.setApp_id(result.getApp_id());
                minhangMemberKey.setKey_id(result.getKey_id());
                minhangMemberKey.setMember_id(user.getObject_id());
                minhangMemberKey.setUser_id(request_user_id);
                minhangMemberKey.setKey_is_activated(false);
                minhangMemberKey.setTask_quantity(result.getKey_activated_task_quantity());
                minhangMemberKey.setTask_complete_quantity(0);
                MinhangMemberKeyService.instance.save(minhangMemberKey, request_user_id);
            }
        }
        
        validateResponse(MinhangKey.KEY_ID, MinhangKey.KEY_NAME, MinhangKey.KEY_IMAGE_FILE, MinhangKey.KEY_ACTIVATED_TASK_QUANTITY, MinhangKey.KEY_DESCRIPTION, MinhangKey.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/minhang/key/find")
    public void find() {
        validateRequest(MinhangKey.KEY_ID);
        
        MinhangKey model = getModel(MinhangKey.class);
        String request_user_id = getRequest_user_id();
        
        MinhangKey minhangKey = MinhangKeyService.instance.find(model.getKey_id());

        minhangKey.put(MinhangKey.KEY_IMAGE_FILE, FileService.instance.getFile(minhangKey.getKey_image()));

        validateResponse(MinhangKey.KEY_NAME, MinhangKey.KEY_IMAGE_FILE, MinhangKey.KEY_ACTIVATED_TASK_QUANTITY, MinhangKey.KEY_SORT, MinhangKey.KEY_DESCRIPTION, MinhangKey.SYSTEM_VERSION);

        MinhangMemberKey member_key = MinhangMemberKeyService.instance.userAndKeyFind(request_user_id, model.getKey_id());
       
        validateResponse(MinhangMemberKey.MEMBER_KEY_ID, MinhangMemberKey.KEY_ID, MinhangMemberKey.TASK_QUANTITY, MinhangMemberKey.TASK_COMPLETE_QUANTITY, MinhangMemberKey.KEY_IS_ACTIVATED);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("key", minhangKey);
        result.put("member_key", member_key);
        renderSuccessJson(result);
    }

    @ActionKey("/mobile/minhang/key/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/key/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/key/delete")
    public void delete() {

        renderSuccessJson();
    }

}