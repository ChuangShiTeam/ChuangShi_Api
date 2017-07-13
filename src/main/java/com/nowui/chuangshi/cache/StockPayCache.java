package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.StockPayDao;
import com.nowui.chuangshi.model.StockPay;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class StockPayCache extends Cache {

    public static final String STOCK_PAY_LIST_BY_STOCK_ID_CACHE = "stock_pay_list_by_stock_id_cache";

    private StockPayDao stockPayDao = new StockPayDao();

    public List<StockPay> listByStock_id(String stock_id) {
        List<StockPay> stock_payList = CacheUtil.get(STOCK_PAY_LIST_BY_STOCK_ID_CACHE, stock_id);
        
        if (stock_payList == null || stock_payList.size() == 0) {
            stock_payList = stockPayDao.listByStock_id(stock_id);
            CacheUtil.put(STOCK_PAY_LIST_BY_STOCK_ID_CACHE, stock_id, stock_payList);
        }
        return stock_payList;
    }

    public Boolean save(String stock_id, String stock_pay_type, String stock_pay_number, String stock_pay_account, String stock_pay_time, String stock_pay_result, String system_create_user_id) {
        return stockPayDao.save(stock_id, stock_pay_type, stock_pay_number, stock_pay_account, stock_pay_time, stock_pay_result, system_create_user_id);
    }


    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {

        boolean result = stockPayDao.deleteByStock_idAndSystem_version(stock_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_PAY_LIST_BY_STOCK_ID_CACHE, stock_id);
        }

        return result;
    }

}