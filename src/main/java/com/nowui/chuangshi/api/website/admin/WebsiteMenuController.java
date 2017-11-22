package com.nowui.chuangshi.api.website.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;
import java.util.Map;


@ControllerKey("/admin/website/menu")
public class WebsiteMenuController extends Controller {

    @ActionKey("/admin/website/menu/list")
    public void list() {
        String request_app_id = getRequest_app_id();

        List<Map<String, Object>> resultList = WebsiteMenuService.instance.tree(request_app_id);

        validateResponse(WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT, WebsiteMenu.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/website/menu/all/list")
    public void allList() {
        String request_app_id = getRequest_app_id();

        List<Map<String, Object>> resultList = WebsiteMenuService.instance.appTree(request_app_id, "value", "label");

        validateResponse("value", "label", Constant.CHILDREN);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/website/menu/find")
    public void find() {
        validateRequest(WebsiteMenu.WEBSITE_MENU_ID);

        WebsiteMenu model = getModel(WebsiteMenu.class);

        WebsiteMenu result = WebsiteMenuService.instance.find(model.getWebsite_menu_id());

        validateResponse(WebsiteMenu.WEBSITE_MENU_PARENT_ID, WebsiteMenu.PAGE_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT, WebsiteMenu.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/website/menu/save")
    public void save() {
        validateRequest(WebsiteMenu.WEBSITE_MENU_PARENT_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT);

        WebsiteMenu model = getModel(WebsiteMenu.class);
        model.setWebsite_menu_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = WebsiteMenuService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/website/menu/update")
    public void update() {
        validateRequest(WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_PARENT_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT, WebsiteMenu.SYSTEM_VERSION);

        WebsiteMenu model = getModel(WebsiteMenu.class);
        String request_user_id = getRequest_user_id();

        Boolean result = WebsiteMenuService.instance.update(model, model.getWebsite_menu_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/website/menu/delete")
    public void delete() {
        validateRequest(WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.SYSTEM_VERSION);

        WebsiteMenu model = getModel(WebsiteMenu.class);
        String request_user_id = getRequest_user_id();

        Boolean result = WebsiteMenuService.instance.delete(model.getWebsite_menu_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}