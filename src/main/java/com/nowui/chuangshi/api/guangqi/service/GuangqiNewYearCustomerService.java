package com.nowui.chuangshi.api.guangqi.service;

import com.nowui.chuangshi.api.guangqi.dao.GuangqiNewYearCustomerDao;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearCustomer;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GuangqiNewYearCustomerService extends Service {

    public static final GuangqiNewYearCustomerService instance = new GuangqiNewYearCustomerService();
    private final String GUANGQI_NEW_YEAR_CUSTOMER_ITEM_CACHE = "guangqi_new_year_customer_item_cache";
    private final GuangqiNewYearCustomerDao guangqiNewYearCustomerDao = new GuangqiNewYearCustomerDao();

    public Integer adminCount(String app_id, String new_year_customer_car_model, String new_year_customer_name, String new_year_customer_phone, String new_year_customer_province, String new_year_customer_city, String new_year_customer_dealer, String new_year_customer_from) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomer.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomer.APP_ID, app_id);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, new_year_customer_car_model);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, new_year_customer_name);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, new_year_customer_phone);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, new_year_customer_province);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, new_year_customer_city);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, new_year_customer_dealer);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, new_year_customer_from);

        Integer count = guangqiNewYearCustomerDao.count(cnd);
        return count;
    }
    
    public Integer phoneCount(String new_year_customer_phone) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomer.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, new_year_customer_phone);

        Integer count = guangqiNewYearCustomerDao.count(cnd);
        return count;
    }

    public List<GuangqiNewYearCustomer> adminList(String app_id, String new_year_customer_car_model, String new_year_customer_name, String new_year_customer_phone, String new_year_customer_province, String new_year_customer_city, String new_year_customer_dealer, String new_year_customer_from, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomer.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomer.APP_ID, app_id);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, new_year_customer_car_model);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, new_year_customer_name);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, new_year_customer_phone);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, new_year_customer_province);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, new_year_customer_city);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, new_year_customer_dealer);
        cnd.andLikeAllowEmpty(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, new_year_customer_from);
        cnd.paginate(m, n);

        List<GuangqiNewYearCustomer> guangqi_new_year_customerList = guangqiNewYearCustomerDao.primaryKeyList(cnd);
        for (GuangqiNewYearCustomer guangqi_new_year_customer : guangqi_new_year_customerList) {
            guangqi_new_year_customer.put(find(guangqi_new_year_customer.getNew_year_customer_id()));
        }
        return guangqi_new_year_customerList;
    }
    
    public GuangqiNewYearCustomer find(String new_year_customer_id) {
        GuangqiNewYearCustomer guangqi_new_year_customer = CacheUtil.get(GUANGQI_NEW_YEAR_CUSTOMER_ITEM_CACHE, new_year_customer_id);

        if (guangqi_new_year_customer == null) {
            guangqi_new_year_customer = guangqiNewYearCustomerDao.find(new_year_customer_id);

            CacheUtil.put(GUANGQI_NEW_YEAR_CUSTOMER_ITEM_CACHE, new_year_customer_id, guangqi_new_year_customer);
        }

        return guangqi_new_year_customer;
    }

    public Boolean save(GuangqiNewYearCustomer guangqi_new_year_customer, String system_create_user_id) {
        Boolean success = guangqiNewYearCustomerDao.save(guangqi_new_year_customer, system_create_user_id);
        return success;
    }

    public Boolean update(GuangqiNewYearCustomer guangqi_new_year_customer, String new_year_customer_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomer.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID, new_year_customer_id);
        cnd.and(GuangqiNewYearCustomer.SYSTEM_VERSION, system_version);

        Boolean success = guangqiNewYearCustomerDao.update(guangqi_new_year_customer, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_NEW_YEAR_CUSTOMER_ITEM_CACHE, new_year_customer_id);
        }

        return success;
    }

    public Boolean delete(String new_year_customer_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomer.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID, new_year_customer_id);
        cnd.and(GuangqiNewYearCustomer.SYSTEM_VERSION, system_version);

        Boolean success = guangqiNewYearCustomerDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_NEW_YEAR_CUSTOMER_ITEM_CACHE, new_year_customer_id);
        }

        return success;
    }

}