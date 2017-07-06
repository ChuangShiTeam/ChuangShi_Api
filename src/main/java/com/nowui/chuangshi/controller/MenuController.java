package com.nowui.chuangshi.controller;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Menu;
import com.nowui.chuangshi.service.MenuService;
import com.nowui.chuangshi.util.Util;

import java.util.List;
import java.util.Map;

public class MenuController extends Controller {

    private final MenuService menuService = new MenuService();

    @ActionKey(Url.MENU_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Menu.APP_ID, Menu.MENU_NAME);

        Menu model = getModel(Menu.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        List<Map<String, Object>> resultList = menuService.treeByApp_idOrLikeMenu_name(request_app_id, model.getMenu_name());

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MENU_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Menu.MENU_ID);

        Menu model = getModel(Menu.class);

        authenticateRequest_app_idAndRequest_user_id();

        Menu menu = menuService.findByMenu_id(model.getMenu_id());

        authenticateApp_id(menu.getApp_id());

        menu.keep(Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT, Menu.MENU_PARENT_ID, Menu.SYSTEM_VERSION);

        renderSuccessJson(menu);
    }

    @ActionKey(Url.MENU_ADMIN_SAVE)
    public void adminSave() {
        validateRequest_app_id();
        validate(Menu.MENU_PARENT_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT);

        Menu model = getModel(Menu.class);
        String menu_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = menuService.save(menu_id, request_app_id, model.getMenu_parent_id(), model.getMenu_name(), model.getMenu_image(), model.getMenu_url(), model.getMenu_sort(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MENU_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Menu.MENU_ID, Menu.MENU_PARENT_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT, Menu.SYSTEM_VERSION);

        Menu model = getModel(Menu.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Menu menu = menuService.findByMenu_id(model.getMenu_id());

        authenticateApp_id(menu.getApp_id());

        Boolean result = menuService.updateValidateSystem_version(model.getMenu_id(), model.getMenu_parent_id(), model.getMenu_name(), model.getMenu_image(), model.getMenu_url(), model.getMenu_sort(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MENU_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Menu.MENU_ID, Menu.SYSTEM_VERSION);

        Menu model = getModel(Menu.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Menu menu = menuService.findByMenu_id(model.getMenu_id());

        authenticateApp_id(menu.getApp_id());

        Boolean result = menuService.deleteByMenu_idAndSystem_update_user_idValidateSystem_version(model.getMenu_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MENU_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Menu.APP_ID, Menu.MENU_NAME);

        Menu model = getModel(Menu.class);

        List<Map<String, Object>>  resultList = menuService.treeByOrApp_idOrLikeMenu_name(model.getApp_id(), model.getMenu_name());

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MENU_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Menu.MENU_ID);

        Menu model = getModel(Menu.class);

        Menu menu = menuService.findByMenu_id(model.getMenu_id());

        menu.keep(Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT, Menu.MENU_PARENT_ID, Menu.SYSTEM_VERSION);

        renderSuccessJson(menu);
    }

    @ActionKey(Url.MENU_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Menu.APP_ID, Menu.MENU_PARENT_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT);

        Menu model = getModel(Menu.class);
        String menu_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = menuService.save(menu_id, model.getApp_id(), model.getMenu_parent_id(), model.getMenu_name(), model.getMenu_image(), model.getMenu_url(), model.getMenu_sort(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MENU_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Menu.MENU_ID, Menu.MENU_PARENT_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT, Menu.SYSTEM_VERSION);

        Menu model = getModel(Menu.class);
        String request_user_id = getRequest_user_id();

        Boolean result = menuService.updateValidateSystem_version(model.getMenu_id(), model.getMenu_parent_id(), model.getMenu_name(), model.getMenu_image(), model.getMenu_url(), model.getMenu_sort(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MENU_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Menu.MENU_ID, Menu.SYSTEM_VERSION);

        Menu model = getModel(Menu.class);
        String request_user_id = getRequest_user_id();

        Boolean result = menuService.deleteByMenu_idAndSystem_update_user_idValidateSystem_version(model.getMenu_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}