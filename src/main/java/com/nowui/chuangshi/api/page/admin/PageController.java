package com.nowui.chuangshi.api.page.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.api.page.service.PageService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/page")
public class PageController extends Controller {

    @ActionKey("/admin/page/list")
    public void list() {
        validateRequest(Page.PAGE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Page model = getModel(Page.class);
        Cnd cnd = Cnd.where(Page.APP_ID, model.getApp_id()).andAllowEmpty(Page.PAGE_NAME, model.getPage_name());

        Integer resultCount = PageService.me.count(cnd);
        List<Page> resultList = PageService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(Page.PAGE_ID, Page.PAGE_NAME, Page.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/page/find")
    public void find() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);

        Page result = PageService.me.findById(model.getPage_id());

        validateResponse(Page.PAGE_NAME, Page.PAGE_CONTENT, Page.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/save")
    public void save() {
        validateRequest(Page.PAGE_NAME, Page.PAGE_CONTENT);

        Page model = getModel(Page.class);
        model.setPage_id(Util.getRandomUUID());

        Boolean result = PageService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/update")
    public void update() {
        validateRequest(Page.PAGE_ID, Page.PAGE_NAME, Page.PAGE_CONTENT, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);

        Boolean result = PageService.me.update(model, Cnd.where(model.PAGE_ID, model.getPage_id()).and(Page.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/delete")
    public void delete() {
        validateRequest(Page.PAGE_ID, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);

        Boolean result = PageService.me.delete(model, Cnd.where(model.PAGE_ID, model.getPage_id()).and(Page.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}