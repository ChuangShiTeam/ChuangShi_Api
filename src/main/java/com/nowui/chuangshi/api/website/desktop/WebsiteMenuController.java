package com.nowui.chuangshi.api.website.desktop;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

import java.util.List;
import java.util.Map;

@ControllerKey("/desktop/website/menu")
public class WebsiteMenuController extends Controller {

    @ActionKey("/desktop/website/menu/list")
    public void list() {
        String request_app_id = getRequest_app_id();

        List<Map<String, Object>> resultList = WebsiteMenuService.instance.appTree(request_app_id, WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, Page.PAGE_ID);

        validateResponse(WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, Page.PAGE_ID, Constant.CHILDREN);

        renderSuccessJson(resultList);
    }

    @ActionKey("/desktop/website/menu/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/website/menu/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/website/menu/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/website/menu/delete")
    public void delete() {

        renderSuccessJson();
    }

}