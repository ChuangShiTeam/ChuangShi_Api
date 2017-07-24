package com.nowui.chuangshi.cache;

import java.util.Date;
import java.util.List;

<<<<<<< HEAD
import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.StockDao;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.type.StockType;
import com.nowui.chuangshi.util.CacheUtil;

public class StockCache extends Cache {

    public static final String STOCK_BY_STOCK_ID_CACHE = "stock_by_stock_id_cache";
    public static final String MEMBER_STOCK_BY_STOCK_ID_CACHE = "member_stock_by_stock_id_cache";
    public static final String APP_STOCK_BY_STOCK_ID_CACHE = "app_stock_by_stock_id_cache";
    public static final String STOCK_QUANTITY_BY_OBJECT_ID_CACHE = "stock_quantity_by_object_id_cache";

    private StockDao stockDao = new StockDao();

    public Integer countByApp_idAndStock_typeOrStock_actionOrLikeUser_name(String app_id, String stock_type, String stock_action, String user_name) {
        return stockDao.countByApp_idAndStock_typeOrStock_actionOrLikeUser_name(app_id, stock_type, stock_action, user_name);
    }

    public Integer countByOrApp_idAndStock_typeOrStock_actionOrLikeUser_name(String app_id, String stock_type, String stock_action, String user_name) {
        return stockDao.countByOrApp_idAndStock_typeOrStock_actionOrLikeUser_name(app_id, stock_type, stock_action, user_name);
    }
    
    public Integer countOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(String app_id, String express_sender_name, String stock_receiver_name, String express_no) {
        return stockDao.countOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(app_id, express_sender_name, stock_receiver_name, express_no);
    }
    
    public Integer countOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(String app_id, String express_sender_name, String stock_receiver_name, String express_no) {
        return stockDao.countOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(app_id, express_sender_name, stock_receiver_name, express_no);
    }
    
    public Integer countByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(String app_id, String stock_type, String product_name, String user_name) {
    	return stockDao.countByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(app_id, stock_type, product_name, user_name);
    }
    
    public Integer countByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(String app_id, String stock_type, String product_name, String user_name) {
    	return stockDao.countByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(app_id, stock_type, product_name, user_name);
    }
    
    public Integer sumStock_quantityByObject_idAndProduct_sku_id(String object_id, String product_sku_id) {
    	return stockDao.sumStock_quantityByObject_idAndProduct_sku_id(object_id, product_sku_id);
    }
    
    public Integer sumStock_quantityByObject_id(String object_id) {
    	Integer stock_quantity = CacheUtil.get(STOCK_QUANTITY_BY_OBJECT_ID_CACHE, object_id);
    	
    	if (stock_quantity == null) {
    		stock_quantity = stockDao.sumStock_quantityByObject_id(object_id);
    		
    		CacheUtil.put(STOCK_QUANTITY_BY_OBJECT_ID_CACHE, object_id, stock_quantity);
    	}
    	return stock_quantity;
    }

    public List<Stock> listByApp_idAndStock_typeAndSystem_create_timeAndLimit(String app_id, String stock_type, Date system_create_time, int m, int n) {
        List<Stock> stockList = stockDao.listByApp_idAndStock_typeAndSystem_create_timeAndLimit(app_id, stock_type, system_create_time, m, n);

        if (StockType.MEMBER.getKey().equals(stock_type)) {
            for (Stock stock : stockList) {
                stock.put(findWithMemberByStock_id(stock.getStock_id()));
            }
        } else if (StockType.APP.getKey().equals(stock_type)) {
            for (Stock stock : stockList) {
                stock.put(findWithAppByStock_id(stock.getStock_id()));
            }
        } else {
            for (Stock stock : stockList) {
                stock.put(findByStock_id(stock.getStock_id()));
            }
        }
        
        return stockList;
    }

