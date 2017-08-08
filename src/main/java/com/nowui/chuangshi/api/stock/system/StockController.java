package com.nowui.chuangshi.api.stock.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.stock.service.StockService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/stock")
public class StockController extends Controller {

    private final StockService stockService = new StockService();

    @ActionKey("/system/stock/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/stock/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/stock/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/stock/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/stock/delete")
    public void delete() {

        renderSuccessJson();
    }

}