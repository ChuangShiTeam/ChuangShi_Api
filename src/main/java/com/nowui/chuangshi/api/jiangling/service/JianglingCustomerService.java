package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.dao.JianglingCustomerDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingCustomer;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class JianglingCustomerService extends Service {

    public static final JianglingCustomerService instance = new JianglingCustomerService();
    private final String JIANGLING_CUSTOMER_ITEM_CACHE = "jiangling_customer_item_cache";
    private final JianglingCustomerDao jianglingCustomerDao = new JianglingCustomerDao();

    public Integer adminCount(String app_id, String customer_name) {
        Cnd cnd = Cnd.where(JianglingCustomer.SYSTEM_STATUS, true);
        cnd.and(JianglingCustomer.APP_ID, app_id);
        cnd.andAllowEmpty(JianglingCustomer.CUSTOMER_NAME, customer_name);

        Integer count = jianglingCustomerDao.count(cnd);
        return count;
    }

    public List<JianglingCustomer> adminList(String app_id, String customer_name, Integer m, Integer n) {
        Cnd cnd = Cnd.where(JianglingCustomer.SYSTEM_STATUS, true);
        cnd.and(JianglingCustomer.APP_ID, app_id);
        cnd.andAllowEmpty(JianglingCustomer.CUSTOMER_NAME, customer_name);
        cnd.paginate(m, n);

        List<JianglingCustomer> jianglingCustomerList = jianglingCustomerDao.primaryKeyList(cnd);
        for (JianglingCustomer jianglingCustomer : jianglingCustomerList) {
            jianglingCustomer.put(find(jianglingCustomer.getUser_id()));
        }
        return jianglingCustomerList;
    }

    public JianglingCustomer find(String user_id) {
        JianglingCustomer page = CacheUtil.get(JIANGLING_CUSTOMER_ITEM_CACHE, user_id);

        if (page == null) {
            page = jianglingCustomerDao.find(user_id);

            CacheUtil.put(JIANGLING_CUSTOMER_ITEM_CACHE, user_id, page);
        }

        return page;
    }

    public Boolean save(JianglingCustomer page) {
        Boolean success = jianglingCustomerDao.save(page);
        return success;
    }

    public Boolean update(JianglingCustomer page, String user_id, Integer system_version) {
        Cnd cnd = Cnd.where(JianglingCustomer.SYSTEM_STATUS, true);
        cnd.and(JianglingCustomer.USER_ID, user_id);
        cnd.and(JianglingCustomer.SYSTEM_VERSION, system_version);

        Boolean success = jianglingCustomerDao.update(page, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_CUSTOMER_ITEM_CACHE, user_id);
        }

        return success;
    }

    public Boolean delete(String user_id, Integer system_version) {
        Cnd cnd = Cnd.where(JianglingCustomer.SYSTEM_STATUS, true);
        cnd.and(JianglingCustomer.USER_ID, user_id);
        cnd.and(JianglingCustomer.SYSTEM_VERSION, system_version);

        Boolean success = jianglingCustomerDao.delete(cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_CUSTOMER_ITEM_CACHE, user_id);
        }

        return success;
    }

}