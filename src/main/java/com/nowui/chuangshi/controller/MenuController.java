package com.nowui.chuangshi.controller;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Api;
import com.nowui.chuangshi.model.Category;
import com.nowui.chuangshi.model.MenuApi;
import com.nowui.chuangshi.service.ApiService;
import com.nowui.chuangshi.service.CategoryService;
import com.nowui.chuangshi.service.MenuApiService;
import com.nowui.chuangshi.type.CategoryType;

import java.util.List;

public class MenuController extends Controller {

    private final CategoryService categoryService = new CategoryService();
    private final MenuApiService menuApiService = new MenuApiService();
    private final ApiService apiService = new ApiService();

    @ActionKey(Url.MENU_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Category.CATEGORY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = categoryService.countByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(request_app_id, Constant.PARENT_ID, model.getCategory_name(), CategoryType.MENU.getKey(), request_app_id, request_http_id, request_user_id);
        List<Category> resultList = categoryService.listByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(request_app_id, Constant.PARENT_ID, model.getCategory_name(), CategoryType.MENU.getKey(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        setResultList(resultList, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MENU_API_ADMIN_LIST)
    public void menuApiAdminList() {
        validateRequest_app_id();
        validate(MenuApi.MENU_ID);

        MenuApi model = getModel(MenuApi.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Category menu = categoryService.findByCategory_id(model.getMenu_id(), request_app_id, request_http_id, request_user_id);

        List<Api> resultList = apiService.listNotInMenuByApp_id(menu.getApp_id(),request_app_id, request_http_id, request_user_id);

        for (Api result : resultList) {
            result.keep(Api.API_ID, Api.API_NAME);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MENU_API_ADMIN_SAVE)
    public void menuApiAdminSave() {
        validateRequest_app_id();
        validate(MenuApi.API_ID, MenuApi.MENU_ID, MenuApi.API_ID);

        MenuApi model = getModel(MenuApi.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getMenu_id(), request_app_id, request_http_id, request_user_id);

        Integer count = menuApiService.countByApp_idAndApi_id(category.getApp_id(), model.getApi_id(), request_app_id, request_http_id, request_user_id);

        if (count > 0) {
            throw new RuntimeException("API重复啦");
        }

        Boolean result = menuApiService.save(category.getApp_id(), model.getMenu_id(), model.getApi_id(), model.getMenu_api_sort(), request_user_id, request_app_id, request_http_id, request_user_id);

        if (!result) {
            throw new RuntimeException("保存不成功");
        }

        renderSuccessJson();
    }

    @ActionKey(Url.MENU_API_ADMIN_DELETE)
    public void menuApiAdminDelete() {
        validateRequest_app_id();
        validate(MenuApi.MENU_ID, MenuApi.API_ID, MenuApi.SYSTEM_VERSION);

        MenuApi model = getModel(MenuApi.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = menuApiService.deleteByMenu_idAndApi_id(model.getMenu_id(), model.getApi_id(), request_user_id, request_app_id, request_http_id, request_user_id);

        if (!result) {
            throw new RuntimeException("删除不成功");
        }

        renderSuccessJson();
    }

    @ActionKey(Url.MENU_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Category.APP_ID, Category.CATEGORY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Category model = getModel(Category.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = categoryService.countByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(model.getApp_id(), Constant.PARENT_ID, model.getCategory_name(), CategoryType.MENU.getKey(), request_app_id, request_http_id, request_user_id);
        List<Category> resultList = categoryService.listByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(model.getApp_id(), Constant.PARENT_ID, model.getCategory_name(), CategoryType.MENU.getKey(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        setResultList(resultList, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MENU_API_SYSTEM_LIST)
    public void menuApiSystemList() {
        validateRequest_app_id();
        validate(MenuApi.MENU_ID);

        MenuApi model = getModel(MenuApi.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Category menu = categoryService.findByCategory_id(model.getMenu_id(), request_app_id, request_http_id, request_user_id);

        List<Api> resultList = apiService.listNotInMenuByApp_id(menu.getApp_id(),request_app_id, request_http_id, request_user_id);

        for (Api result : resultList) {
            result.keep(Api.API_ID, Api.API_NAME);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MENU_API_SYSTEM_SAVE)
    public void menuApiSystemSave() {
        validateRequest_app_id();
        validate(MenuApi.API_ID, MenuApi.MENU_ID, MenuApi.API_ID);

        MenuApi model = getModel(MenuApi.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Category category = categoryService.findByCategory_id(model.getMenu_id(), request_app_id, request_http_id, request_user_id);

        Integer count = menuApiService.countByApp_idAndApi_id(category.getApp_id(), model.getApi_id(), request_app_id, request_http_id, request_user_id);

        if (count > 0) {
            throw new RuntimeException("API重复啦");
        }

        Boolean result = menuApiService.save(category.getApp_id(), model.getMenu_id(), model.getApi_id(), model.getMenu_api_sort(), request_user_id, request_app_id, request_http_id, request_user_id);

        if (!result) {
            throw new RuntimeException("保存不成功");
        }

        renderSuccessJson();
    }

    @ActionKey(Url.MENU_API_SYSTEM_DELETE)
    public void menuApiSystemDelete() {
        validateRequest_app_id();
        validate(MenuApi.MENU_ID, MenuApi.API_ID, MenuApi.SYSTEM_VERSION);

        MenuApi model = getModel(MenuApi.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = menuApiService.deleteByMenu_idAndApi_id(model.getMenu_id(), model.getApi_id(), request_user_id, request_app_id, request_http_id, request_user_id);

        if (!result) {
            throw new RuntimeException("删除不成功");
        }

        renderSuccessJson();
    }

    private void setResultList(List<Category> resultList, String request_app_id, String request_http_id, String request_user_id) {
        for (Category result : resultList) {
            result.keep(Category.CATEGORY_ID, Category.APP_ID, Category.CATEGORY_NAME, Category.SYSTEM_VERSION);

            List<MenuApi> menuApiList = menuApiService.listByMenu_id(result.getCategory_id(), request_app_id, request_http_id, request_user_id);
            if (menuApiList.size() > 0) {
                for (MenuApi menuApi : menuApiList) {

                    Api api = apiService.findByApi_id(menuApi.getApi_id(), request_app_id, request_http_id, request_user_id);

                    menuApi.keep(MenuApi.MENU_ID, MenuApi.SYSTEM_VERSION);
                    menuApi.put(Api.API_ID, api.getApi_id());
                    menuApi.put(Api.API_URL, api.getApi_url());
                    menuApi.put(Category.CATEGORY_ID, api.getApi_id());
                    menuApi.put(Category.CATEGORY_NAME, api.getApi_name());
                }

                result.put(Constant.CHILDREN, menuApiList);
            }
        }
    }

}