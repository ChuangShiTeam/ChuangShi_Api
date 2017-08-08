package com.nowui.chuangshi.api.stock.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.stock.model.Stock;
import com.nowui.chuangshi.api.stock.service.StockService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/stock")
public class StockController extends Controller {

    @ActionKey("/admin/stock/list")
    public void list() {
        validateRequest(Stock.WAREHOUSE_ID, Stock.STOCK_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Stock model = getModel(Stock.class);
        Cnd cnd = Cnd.where(Stock.APP_ID, model.getApp_id()).andAllowEmpty(Stock.WAREHOUSE_ID, model.getWarehouse_id()).andAllowEmpty(Stock.STOCK_TYPE, model.getStock_type());

        Integer resultCount = StockService.me.count(cnd);
        List<Stock> resultList = StockService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(Stock.STOCK_ID, Stock.WAREHOUSE_ID, Stock.STOCK_TYPE, Stock.PRODUCT_ID, Stock.STOCK_QUANTITY, Stock.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/stock/find")
    public void find() {
        validateRequest(Stock.STOCK_ID);

        Stock model = getModel(Stock.class);

        Stock result = StockService.me.findById(model.getStock_id());

        validateResponse(Stock.WAREHOUSE_ID, Stock.OBJECT_ID, Stock.STOCK_BATCH, Stock.STOCK_TYPE, Stock.PRODUCT_CATEGORY_ID, Stock.PRODUCT_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Stock.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/stock/save")
    public void save() {
        validateRequest(Stock.WAREHOUSE_ID, Stock.OBJECT_ID, Stock.STOCK_BATCH, Stock.STOCK_TYPE, Stock.PRODUCT_CATEGORY_ID, Stock.PRODUCT_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY);

        Stock model = getModel(Stock.class);
        model.setStock_id(Util.getRandomUUID());

        Boolean result = StockService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/stock/update")
    public void update() {
        validateRequest(Stock.STOCK_ID, Stock.WAREHOUSE_ID, Stock.OBJECT_ID, Stock.STOCK_BATCH, Stock.STOCK_TYPE, Stock.PRODUCT_CATEGORY_ID, Stock.PRODUCT_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);

        Boolean result = StockService.me.update(model, Cnd.where(model.STOCK_ID, model.getStock_id()).and(Stock.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/stock/delete")
    public void delete() {
        validateRequest(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);

        Boolean result = StockService.me.delete(model, Cnd.where(model.STOCK_ID, model.getStock_id()).and(Stock.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}