    public List<Stock> listByApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(String app_id, String stock_type, String stock_action, String user_name, int m, int n) {
        List<Stock> stockList = stockDao.listByApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(app_id, stock_type, stock_action, user_name, m, n);

        if (StockType.MEMBER.getKey().equals(stock_type)) {
            for (Stock stock : stockList) {
                stock.put(findWithMemberByStock_id(stock.getStock_id()));
            }
        } else if (StockType.APP.getKey().equals(stock_type)) {
            for (Stock stock : stockList) {
                stock.put(findWithAppByStock_id(stock.getStock_id()));
            }
        } else {
            for (Stock stock : stockList) {
                stock.put(findByStock_id(stock.getStock_id()));
            }
        }

        return stockList;
    }

    public List<Stock> listByOrApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(String app_id, String stock_type, String stock_action, String user_name, int m, int n) {
        List<Stock> stockList = stockDao.listByOrApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(app_id, stock_type, stock_action, user_name, m, n);

        if (StockType.MEMBER.getKey().equals(stock_type)) {
            for (Stock stock : stockList) {
                stock.put(findWithMemberByStock_id(stock.getStock_id()));
            }
        } else if (StockType.APP.getKey().equals(stock_type)) {
            for (Stock stock : stockList) {
                stock.put(findWithAppByStock_id(stock.getStock_id()));
            }
        } else {
            for (Stock stock : stockList) {
                stock.put(findByStock_id(stock.getStock_id()));
            }
        }

        return stockList;
    }
    
    public List<Record> listByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(String app_id, String stock_type, String product_name, String user_name, int m, int n) {
        return stockDao.listByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(app_id, stock_type, product_name, user_name, m, n);
    }
    
    public List<Record> listByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(String app_id, String stock_type, String product_name, String user_name, int m, int n) {
        return stockDao.listByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(app_id, stock_type, product_name, user_name, m, n);
    }
    
    public List<Record> listOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(String app_id, String express_sender_name, String stock_receiver_name, String express_no, int m, int n) {
        return stockDao.listOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(app_id, express_sender_name, stock_receiver_name, express_no, m, n);
    }
    
    public List<Record> listOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(String app_id, String express_sender_name, String stock_receiver_name, String express_no, int m, int n) {
        return stockDao.listOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(app_id, express_sender_name, stock_receiver_name, express_no, m, n);
    }
    
    public List<Record> listWithExpressByObject_id(String object_id, int m, int n) {
    	return stockDao.listWithExpressByObject_id(object_id, m, n);
    }
    
    public Stock findByStock_id(String stock_id) {
        Stock stock = CacheUtil.get(STOCK_BY_STOCK_ID_CACHE, stock_id);

        if (stock == null) {
            stock = stockDao.findByStock_id(stock_id);

            CacheUtil.put(STOCK_BY_STOCK_ID_CACHE, stock_id, stock);
        }

        return stock;
    }
    
    public Stock findWithMemberByStock_id(String stock_id) {
        Stock stock = CacheUtil.get(MEMBER_STOCK_BY_STOCK_ID_CACHE, stock_id);
        
        if (stock == null) {
            stock = stockDao.findWithMemberByStock_id(stock_id);
            
            CacheUtil.put(MEMBER_STOCK_BY_STOCK_ID_CACHE, stock_id, stock);
        }
        
        return stock;
    }
    
    public Stock findWithAppByStock_id(String stock_id) {
        Stock stock = CacheUtil.get(APP_STOCK_BY_STOCK_ID_CACHE, stock_id);
        
        if (stock == null) {
            stock = stockDao.findWithAppByStock_id(stock_id);
            
            CacheUtil.put(APP_STOCK_BY_STOCK_ID_CACHE, stock_id, stock);
        }
        
        return stock;
    }

