package com.nowui.chuangshi.api.website.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/website/menu")
public class WebsiteMenuController extends Controller {

    @ActionKey("/admin/website/menu/list")
    public void list() {
        validateRequest(WebsiteMenu.WEBSITE_MENU_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        WebsiteMenu model = getModel(WebsiteMenu.class);
        Cnd cnd = Cnd.where(WebsiteMenu.APP_ID, model.getApp_id()).andAllowEmpty(WebsiteMenu.WEBSITE_MENU_NAME, model.getWebsite_menu_name());

        Integer resultCount = WebsiteMenuService.me.count(cnd);
        List<WebsiteMenu> resultList = WebsiteMenuService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT, WebsiteMenu.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/website/menu/find")
    public void find() {
        validateRequest(WebsiteMenu.WEBSITE_MENU_ID);

        WebsiteMenu model = getModel(WebsiteMenu.class);

        WebsiteMenu result = WebsiteMenuService.me.findById(model.getWebsite_menu_id());

        validateResponse(WebsiteMenu.WEBSITE_MENU_PARENT_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT, WebsiteMenu.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/website/menu/save")
    public void save() {
        validateRequest(WebsiteMenu.WEBSITE_MENU_PARENT_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT);

        WebsiteMenu model = getModel(WebsiteMenu.class);
        model.setWebsite_menu_id(Util.getRandomUUID());

        Boolean result = WebsiteMenuService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/website/menu/update")
    public void update() {
        validateRequest(WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_PARENT_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT, WebsiteMenu.SYSTEM_VERSION);

        WebsiteMenu model = getModel(WebsiteMenu.class);

        Boolean result = WebsiteMenuService.me.update(model, Cnd.where(model.WEBSITE_MENU_ID, model.getWebsite_menu_id()).and(WebsiteMenu.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/website/menu/delete")
    public void delete() {
        validateRequest(WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.SYSTEM_VERSION);

        WebsiteMenu model = getModel(WebsiteMenu.class);

        Boolean result = WebsiteMenuService.me.delete(model, Cnd.where(model.WEBSITE_MENU_ID, model.getWebsite_menu_id()).and(WebsiteMenu.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}