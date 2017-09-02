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
        Integer count = jianglingCustomerDao.count(Cnd.where(JianglingCustomer.APP_ID, app_id).andAllowEmpty(JianglingCustomer.CUSTOMER_NAME, customer_name));
        return count;
    }

    public List<JianglingCustomer> adminList(String app_id, String customer_name, Integer m, Integer n) {
        List<JianglingCustomer> pageList = jianglingCustomerDao.list(Cnd.where(JianglingCustomer.APP_ID, app_id).andAllowEmpty(JianglingCustomer.CUSTOMER_NAME, customer_name).paginate(m, n));
        return pageList;
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
        Boolean result = jianglingCustomerDao.save(page);
        return result;
    }

    public Boolean update(JianglingCustomer page, String user_id, Integer system_version) {
        Boolean result = jianglingCustomerDao.update(page, Cnd.where(JianglingCustomer.USER_ID, user_id).and(JianglingCustomer.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(JIANGLING_CUSTOMER_ITEM_CACHE, user_id);
        }

        return result;
    }

    public Boolean delete(String user_id, Integer system_version) {
        Boolean result = jianglingCustomerDao.delete(Cnd.where(JianglingCustomer.USER_ID, user_id).and(JianglingCustomer.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(JIANGLING_CUSTOMER_ITEM_CACHE, user_id);
        }

        return result;
    }

}