package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.api.minhang.service.MinhangKeyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/key")
public class MinhangKeyController extends Controller {

    @ActionKey("/mobile/minhang/key/list")
    public void list() {
        String request_app_id = getRequest_app_id();
        
        List<MinhangKey> resultList = MinhangKeyService.instance.appList(request_app_id);
        for (MinhangKey result : resultList) {
            result.put(MinhangKey.KEY_IMAGE_FILE, FileService.instance.getFile(result.getKey_image()));
        }
        
        validateResponse(MinhangKey.KEY_ID, MinhangKey.KEY_NAME, MinhangKey.KEY_IMAGE_FILE, MinhangKey.KEY_ACTIVATED_TASK_QUANTITY, MinhangKey.KEY_DESCRIPTION, MinhangKey.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/minhang/key/find")
    public void find() {

        renderSuccessJson();
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