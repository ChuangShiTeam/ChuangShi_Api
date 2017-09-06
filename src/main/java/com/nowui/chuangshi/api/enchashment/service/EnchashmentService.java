package com.nowui.chuangshi.api.enchashment.service;

import com.nowui.chuangshi.api.enchashment.dao.EnchashmentDao;
import com.nowui.chuangshi.api.enchashment.model.Enchashment;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class EnchashmentService extends Service {

    public static final EnchashmentService instance = new EnchashmentService();
    private final String ENCHASHMENT_ITEM_CACHE = "enchashment_item_cache";
    private final EnchashmentDao certificateDao = new EnchashmentDao();

    public Integer adminCount(String app_id, String user_id, Boolean enchashment_status) {
        Cnd cnd = Cnd.where(Enchashment.SYSTEM_STATUS, true);
        cnd.and(Enchashment.APP_ID, app_id);
        cnd.andAllowEmpty(Enchashment.USER_ID, user_id);
        cnd.andAllowEmpty(Enchashment.ENCHASHMENT_STATUS, enchashment_status);

        Integer count = certificateDao.count(cnd);
        return count;
    }

    public List<Enchashment> adminList(String app_id, String user_id, Boolean enchashment_status, Integer m, Integer n) {
        Cnd cnd = Cnd.where(Enchashment.SYSTEM_STATUS, true);
        cnd.and(Enchashment.APP_ID, app_id);
        cnd.andAllowEmpty(Enchashment.USER_ID, user_id);
        cnd.andAllowEmpty(Enchashment.ENCHASHMENT_STATUS, enchashment_status);
        cnd.paginate(m, n);

        List<Enchashment> enchashmentList = certificateDao.primaryKeyList(cnd);
        for (Enchashment enchashment : enchashmentList) {
            enchashment.put(find(enchashment.getEnchashment_id()));
        }
        return enchashmentList;
    }

    public List<Enchashment> userList(String user_id) {
        Cnd cnd = Cnd.where(Enchashment.SYSTEM_STATUS, true);
        cnd.and(Enchashment.USER_ID, user_id);

        List<Enchashment> enchashmentList = certificateDao.primaryKeyList(cnd);
        for (Enchashment enchashment : enchashmentList) {
            enchashment.put(find(enchashment.getEnchashment_id()));
        }
        return enchashmentList;
    }

    public Enchashment find(String certificate_id) {
        Enchashment certificate = CacheUtil.get(ENCHASHMENT_ITEM_CACHE, certificate_id);

        if (certificate == null) {
            certificate = certificateDao.find(certificate_id);

            CacheUtil.put(ENCHASHMENT_ITEM_CACHE, certificate_id, certificate);
        }

        return certificate;
    }

    public Boolean save(Enchashment certificate, String system_create_user_id) {
        Boolean success = certificateDao.save(certificate, system_create_user_id);
        return success;
    }

    public Boolean update(Enchashment certificate, String certificate_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(Enchashment.SYSTEM_STATUS, true);
        cnd.and(Enchashment.ENCHASHMENT_ID, certificate_id);
        cnd.and(Enchashment.SYSTEM_VERSION, system_version);

        Boolean success = certificateDao.update(certificate, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ENCHASHMENT_ITEM_CACHE, certificate_id);
        }

        return success;
    }

    public Boolean delete(String certificate_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(Enchashment.SYSTEM_STATUS, true);
        cnd.and(Enchashment.ENCHASHMENT_ID, certificate_id);
        cnd.and(Enchashment.SYSTEM_VERSION, system_version);

        Boolean success = certificateDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ENCHASHMENT_ITEM_CACHE, certificate_id);
        }

        return success;
    }

}