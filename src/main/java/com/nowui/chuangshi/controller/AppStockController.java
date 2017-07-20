package com.nowui.chuangshi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.StockProductSku;
import com.nowui.chuangshi.service.StockProductSkuService;
import com.nowui.chuangshi.service.StockService;
import com.nowui.chuangshi.type.StockType;

public class AppStockController extends Controller {
    
    private final StockService stockService = new StockService();
    private final StockProductSkuService stockProductSkuService = new StockProductSkuService();


    @ActionKey(Url.APP_STOCK_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        Stock model = getModel(Stock.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = stockService.countByApp_idAndStock_typeOrStock_actionOrLikeUser_name(request_app_id, StockType.APP.getKey(), model.getStock_action(), null);
        List<Stock> resultList = stockService.listByApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(request_app_id, StockType.APP.getKey(), model.getStock_action(), null, getM(), getN());

        for (Stock result : resultList) {
            result.keep(Stock.STOCK_ID, Stock.APP_NAME, Stock.STOCK_QUANTITY, Stock.STOCK_ACTION, Stock.SYSTEM_CREATE_TIME, Stock.SYSTEM_VERSION);
        }
        renderSuccessJson(total, resultList);
    }
    
    @ActionKey(Url.APP_STOCK_ADMIN_STOCK_LIST)
    public void adminStockList() {
    	validateRequest_app_id();
    	validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
    	
    	String request_app_id = getRequest_app_id();
    	JSONObject jsonObject = getParameterJSONObject();
    	String product_name = jsonObject.getString("product_name");
    	
    	authenticateRequest_app_idAndRequest_user_id();
    	
    	Integer total = stockService.countByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(request_app_id, StockType.APP.getKey(), product_name, null);
    	List<Record> recordList = stockService.listByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(request_app_id, StockType.APP.getKey(), product_name, null, getM(), getN());
    	
    	List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    	for (Record record : recordList) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put(Stock.SUM_STOCK_QUANTITY, record.get(Stock.SUM_STOCK_QUANTITY));
    		map.put(Stock.APP_NAME, record.get(Stock.APP_NAME));
    		map.put(Product.PRODUCT_NAME, record.get(Product.PRODUCT_NAME));
    		resultList.add(map);
    	}
    	
    	renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.APP_STOCK_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID);

        Stock model = getModel(Stock.class);

        authenticateRequest_app_idAndRequest_user_id();

        Stock stock = stockService.findWithAppByStock_id(model.getStock_id());

        authenticateApp_id(stock.getApp_id());
        
        List<StockProductSku> stockProductSkuList = stockProductSkuService.listAndProduct_nameByStock_id(model.getStock_id());
        
        stock.keep(Stock.STOCK_ID, Stock.APP_NAME, Stock.STOCK_ACTION, Stock.STOCK_QUANTITY, Stock.SYSTEM_VERSION);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("stock", stock);
        result.put(Stock.STOCK_PRODUCT_SKU_LIST, stockProductSkuList);
        renderSuccessJson(result);
    }
    
    @ActionKey(Url.APP_STOCK_ADMIN_REPLENISH)
    public void adminReplenish() {
        validateRequest_app_id();
        validate(App.APP_ID, Stock.STOCK_PRODUCT_SKU_LIST);

        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        
        JSONObject jsonObject = getParameterJSONObject();
        JSONArray productSkuList = jsonObject.getJSONArray(Stock.STOCK_PRODUCT_SKU_LIST);
        if (productSkuList == null || productSkuList.size() == 0) {
            throw new RuntimeException("初始化库存失败, 产品sku不能为空");
        }
        
        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = stockService.replenish(request_app_id, request_app_id, StockType.APP.getKey(), productSkuList, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.APP_STOCK_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Stock stock = stockService.findByStock_id(model.getStock_id());

        authenticateApp_id(stock.getApp_id());

        Boolean result = stockService.deleteByStock_idAndSystem_update_user_idValidateSystem_version(model.getStock_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey(Url.APP_STOCK_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Stock model = getModel(Stock.class);

        Integer total = stockService.countByOrApp_idAndStock_typeOrStock_actionOrLikeUser_name(model.getApp_id(), StockType.APP.getKey(), model.getStock_action(), null);
        List<Stock> resultList = stockService.listByOrApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(model.getApp_id(), StockType.APP.getKey(), model.getStock_action(), null, getM(), getN());

        for (Stock result : resultList) {
            result.keep(Stock.STOCK_ID, Stock.APP_NAME, Stock.STOCK_QUANTITY, Stock.STOCK_ACTION, Stock.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }
    
    @ActionKey(Url.APP_STOCK_SYSTEM_STOCK_LIST)
    public void systemStockList() {
    	validateRequest_app_id();
    	validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
    	
    	JSONObject jsonObject = getParameterJSONObject();
    	String app_id = jsonObject.getString("app_id");
    	String product_name = jsonObject.getString("product_name");
    	
    	authenticateRequest_app_idAndRequest_user_id();
    	
    	Integer total = stockService.countByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(app_id, StockType.APP.getKey(), product_name, null);
    	List<Record> recordList = stockService.listByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(app_id, StockType.APP.getKey(), product_name, null, getM(), getN());
    	
    	List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    	for (Record record : recordList) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put(Stock.SUM_STOCK_QUANTITY, record.get(Stock.SUM_STOCK_QUANTITY));
    		map.put(Stock.APP_NAME, record.get(Stock.APP_NAME));
    		map.put(Product.PRODUCT_NAME, record.get(Product.PRODUCT_NAME));
    		resultList.add(map);
    	}
    	
    	renderSuccessJson(total, resultList);
    }
    
    @ActionKey(Url.APP_STOCK_SYSTEM_REPLENISH)
    public void systemReplenish() {
        validateRequest_app_id();
        validate(App.APP_ID, Stock.STOCK_PRODUCT_SKU_LIST);

        String request_user_id = getRequest_user_id();
        
        JSONObject jsonObject = getParameterJSONObject();
        JSONArray productSkuList = jsonObject.getJSONArray(Stock.STOCK_PRODUCT_SKU_LIST);
        if (productSkuList == null || productSkuList.size() == 0) {
            throw new RuntimeException("初始化库存失败, 产品sku不能为空");
        }
        String app_id = jsonObject.getString("app_id");

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = stockService.replenish(app_id, app_id, StockType.APP.getKey(), productSkuList, request_user_id);

        renderSuccessJson(result);
    }


    @ActionKey(Url.APP_STOCK_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID);

        Stock model = getModel(Stock.class);

        Stock stock = stockService.findByStock_id(model.getStock_id());

        stock.keep(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        renderSuccessJson(stock);
    }

    @ActionKey(Url.APP_STOCK_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        Boolean result = stockService.deleteByStock_idAndSystem_update_user_idValidateSystem_version(model.getStock_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}
