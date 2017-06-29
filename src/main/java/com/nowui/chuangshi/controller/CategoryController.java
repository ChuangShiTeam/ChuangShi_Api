package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Category;
import com.nowui.chuangshi.service.CategoryService;
import com.nowui.chuangshi.service.MenuApiService;
import com.nowui.chuangshi.type.CategoryType;
import com.nowui.chuangshi.util.Util;

import java.util.List;
import java.util.Map;

public class CategoryController extends Controller {

    private final CategoryService categoryService = new CategoryService();
    private final MenuApiService menuApiService = new MenuApiService();

    @ActionKey(Url.CATEGORY_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Category> resultList = categoryService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN(), request_app_id, request_http_id, request_user_id);

        for (Category result : resultList) {
            result.keep(Category.CATEGORY_ID, Category.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.CATEGORY_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(category.getApp_id());
        authenticateSystem_create_user_id(category.getSystem_create_user_id());

        category.keep(Category.CATEGORY_ID, Category.SYSTEM_VERSION);

        renderSuccessJson(category);
    }

    @ActionKey(Url.CATEGORY_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Category.PARENT_ID, Category.CATEGORY_NAME, Category.CATEGORY_IMAGE, Category.CATEGORY_KEY, Category.CATEGORY_VALUE, Category.CATEGORY_SORT, Category.CATEGORY_TYPE);

        Category model = getModel(Category.class);
        String category_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = categoryService.save(category_id, request_app_id, model.getParent_id(), model.getCategory_name(), model.getCategory_image(), model.getCategory_key(), model.getCategory_value(), model.getCategory_sort(), model.getCategory_type(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CATEGORY_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID, Category.PARENT_ID, Category.CATEGORY_NAME, Category.CATEGORY_IMAGE, Category.CATEGORY_KEY, Category.CATEGORY_VALUE, Category.CATEGORY_PATH, Category.CATEGORY_SORT, Category.CATEGORY_TYPE, Category.SYSTEM_VERSION);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(category.getApp_id());
        authenticateSystem_create_user_id(category.getSystem_create_user_id());

        Boolean result = categoryService.updateValidateSystem_version(model.getCategory_id(), model.getParent_id(), model.getCategory_name(), model.getCategory_image(), model.getCategory_key(), model.getCategory_value(), model.getCategory_sort(), model.getCategory_type(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CATEGORY_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID, Category.SYSTEM_VERSION);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(category.getApp_id());
        authenticateSystem_create_user_id(category.getSystem_create_user_id());

        Boolean result = categoryService.deleteByCategory_idAndSystem_update_user_idValidateSystem_version(model.getCategory_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        if (result) {
            if (category.getCategory_type().equals(CategoryType.MENU.getKey())) {
                menuApiService.deleteByMenu_id(category.getCategory_id(), request_user_id, request_app_id, request_http_id, request_user_id);
            }
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.CATEGORY_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Category.CATEGORY_NAME, Category.CATEGORY_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = categoryService.countByApp_idAndParent_idOrLikeCategory_nameOrCategory_type(request_app_id, Constant.PARENT_ID, model.getCategory_name(), model.getCategory_type(), request_app_id, request_http_id, request_user_id);
        List<Category> resultList = categoryService.listByApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(request_app_id, Constant.PARENT_ID, model.getCategory_name(), model.getCategory_type(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (Category result : resultList) {
            result.keep(Category.CATEGORY_ID, Category.CATEGORY_NAME, Category.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.CATEGORY_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(category.getApp_id());

        category.keep(Category.CATEGORY_ID, Category.PARENT_ID, Category.CATEGORY_NAME, Category.CATEGORY_IMAGE, Category.CATEGORY_KEY, Category.CATEGORY_VALUE, Category.CATEGORY_SORT, Category.CATEGORY_TYPE, Category.SYSTEM_VERSION);

        renderSuccessJson(category);
    }

    @ActionKey(Url.CATEGORY_ADMIN_CHILDREN_FIND)
    public void adminChildrenFind() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);

        category.keep(Category.CATEGORY_ID, Category.APP_ID, Category.PARENT_ID, Category.CATEGORY_NAME, Category.CATEGORY_TYPE);

        List<Map<String, Object>> childrenLst = categoryService.treeByParent_id(category.getCategory_id(), request_app_id, request_http_id, request_user_id);
        category.put(Constant.CHILDREN, childrenLst);

        renderSuccessJson(category);
    }

    @ActionKey(Url.CATEGORY_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.CATEGORY_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID, Category.PARENT_ID, Category.CATEGORY_NAME, Category.CATEGORY_IMAGE, Category.CATEGORY_KEY, Category.CATEGORY_VALUE, Category.CATEGORY_SORT, Category.CATEGORY_TYPE, Category.SYSTEM_VERSION);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(category.getApp_id());

        Boolean result = categoryService.updateValidateSystem_version(model.getCategory_id(), model.getParent_id(), model.getCategory_name(), model.getCategory_image(), model.getCategory_key(), model.getCategory_value(), model.getCategory_sort(), model.getCategory_type(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CATEGORY_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID, Category.SYSTEM_VERSION);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);
        authenticateApp_id(category.getApp_id());

        Boolean result = categoryService.deleteByCategory_idAndSystem_update_user_idValidateSystem_version(model.getCategory_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        if (result) {
            if (category.getCategory_type().equals(CategoryType.MENU.getKey())) {
                menuApiService.deleteByMenu_id(category.getCategory_id(), request_user_id, request_app_id, request_http_id, request_user_id);
            }
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.CATEGORY_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Category.APP_ID, Category.CATEGORY_NAME, Category.CATEGORY_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = categoryService.countByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_type(model.getApp_id(), Constant.PARENT_ID, model.getCategory_name(), model.getCategory_type(), request_app_id, request_http_id, request_user_id);
        List<Category> resultList = categoryService.listByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(model.getApp_id(), Constant.PARENT_ID, model.getCategory_name(), model.getCategory_type(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (Category result : resultList) {
            result.keep(Category.CATEGORY_ID, Category.CATEGORY_NAME, Category.CATEGORY_KEY, Category.CATEGORY_VALUE, Category.CATEGORY_SORT, Category.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.CATEGORY_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);

        category.keep(Category.CATEGORY_ID, Category.APP_ID, Category.PARENT_ID, Category.CATEGORY_NAME, Category.CATEGORY_IMAGE, Category.CATEGORY_KEY, Category.CATEGORY_VALUE, Category.CATEGORY_SORT, Category.CATEGORY_TYPE, Category.SYSTEM_VERSION);

        renderSuccessJson(category);
    }

    @ActionKey(Url.CATEGORY_SYSTEM_CHILDREN_FIND)
    public void systemChildrenFind() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);

        category.keep(Category.CATEGORY_ID, Category.APP_ID, Category.PARENT_ID, Category.CATEGORY_NAME, Category.CATEGORY_TYPE);

        List<Map<String, Object>> childrenLst = categoryService.treeByParent_id(category.getCategory_id(), request_app_id, request_http_id, request_user_id);
        category.put(Constant.CHILDREN, childrenLst);

        renderSuccessJson(category);
    }

    @ActionKey(Url.CATEGORY_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Category.APP_ID, Category.PARENT_ID, Category.CATEGORY_NAME, Category.CATEGORY_IMAGE, Category.CATEGORY_KEY, Category.CATEGORY_VALUE, Category.CATEGORY_SORT, Category.CATEGORY_TYPE);

        authenticateRequest_app_idAndRequest_user_id();

        Category model = getModel(Category.class);
        String category_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = categoryService.save(category_id, model.getApp_id(), model.getParent_id(), model.getCategory_name(), model.getCategory_image(), model.getCategory_key(), model.getCategory_value(), model.getCategory_sort(), model.getCategory_type(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CATEGORY_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Category.APP_ID, Category.PARENT_ID, Category.CATEGORY_NAME, Category.CATEGORY_IMAGE, Category.CATEGORY_KEY, Category.CATEGORY_VALUE, Category.CATEGORY_SORT, Category.CATEGORY_TYPE, Category.SYSTEM_VERSION);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = categoryService.updateValidateSystem_version(model.getCategory_id(), model.getParent_id(), model.getCategory_name(), model.getCategory_image(), model.getCategory_key(), model.getCategory_value(), model.getCategory_sort(), model.getCategory_type(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CATEGORY_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Category.CATEGORY_ID, Category.SYSTEM_VERSION);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getCategory_id(), request_app_id, request_http_id, request_user_id);

        Boolean result = categoryService.deleteByCategory_idAndSystem_update_user_idValidateSystem_version(model.getCategory_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        if (result) {
            if (category.getCategory_type().equals(CategoryType.MENU.getKey())) {
                menuApiService.deleteByMenu_id(category.getCategory_id(), request_user_id, request_app_id, request_http_id, request_user_id);
            }
        }

        renderSuccessJson(result);
    }

}