package com.nowui.chuangshi.api.page.desktop;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.api.page.service.PageService;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/desktop/page")
public class PageController extends Controller {

    @ActionKey("/desktop/page/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/page/find")
    public void find() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);

        Page result = PageService.instance.find(model.getPage_id());

        WebsiteMenu websiteMenu = WebsiteMenuService.instance.parentFind(result.getWebsite_menu_id());
        websiteMenu.keep(WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL);
        result.put(websiteMenu);

        validateResponse(Page.PAGE_ID, Page.PAGE_NAME, Page.PAGE_CONTENT, Page.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL);

        renderSuccessJson(result);
    }

    @ActionKey("/desktop/page/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/page/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/page/delete")
    public void delete() {

        renderSuccessJson();
    }

}