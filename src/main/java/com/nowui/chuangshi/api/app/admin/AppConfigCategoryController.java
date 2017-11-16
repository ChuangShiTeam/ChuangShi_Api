package com.nowui.chuangshi.api.app.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.app.model.AppConfigCategory;
import com.nowui.chuangshi.api.app.service.AppConfigCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/app/config/category")
public class AppConfigCategoryController extends Controller {

    @ActionKey("/admin/app/config/category/list")
    public void list() {
        validateRequest(AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        AppConfigCategory model = getModel(AppConfigCategory.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = AppConfigCategoryService.instance.adminCount(request_app_id, model.getConfig_category_name(), model.getConfig_category_code());
        List<AppConfigCategory> resultList = AppConfigCategoryService.instance.adminList(request_app_id, model.getConfig_category_name(), model.getConfig_category_code(), getM(), getN());

        validateResponse(AppConfigCategory.CONFIG_CATEGORY_ID, AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE, AppConfigCategory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }
    
    @ActionKey("/admin/app/config/category/all/list")
    public void allList() {
        String request_app_id = getRequest_app_id();

        List<AppConfigCategory> resultList = AppConfigCategoryService.instance.appList(request_app_id);

        validateResponse(AppConfigCategory.CONFIG_CATEGORY_ID, AppConfigCategory.CONFIG_CATEGORY_NAME);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/app/config/category/find")
    public void find() {
        validateRequest(AppConfigCategory.CONFIG_CATEGORY_ID);

        AppConfigCategory model = getModel(AppConfigCategory.class);

        AppConfigCategory result = AppConfigCategoryService.instance.find(model.getConfig_category_id());

        validateResponse(AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE, AppConfigCategory.CONFIG_CATEGORY_DESCRIPTION, AppConfigCategory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/app/config/category/save")
    public void save() {
        validateRequest(AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE, AppConfigCategory.CONFIG_CATEGORY_DESCRIPTION);

        AppConfigCategory model = getModel(AppConfigCategory.class);
        model.setConfig_category_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = AppConfigCategoryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/app/config/category/update")
    public void update() {
        validateRequest(AppConfigCategory.CONFIG_CATEGORY_ID, AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE, AppConfigCategory.CONFIG_CATEGORY_DESCRIPTION, AppConfigCategory.SYSTEM_VERSION);

        AppConfigCategory model = getModel(AppConfigCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AppConfigCategoryService.instance.update(model, model.getConfig_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/app/config/category/delete")
    public void delete() {
        validateRequest(AppConfigCategory.CONFIG_CATEGORY_ID, AppConfigCategory.SYSTEM_VERSION);

        AppConfigCategory model = getModel(AppConfigCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AppConfigCategoryService.instance.delete(model.getConfig_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}