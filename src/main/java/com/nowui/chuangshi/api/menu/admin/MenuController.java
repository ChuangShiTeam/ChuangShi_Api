package com.nowui.chuangshi.api.menu.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.menu.model.Menu;
import com.nowui.chuangshi.api.menu.service.MenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/menu")
public class MenuController extends Controller {

    @ActionKey("/admin/menu/list")
    public void list() {
        validateRequest(Menu.MENU_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Menu model = getModel(Menu.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MenuService.instance.adminCount(request_app_id, model.getMenu_name());
        List<Menu> resultList = MenuService.instance.adminList(request_app_id, model.getMenu_name(), getM(), getN());

        validateResponse(Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_URL, Menu.MENU_SORT, Menu.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/menu/find")
    public void find() {
        validateRequest(Menu.MENU_ID);

        Menu model = getModel(Menu.class);

        Menu result = MenuService.instance.find(model.getMenu_id());

        validateResponse(Menu.MENU_PARENT_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT, Menu.MENU_PARENT_PATH, Menu.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/menu/save")
    public void save() {
        validateRequest(Menu.MENU_PARENT_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT, Menu.MENU_PARENT_PATH);

        Menu model = getModel(Menu.class);
        model.setMenu_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MenuService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/menu/update")
    public void update() {
        validateRequest(Menu.MENU_ID, Menu.MENU_PARENT_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT, Menu.MENU_PARENT_PATH, Menu.SYSTEM_VERSION);

        Menu model = getModel(Menu.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MenuService.instance.update(model, model.getMenu_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/menu/delete")
    public void delete() {
        validateRequest(Menu.MENU_ID, Menu.SYSTEM_VERSION);

        Menu model = getModel(Menu.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MenuService.instance.delete(model.getMenu_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}