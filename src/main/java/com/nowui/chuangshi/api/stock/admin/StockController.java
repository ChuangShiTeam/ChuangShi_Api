package com.nowui.chuangshi.api.stock.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/admin/stock")
public class StockController extends Controller {

    @ActionKey("/admin/stock/list")
    public void list() {
        renderSuccessJson();
    }

    @ActionKey("/admin/stock/find")
    public void find() {
        renderSuccessJson();
    }

    @ActionKey("/admin/stock/save")
    public void save() {
        renderSuccessJson();
    }

    @ActionKey("/admin/stock/update")
    public void update() {
        renderSuccessJson();
    }

    @ActionKey("/admin/stock/delete")
    public void delete() {
        renderSuccessJson();
    }

}