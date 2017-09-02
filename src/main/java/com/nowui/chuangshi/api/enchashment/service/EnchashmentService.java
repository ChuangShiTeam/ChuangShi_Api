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
        Integer count = certificateDao.count(Cnd.where(Enchashment.APP_ID, app_id).andAllowEmpty(Enchashment.USER_ID, user_id).andAllowEmpty(Enchashment.ENCHASHMENT_STATUS, enchashment_status));
        return count;
    }

    public List<Enchashment> adminList(String app_id, String user_id, Boolean enchashment_status, Integer m, Integer n) {
        List<Enchashment> enchashmentList = certificateDao.list(Cnd.where(Enchashment.APP_ID, app_id).andAllowEmpty(Enchashment.USER_ID, user_id).andAllowEmpty(Enchashment.ENCHASHMENT_STATUS, enchashment_status).paginate(m, n));
        return enchashmentList;
    }

    public List<Enchashment> userList(String user_id) {
        List<Enchashment> enchashmentList = certificateDao.list(Cnd.where(Enchashment.USER_ID, user_id));
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

    public Boolean save(Enchashment certificate) {
        Boolean result = certificateDao.save(certificate);
        return result;
    }

    public Boolean update(Enchashment certificate, String certificate_id, Integer system_version) {
        Boolean result = certificateDao.update(certificate, Cnd.where(Enchashment.ENCHASHMENT_ID, certificate_id).and(Enchashment.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(ENCHASHMENT_ITEM_CACHE, certificate_id);
        }

        return result;
    }

    public Boolean delete(String certificate_id, Integer system_version) {
        Boolean result = certificateDao.delete(Cnd.where(Enchashment.ENCHASHMENT_ID, certificate_id).and(Enchashment.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(ENCHASHMENT_ITEM_CACHE, certificate_id);
        }

        return result;
    }

}