    public Boolean save(String stock_id, String app_id, String trade_id, String object_id, String stock_type, Integer stock_quantity, String stock_sender_user_id, String stock_reciever_user_id, String stock_receiver_name, String stock_receiver_mobile, String stock_receiver_province, String stock_receiver_city, String stock_receiver_area, String stock_receiver_address, String stock_action, String stock_flow, String stock_express_pay_way, String stock_express_shipper_code, Boolean stock_is_pay, String stock_status, String system_create_user_id) {
        boolean result = stockDao.save(stock_id, app_id, trade_id, object_id, stock_type, stock_quantity, stock_sender_user_id, stock_reciever_user_id, stock_receiver_name, stock_receiver_mobile, stock_receiver_province, stock_receiver_city, stock_receiver_area, stock_receiver_address, stock_action, stock_flow, stock_express_pay_way, stock_express_shipper_code, stock_is_pay, stock_status, system_create_user_id);
                
        if (result) {
        	CacheUtil.remove(STOCK_QUANTITY_BY_OBJECT_ID_CACHE, object_id);
        }
        return result;
    }

    public Boolean updateValidateSystem_version(String stock_id, String trade_id, String object_id, String stock_type, Integer stock_quantity, String stock_sender_user_id, String stock_reciever_user_id, String stock_receiver_name, String stock_receiver_mobile, String stock_receiver_province, String stock_receiver_city, String stock_receiver_area, String stock_receiver_address, String stock_action, String stock_flow, String stock_express_pay_way, String stock_express_shipper_code, Boolean stock_is_pay, String stock_status, String system_update_user_id, Integer system_version) {
        Stock stock = findByStock_id(stock_id);
        if (!stock.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockDao.update(stock_id, trade_id, object_id, stock_type, stock_quantity, stock_sender_user_id, stock_reciever_user_id, stock_receiver_name, stock_receiver_mobile, stock_receiver_province, stock_receiver_city, stock_receiver_area, stock_receiver_address, stock_action, stock_flow, stock_express_pay_way, stock_express_shipper_code, stock_is_pay, stock_status, system_update_user_id, system_version);
        
        if (result) {
            CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(MEMBER_STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(APP_STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(STOCK_QUANTITY_BY_OBJECT_ID_CACHE, stock.getObject_id());
        }

        return result;
    }
    
    public Boolean updateStock_flowByStock_idValidateSystem_version(String stock_id, String stock_flow, String system_update_user_id, Integer system_version) {
    	Stock stock = findByStock_id(stock_id);
    	if (!stock.getSystem_version().equals(system_version)) {
    		throw new RuntimeException(Constant.ERROR_VERSION);
    	}
    	
    	boolean result = stockDao.updateStock_flowByStock_idAndSystem_version(stock_id, stock_flow, system_update_user_id, system_version);
    	
    	if (result) {
    		CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
    		CacheUtil.remove(MEMBER_STOCK_BY_STOCK_ID_CACHE, stock_id);
    		CacheUtil.remove(APP_STOCK_BY_STOCK_ID_CACHE, stock_id);
    		CacheUtil.remove(STOCK_QUANTITY_BY_OBJECT_ID_CACHE, stock.getObject_id());
    	}
    	
    	return result;
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        Stock stock = findByStock_id(stock_id);
        if (!stock.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockDao.deleteByStock_idAndSystem_version(stock_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(MEMBER_STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(APP_STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(STOCK_QUANTITY_BY_OBJECT_ID_CACHE, stock.getObject_id());
        }

        return result;
=======
import com.jfinal.plugin.redis.Cache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.StockDao;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.util.CacheUtil;

public class StockCache extends Cache {

    public static final String STOCK_BY_STOCK_ID_CACHE = "stock_by_stock_id_cache";
    public static final String STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE = "stock_by_stock_id_and_stock_type_cache";

    private StockDao stockDao = new StockDao();

    public Integer countByApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(String app_id, String warehouse_id, String stock_type, String product_name, String user_name) {
        return stockDao.countByApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(app_id, warehouse_id, stock_type, product_name, user_name);
    }

    public Integer countByOrApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(String app_id, String warehouse_id, String stock_type, String product_name, String user_name) {
        return stockDao.countByOrApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(app_id, warehouse_id, stock_type, product_name, user_name);
    }
    
    public Integer sumQuantityByApp_idOrWarehouse_idAndObject_idAndProduct_sku_id(String app_id, String warehouse_id, String object_id, String product_sku_id) {
    	return stockDao.sumQuantityByApp_idOrWarehouse_idAndObject_idAndProduct_sku_id(app_id, warehouse_id, object_id, product_sku_id);
    }
    
    public Integer sumQuantityByObject_id(String object_id) {
    	return stockDao.sumQuantityByObject_id(object_id);
    }	

    public List<Stock> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Stock> stockList = stockDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Stock stock : stockList) {
            stock.put(findByStock_id(stock.getStock_id()));
        }

        return stockList;
    }

    public List<Stock> listByApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_type, String product_name, String user_name, int m, int n) {
        List<Stock> stockList = stockDao.listByApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_type, product_name, user_name, m, n);

        for (Stock stock : stockList) {
            stock.put(findByStock_idAndStock_type(stock.getStock_id(), stock_type));
        }

        return stockList;
    }
    
    public List<Stock> listByOrApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_type, String product_name, String user_name, int m, int n) {
        List<Stock> stockList = stockDao.listByOrApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_type, product_name, user_name, m, n);

        for (Stock stock : stockList) {
            stock.put(findByStock_idAndStock_type(stock.getStock_id(), stock_type));
        }

        return stockList;
    }

    public Stock findByStock_id(String stock_id) {
        Stock stock = CacheUtil.get(STOCK_BY_STOCK_ID_CACHE, stock_id);

        if (stock == null) {
            stock = stockDao.findByStock_id(stock_id);

            CacheUtil.put(STOCK_BY_STOCK_ID_CACHE, stock_id, stock);
        }

        return stock;
    }
    
    public Stock findByWarehouse_idAndProduct_sku_idAndStock_type(String warehouse_id, String product_sku_id, String stock_type) {
        return stockDao.findByWarehouse_idAndProduct_sku_idAndStock_type(warehouse_id, product_sku_id, stock_type);
    }
    
    public Stock findByStock_idAndStock_type(String stock_id, String stock_type) {
        Stock stock = CacheUtil.get(STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE, stock_id + stock_type);

        if (stock == null) {
            stock = stockDao.findByStock_idAndStock_type(stock_id, stock_type);

            CacheUtil.put(STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE, stock_id + stock_type, stock);
        }

        return stock;
    }

    public Boolean save(String stock_id, String app_id, String warehouse_id, String object_id, String stock_type, String product_category_id, String product_id, String product_sku_id, Integer stock_quantity, String system_create_user_id) {
        return stockDao.save(stock_id, app_id, warehouse_id, object_id, stock_type, product_category_id, product_id, product_sku_id, stock_quantity, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_id, String warehouse_id, String object_id, String stock_type, String product_category_id, String product_id, String product_sku_id, Integer stock_quantity, String system_update_user_id, Integer system_version) {
        Stock stock = findByStock_id(stock_id);
        if (!stock.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockDao.update(stock_id, warehouse_id, object_id, stock_type, product_category_id, product_id, product_sku_id, stock_quantity, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE, stock_id + stock.getStock_type());
        }

        return result;
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        Stock stock = findByStock_id(stock_id);
        if (!stock.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockDao.deleteByStock_idAndSystem_version(stock_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE, stock_id + stock.getStock_type());
        }

        return result;
    }
    
    public Boolean batchUpdate(List<Stock> stockList) {
    	return stockDao.batchUpdate(stockList);
    }
    
    public Boolean batchSave(List<Stock> stockList) {
    	return stockDao.batchSave(stockList);
>>>>>>> refs/remotes/origin/stock_test
    }

}