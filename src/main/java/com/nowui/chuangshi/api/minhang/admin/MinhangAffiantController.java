package com.nowui.chuangshi.api.minhang.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangAffiant;
import com.nowui.chuangshi.api.minhang.model.MinhangPoster;
import com.nowui.chuangshi.api.minhang.service.MinhangAffiantService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/minhang/affiant")
public class MinhangAffiantController extends Controller {

    @ActionKey("/admin/minhang/affiant/list")
    public void list() {
        validateRequest(MinhangAffiant.AFFIANT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangAffiant model = getModel(MinhangAffiant.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangAffiantService.instance.adminCount(request_app_id, model.getAffiant_name());
        List<MinhangAffiant> resultList = MinhangAffiantService.instance.adminList(request_app_id, model.getAffiant_name(), getM(), getN());

        for (MinhangAffiant minhangAffiant : resultList) {
            minhangAffiant.put(MinhangAffiant.AFFIANT_AVATAR_FILE, FileService.instance.getFile(minhangAffiant.getAffiant_avatar()));
        }
        
        validateResponse(MinhangAffiant.AFFIANT_ID, MinhangAffiant.AFFIANT_NAME, MinhangAffiant.AFFIANT_AVATAR, MinhangAffiant.AFFIANT_SEX, MinhangAffiant.AFFIANT_BIRTHDAY, MinhangAffiant.AFFIANT_SORT, MinhangAffiant.SYSTEM_VERSION, MinhangAffiant.AFFIANT_AVATAR_FILE);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/affiant/find")
    public void find() {
        validateRequest(MinhangAffiant.AFFIANT_ID);

        MinhangAffiant model = getModel(MinhangAffiant.class);

        MinhangAffiant result = MinhangAffiantService.instance.find(model.getAffiant_id());
        
        result.put(MinhangAffiant.AFFIANT_AVATAR_FILE, FileService.instance.getFile(result.getAffiant_avatar()));

        validateResponse(MinhangAffiant.AFFIANT_NAME, MinhangAffiant.AFFIANT_AVATAR, MinhangAffiant.AFFIANT_AVATAR_FILE, MinhangAffiant.AFFIANT_SEX, MinhangAffiant.AFFIANT_BIRTHDAY, MinhangAffiant.AFFIANT_SUMMARY, MinhangAffiant.AFFIANT_DESCRIPTION, MinhangAffiant.AFFIANT_SORT, MinhangAffiant.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/affiant/save")
    public void save() {
        validateRequest(MinhangAffiant.AFFIANT_NAME, MinhangAffiant.AFFIANT_AVATAR, MinhangAffiant.AFFIANT_SEX, MinhangAffiant.AFFIANT_BIRTHDAY, MinhangAffiant.AFFIANT_SUMMARY, MinhangAffiant.AFFIANT_DESCRIPTION, MinhangAffiant.AFFIANT_SORT);

        MinhangAffiant model = getModel(MinhangAffiant.class);
        model.setAffiant_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangAffiantService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/affiant/update")
    public void update() {
        validateRequest(MinhangAffiant.AFFIANT_ID, MinhangAffiant.AFFIANT_NAME, MinhangAffiant.AFFIANT_AVATAR, MinhangAffiant.AFFIANT_SEX, MinhangAffiant.AFFIANT_BIRTHDAY, MinhangAffiant.AFFIANT_SUMMARY, MinhangAffiant.AFFIANT_DESCRIPTION, MinhangAffiant.AFFIANT_SORT, MinhangAffiant.SYSTEM_VERSION);

        MinhangAffiant model = getModel(MinhangAffiant.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangAffiantService.instance.update(model, model.getAffiant_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/affiant/delete")
    public void delete() {
        validateRequest(MinhangAffiant.AFFIANT_ID, MinhangAffiant.SYSTEM_VERSION);

        MinhangAffiant model = getModel(MinhangAffiant.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangAffiantService.instance.delete(model.getAffiant_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}