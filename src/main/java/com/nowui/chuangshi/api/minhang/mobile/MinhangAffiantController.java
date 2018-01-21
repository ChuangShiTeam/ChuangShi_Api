package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangAffiant;
import com.nowui.chuangshi.api.minhang.service.MinhangAffiantService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/affiant")
public class MinhangAffiantController extends Controller {

    @ActionKey("/mobile/minhang/affiant/list")
    public void list() {
        String request_app_id = getRequest_app_id();
        
        List<MinhangAffiant> resultList = MinhangAffiantService.instance.appList(request_app_id);
        
        for (MinhangAffiant minhangAffiant : resultList) {
            minhangAffiant.put(MinhangAffiant.AFFIANT_AVATAR_FILE, FileService.instance.getFile(minhangAffiant.getAffiant_avatar()));
        }
        
        validateResponse(MinhangAffiant.AFFIANT_ID, MinhangAffiant.AFFIANT_NAME, MinhangAffiant.AFFIANT_AVATAR_FILE);
        
        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/minhang/affiant/find")
    public void find() {
        validateRequest(MinhangAffiant.AFFIANT_ID);

        MinhangAffiant model = getModel(MinhangAffiant.class);

        MinhangAffiant result = MinhangAffiantService.instance.find(model.getAffiant_id());
        
        result.put(MinhangAffiant.AFFIANT_AVATAR_FILE, FileService.instance.getFile(result.getAffiant_avatar()));

        validateResponse(MinhangAffiant.AFFIANT_NAME, MinhangAffiant.AFFIANT_AVATAR, MinhangAffiant.AFFIANT_AVATAR_FILE, MinhangAffiant.AFFIANT_SEX, MinhangAffiant.AFFIANT_BIRTHDAY, MinhangAffiant.AFFIANT_SUMMARY, MinhangAffiant.AFFIANT_DESCRIPTION, MinhangAffiant.AFFIANT_SORT, MinhangAffiant.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/minhang/affiant/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/affiant/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/affiant/delete")
    public void delete() {

        renderSuccessJson();
    }

}