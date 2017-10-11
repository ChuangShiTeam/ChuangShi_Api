package com.nowui.chuangshi.api.minhang.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.PathKit;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.api.minhang.service.MinhangKeyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.QRCodeUtil;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/key")
public class MinhangKeyController extends Controller {

    @ActionKey("/admin/minhang/key/list")
    public void list() {
        validateRequest(MinhangKey.KEY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangKey model = getModel(MinhangKey.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangKeyService.instance.adminCount(request_app_id, model.getKey_name());
        List<MinhangKey> resultList = MinhangKeyService.instance.adminList(request_app_id, model.getKey_name(), getM(), getN());
        for (MinhangKey result : resultList) {
            result.put(MinhangKey.KEY_IMAGE_FILE, FileService.instance.getFile(result.getKey_image()));
        }
        validateResponse(MinhangKey.KEY_ID, MinhangKey.KEY_NAME, MinhangKey.KEY_IMAGE_FILE, MinhangKey.KEY_ACTIVATED_TASK_QUANTITY, MinhangKey.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }
    
    @ActionKey("/admin/minhang/key/all/list")
    public void allList() {
    	String request_app_id = getRequest_app_id();
    	
    	List<MinhangKey> resultList = MinhangKeyService.instance.allList(request_app_id);
    	
    	validateResponse(MinhangKey.KEY_ID, MinhangKey.KEY_NAME);
    	
    	renderSuccessJson(resultList);
    }

    @ActionKey("/admin/minhang/key/find")
    public void find() {
        validateRequest(MinhangKey.KEY_ID);

        MinhangKey model = getModel(MinhangKey.class);

        MinhangKey result = MinhangKeyService.instance.find(model.getKey_id());

        result.put(MinhangKey.KEY_IMAGE_FILE, FileService.instance.getFile(result.getKey_image()));

        validateResponse(MinhangKey.KEY_NAME, MinhangKey.KEY_IMAGE_FILE, MinhangKey.KEY_ACTIVATED_TASK_QUANTITY, MinhangKey.KEY_DESCRIPTION, MinhangKey.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/key/save")
    public void save() {
        validateRequest(MinhangKey.KEY_NAME, MinhangKey.KEY_IMAGE, MinhangKey.KEY_ACTIVATED_TASK_QUANTITY, MinhangKey.KEY_DESCRIPTION);

        MinhangKey model = getModel(MinhangKey.class);
        model.setKey_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();
        
        Boolean result = MinhangKeyService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/key/update")
    public void update() {
        validateRequest(MinhangKey.KEY_ID, MinhangKey.KEY_NAME, MinhangKey.KEY_IMAGE, MinhangKey.KEY_DESCRIPTION, MinhangKey.SYSTEM_VERSION);

        MinhangKey model = getModel(MinhangKey.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangKeyService.instance.update(model, model.getKey_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/key/delete")
    public void delete() {
        validateRequest(MinhangKey.KEY_ID, MinhangKey.SYSTEM_VERSION);

        MinhangKey model = getModel(MinhangKey.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangKeyService.instance.delete(model.getKey_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}