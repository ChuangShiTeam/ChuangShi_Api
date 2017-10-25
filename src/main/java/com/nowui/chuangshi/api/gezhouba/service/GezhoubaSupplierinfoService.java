package com.nowui.chuangshi.api.gezhouba.service;

import com.nowui.chuangshi.api.gezhouba.dao.GezhoubaSupplierinfoDao;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaSupplierinfo;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GezhoubaSupplierinfoService extends Service {

    public static final GezhoubaSupplierinfoService instance = new GezhoubaSupplierinfoService();
    private final String GEZHOUBA_SUPPLIERINFO_ITEM_CACHE = "gezhouba_supplierinfo_item_cache";
    private final GezhoubaSupplierinfoDao gezhoubaSupplierinfoDao = new GezhoubaSupplierinfoDao();

    public Integer adminCount(String app_id, String supplier_name, String supplier_address, String supplier_tel) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaSupplierinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaSupplierinfo.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaSupplierinfo.SUPPLIER_NAME, supplier_name);
        cnd.andAllowEmpty(GezhoubaSupplierinfo.SUPPLIER_ADDRESS, supplier_address);
        cnd.andAllowEmpty(GezhoubaSupplierinfo.SUPPLIER_TEL, supplier_tel);

        Integer count = gezhoubaSupplierinfoDao.count(cnd);
        return count;
    }

    public List<GezhoubaSupplierinfo> adminList(String app_id, String supplier_name, String supplier_address, String supplier_tel, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaSupplierinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaSupplierinfo.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaSupplierinfo.SUPPLIER_NAME, supplier_name);
        cnd.andAllowEmpty(GezhoubaSupplierinfo.SUPPLIER_ADDRESS, supplier_address);
        cnd.andAllowEmpty(GezhoubaSupplierinfo.SUPPLIER_TEL, supplier_tel);
        cnd.paginate(m, n);

        List<GezhoubaSupplierinfo> gezhouba_supplierinfoList = gezhoubaSupplierinfoDao.primaryKeyList(cnd);
        for (GezhoubaSupplierinfo gezhouba_supplierinfo : gezhouba_supplierinfoList) {
            gezhouba_supplierinfo.put(find(gezhouba_supplierinfo.getSupplier_id()));
        }
        return gezhouba_supplierinfoList;
    }

    public GezhoubaSupplierinfo find(String supplier_id) {
        GezhoubaSupplierinfo gezhouba_supplierinfo = CacheUtil.get(GEZHOUBA_SUPPLIERINFO_ITEM_CACHE, supplier_id);

        if (gezhouba_supplierinfo == null) {
            gezhouba_supplierinfo = gezhoubaSupplierinfoDao.find(supplier_id);

            CacheUtil.put(GEZHOUBA_SUPPLIERINFO_ITEM_CACHE, supplier_id, gezhouba_supplierinfo);
        }

        return gezhouba_supplierinfo;
    }

    public Boolean save(GezhoubaSupplierinfo gezhouba_supplierinfo, String system_create_user_id) {
        Boolean success = gezhoubaSupplierinfoDao.save(gezhouba_supplierinfo, system_create_user_id);
        return success;
    }

    public Boolean update(GezhoubaSupplierinfo gezhouba_supplierinfo, String supplier_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaSupplierinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaSupplierinfo.SUPPLIER_ID, supplier_id);
        cnd.and(GezhoubaSupplierinfo.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaSupplierinfoDao.update(gezhouba_supplierinfo, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_SUPPLIERINFO_ITEM_CACHE, supplier_id);
        }

        return success;
    }

    public Boolean delete(String supplier_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaSupplierinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaSupplierinfo.SUPPLIER_ID, supplier_id);
        cnd.and(GezhoubaSupplierinfo.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaSupplierinfoDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_SUPPLIERINFO_ITEM_CACHE, supplier_id);
        }

        return success;
    }

}