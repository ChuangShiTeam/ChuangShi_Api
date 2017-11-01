package com.nowui.chuangshi.api.renault.service;

import java.util.List;

import com.nowui.chuangshi.api.renault.dao.RenaultCustomerDao;
import com.nowui.chuangshi.api.renault.model.RenaultCustomer;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class RenaultCustomerService extends Service {

    public static final RenaultCustomerService instance = new RenaultCustomerService();
    private final String RENAULT_CUSTOMER_ITEM_CACHE = "renault_customer_item_cache";
    private final RenaultCustomerDao renaultCustomerDao = new RenaultCustomerDao();

    public Integer adminCount(String app_id, String customer_name, String customer_phone) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultCustomer.SYSTEM_STATUS, true);
        cnd.and(RenaultCustomer.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultCustomer.CUSTOMER_NAME, customer_name);
        cnd.andAllowEmpty(RenaultCustomer.CUSTOMER_PHONE, customer_phone);

        Integer count = renaultCustomerDao.count(cnd);
        return count;
    }

    public List<RenaultCustomer> adminList(String app_id, String customer_name, String customer_phone, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultCustomer.SYSTEM_STATUS, true);
        cnd.and(RenaultCustomer.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultCustomer.CUSTOMER_NAME, customer_name);
        cnd.andAllowEmpty(RenaultCustomer.CUSTOMER_PHONE, customer_phone);
        cnd.paginate(m, n);

        List<RenaultCustomer> renault_customerList = renaultCustomerDao.primaryKeyList(cnd);
        for (RenaultCustomer renault_customer : renault_customerList) {
            renault_customer.put(find(renault_customer.getCustomer_id()));
        }
        return renault_customerList;
    }

    public RenaultCustomer find(String customer_id) {
        RenaultCustomer renault_customer = CacheUtil.get(RENAULT_CUSTOMER_ITEM_CACHE, customer_id);

        if (renault_customer == null) {
            renault_customer = renaultCustomerDao.find(customer_id);

            CacheUtil.put(RENAULT_CUSTOMER_ITEM_CACHE, customer_id, renault_customer);
        }

        return renault_customer;
    }

    public Boolean save(RenaultCustomer renault_customer, String system_create_user_id) {
        Boolean success = renaultCustomerDao.save(renault_customer, system_create_user_id);
        return success;
    }
    
    public Boolean update(RenaultCustomer renault_customer, String customer_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultCustomer.SYSTEM_STATUS, true);
        cnd.and(RenaultCustomer.CUSTOMER_ID, customer_id);
        cnd.and(RenaultCustomer.SYSTEM_VERSION, system_version);

        Boolean success = renaultCustomerDao.update(renault_customer, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_CUSTOMER_ITEM_CACHE, customer_id);
        }

        return success;
    }

    public Boolean delete(String customer_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultCustomer.SYSTEM_STATUS, true);
        cnd.and(RenaultCustomer.CUSTOMER_ID, customer_id);
        cnd.and(RenaultCustomer.SYSTEM_VERSION, system_version);

        Boolean success = renaultCustomerDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_CUSTOMER_ITEM_CACHE, customer_id);
        }

        return success;
    }

}