package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.dao.JianglingPvDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingPv;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class JianglingPvService extends Service {

    public static final JianglingPvService instance = new JianglingPvService();
    private final String JIANGLING_PV_ITEM_CACHE = "jiangling_pv_item_cache";
    private final JianglingPvDao jianglingPvDao = new JianglingPvDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingPv.SYSTEM_STATUS, true);
        cnd.and(JianglingPv.APP_ID, app_id);

        Integer count = jianglingPvDao.count(cnd);
        return count;
    }

    public List<JianglingPv> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingPv.SYSTEM_STATUS, true);
        cnd.and(JianglingPv.APP_ID, app_id);
        cnd.paginate(m, n);

        List<JianglingPv> jiangling_pvList = jianglingPvDao.primaryKeyList(cnd);
        for (JianglingPv jiangling_pv : jiangling_pvList) {
            jiangling_pv.put(find(jiangling_pv.getApp_id()));
        }
        return jiangling_pvList;
    }

    public JianglingPv find(String app_id) {
        JianglingPv jiangling_pv = CacheUtil.get(JIANGLING_PV_ITEM_CACHE, app_id);

        if (jiangling_pv == null) {
            jiangling_pv = jianglingPvDao.find(app_id);

            CacheUtil.put(JIANGLING_PV_ITEM_CACHE, app_id, jiangling_pv);
        }

        return jiangling_pv;
    }

    public Boolean save(JianglingPv jiangling_pv, String system_create_user_id) {
        Boolean success = jianglingPvDao.save(jiangling_pv, system_create_user_id);
        return success;
    }

    public Boolean update(JianglingPv jiangling_pv, String app_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingPv.SYSTEM_STATUS, true);
        cnd.and(JianglingPv.APP_ID, app_id);
        cnd.and(JianglingPv.SYSTEM_VERSION, system_version);

        Boolean success = jianglingPvDao.update(jiangling_pv, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_PV_ITEM_CACHE, app_id);
        }

        return success;
    }

    public Boolean delete(String app_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingPv.SYSTEM_STATUS, true);
        cnd.and(JianglingPv.APP_ID, app_id);
        cnd.and(JianglingPv.SYSTEM_VERSION, system_version);

        Boolean success = jianglingPvDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_PV_ITEM_CACHE, app_id);
        }

        return success;
    }

}