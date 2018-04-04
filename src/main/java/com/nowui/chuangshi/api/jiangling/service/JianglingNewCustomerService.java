package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.dao.JianglingNewCustomerDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingNewCustomer;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class JianglingNewCustomerService extends Service {

    public static final JianglingNewCustomerService instance = new JianglingNewCustomerService();
    private final String JIANGLING_NEW_CUSTOMER_ITEM_CACHE = "JIANGLING_NEW_CUSTOMER_ITEM_CACHE";
    private final JianglingNewCustomerDao jianglingNewCustomerDao = new JianglingNewCustomerDao();

    public Integer adminCount(String app_id, String new_customer_name) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewCustomer.SYSTEM_STATUS, true);
        cnd.and(JianglingNewCustomer.APP_ID, app_id);
        cnd.andAllowEmpty(JianglingNewCustomer.NEW_CUSTOMER_NAME, new_customer_name);

        Integer count = jianglingNewCustomerDao.count(cnd);
        return count;
    }

    public List<JianglingNewCustomer> adminList(String app_id, String new_customer_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewCustomer.SYSTEM_STATUS, true);
        cnd.and(JianglingNewCustomer.APP_ID, app_id);
        cnd.andAllowEmpty(JianglingNewCustomer.NEW_CUSTOMER_NAME, new_customer_name);
        cnd.paginate(m, n);

        List<JianglingNewCustomer> jianglingNewCustomerList = jianglingNewCustomerDao.primaryKeyList(cnd);
        for (JianglingNewCustomer jianglingNewCustomer : jianglingNewCustomerList) {
            jianglingNewCustomer.put(find(jianglingNewCustomer.getUser_id()));
        }
        return jianglingNewCustomerList;
    }

    public JianglingNewCustomer find(String user_id) {
        JianglingNewCustomer page = CacheUtil.get(JIANGLING_NEW_CUSTOMER_ITEM_CACHE, user_id);

        if (page == null) {
            page = jianglingNewCustomerDao.find(user_id);

            CacheUtil.put(JIANGLING_NEW_CUSTOMER_ITEM_CACHE, user_id, page);
        }

        return page;
    }

    public Boolean save(JianglingNewCustomer page, String system_create_user_id) {
        Boolean success = jianglingNewCustomerDao.save(page, system_create_user_id);
        return success;
    }

    public Boolean update(JianglingNewCustomer page, String user_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewCustomer.SYSTEM_STATUS, true);
        cnd.and(JianglingNewCustomer.USER_ID, user_id);
        cnd.and(JianglingNewCustomer.SYSTEM_VERSION, system_version);

        Boolean success = jianglingNewCustomerDao.update(page, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_NEW_CUSTOMER_ITEM_CACHE, user_id);
        }

        return success;
    }

    public Boolean delete(String user_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewCustomer.SYSTEM_STATUS, true);
        cnd.and(JianglingNewCustomer.USER_ID, user_id);
        cnd.and(JianglingNewCustomer.SYSTEM_VERSION, system_version);

        Boolean success = jianglingNewCustomerDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_NEW_CUSTOMER_ITEM_CACHE, user_id);
        }

        return success;
    }

}