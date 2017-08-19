package com.nowui.chuangshi.api.page.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.api.page.service.PageService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/page")
public class PageController extends Controller {

    @ActionKey("/mobile/page/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/page/find")
    public void find() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);

        Page result = PageService.me.findById(model.getPage_id());

        validateResponse(Page.PAGE_NAME, Page.PAGE_CONTENT);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/page/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/page/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/page/delete")
    public void delete() {

        renderSuccessJson();
    }

}