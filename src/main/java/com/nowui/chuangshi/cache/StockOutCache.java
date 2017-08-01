package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.StockOutDao;
import com.nowui.chuangshi.model.StockOut;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class StockOutCache extends Cache {

    public static final String STOCK_OUT_BY_STOCK_OUT_ID_CACHE = "stock_out_by_stock_out_id_cache";
    public static final String STOCK_OUT_BY_STOCK_OUT_ID_AND_STOCK_OUT_TYPE_CACHE = "stock_out_by_stock_out_id_and_stock_out_type_cache";

    private StockOutDao stockOutDao = new StockOutDao();

    public Integer countByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name) {
        return stockOutDao.countByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(app_id, warehouse_id, stock_out_type, stock_out_batch, user_name);
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name) {
        return stockOutDao.countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(app_id, warehouse_id, stock_out_type, stock_out_batch, user_name);
    }

    public List<StockOut> listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit(String app_id, String stock_out_type, Date system_create_time, int m, int n) {
        List<StockOut> stock_outList = stockOutDao.listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit(app_id, stock_out_type, system_create_time, m, n);

        for (StockOut stock_out : stock_outList) {
            stock_out.put(findByStock_out_idAndStock_out_type(stock_out.getStock_out_id(), stock_out_type));
        }

        return stock_outList;
    }

    public List<StockOut> listByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name, int m, int n) {
        List<StockOut> stock_outList = stockOutDao.listByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_out_type, stock_out_batch, user_name, m, n);

        for (StockOut stock_out : stock_outList) {
            stock_out.put(findByStock_out_idAndStock_out_type(stock_out.getStock_out_id(), stock_out_type));
        }

        return stock_outList;
    }

    public List<StockOut> listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name, int m, int n) {
        List<StockOut> stock_outList = stockOutDao.listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_out_type, stock_out_batch, user_name, m, n);

        for (StockOut stock_out : stock_outList) {
            stock_out.put(findByStock_out_idAndStock_out_type(stock_out.getStock_out_id(), stock_out_type));
        }

        return stock_outList;
    }

    public StockOut findByStock_out_id(String stock_out_id) {
        StockOut stock_out = CacheUtil.get(STOCK_OUT_BY_STOCK_OUT_ID_CACHE, stock_out_id);

        if (stock_out == null) {
            stock_out = stockOutDao.findByStock_out_id(stock_out_id);

            CacheUtil.put(STOCK_OUT_BY_STOCK_OUT_ID_CACHE, stock_out_id, stock_out);
        }

        return stock_out;
    }
    
    public StockOut findByStock_out_idAndStock_out_type(String stock_out_id, String stock_out_type) {
        StockOut stock_out = CacheUtil.get(STOCK_OUT_BY_STOCK_OUT_ID_AND_STOCK_OUT_TYPE_CACHE, stock_out_id + stock_out_type);

        if (stock_out == null) {
            stock_out = stockOutDao.findByStock_out_idAndStock_out_type(stock_out_id, stock_out_type);

            CacheUtil.put(STOCK_OUT_BY_STOCK_OUT_ID_AND_STOCK_OUT_TYPE_CACHE, stock_out_id + stock_out_type, stock_out);
        }

        return stock_out;
    }


    public Boolean save(String stock_out_id, String app_id, String warehouse_id, String delivery_order_id, String object_id, String stock_out_batch, String stock_out_type, Integer stock_out_quantity, String stock_out_status, String system_create_user_id) {
        return stockOutDao.save(stock_out_id, app_id, warehouse_id, delivery_order_id, object_id, stock_out_batch, stock_out_type, stock_out_quantity, stock_out_status, system_create_user_id);
	}

    public Boolean updateValidateSystem_version(String stock_out_id, String warehouse_id, String delivery_order_id, String object_id, String stock_out_batch, String stock_out_type, Integer stock_out_quantity, String stock_out_status, String system_update_user_id, Integer system_version) {
        StockOut stock_out = findByStock_out_id(stock_out_id);
        if (!stock_out.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockOutDao.update(stock_out_id, warehouse_id, delivery_order_id, object_id, stock_out_batch, stock_out_type, stock_out_quantity, stock_out_status, system_update_user_id, system_version);
		if (result) {
            CacheUtil.remove(STOCK_OUT_BY_STOCK_OUT_ID_CACHE, stock_out_id);
            CacheUtil.remove(STOCK_OUT_BY_STOCK_OUT_ID_AND_STOCK_OUT_TYPE_CACHE, stock_out_id + stock_out.getStock_out_type());
        }

        return result;
    }

    public Boolean deleteByStock_out_idAndSystem_update_user_idValidateSystem_version(String stock_out_id, String system_update_user_id, Integer system_version) {
        StockOut stock_out = findByStock_out_id(stock_out_id);
        if (!stock_out.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockOutDao.deleteByStock_out_idAndSystem_version(stock_out_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_OUT_BY_STOCK_OUT_ID_CACHE, stock_out_id);
            CacheUtil.remove(STOCK_OUT_BY_STOCK_OUT_ID_AND_STOCK_OUT_TYPE_CACHE, stock_out_id + stock_out.getStock_out_type());
        }

        return result;
    }

}