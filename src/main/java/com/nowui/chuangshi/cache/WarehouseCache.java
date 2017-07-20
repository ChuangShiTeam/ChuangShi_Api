package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.WarehouseDao;
import com.nowui.chuangshi.model.Warehouse;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class WarehouseCache extends Cache {

    public static final String WAREHOUSE_BY_WAREHOUSE_ID_CACHE = "warehouse_by_warehouse_id_cache";

    private WarehouseDao warehouseDao = new WarehouseDao();

    public Integer countByApp_idOrLikeWarehouse_name(String app_id, String warehouse_name) {
        return warehouseDao.countByApp_idOrLikeWarehouse_name(app_id, warehouse_name);
    }

    public Integer countByOrApp_idOrLikeWarehouse_name(String app_id, String warehouse_name) {
        return warehouseDao.countByOrApp_idOrLikeWarehouse_name(app_id, warehouse_name);
    }

    public List<Warehouse> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Warehouse> warehouseList = warehouseDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Warehouse warehouse : warehouseList) {
            warehouse.put(findByWarehouse_id(warehouse.getWarehouse_id()));
        }

        return warehouseList;
    }

    public List<Warehouse> listByApp_idOrLikeWarehouse_nameAndLimit(String app_id, String warehouse_name, int m, int n) {
        List<Warehouse> warehouseList = warehouseDao.listByApp_idOrLikeWarehouse_nameAndLimit(app_id, warehouse_name, m, n);

        for (Warehouse warehouse : warehouseList) {
            warehouse.put(findByWarehouse_id(warehouse.getWarehouse_id()));
        }

        return warehouseList;
    }

    public List<Warehouse> listByOrApp_idOrLikeWarehouse_nameAndLimit(String app_id, String warehouse_name, int m, int n) {
        List<Warehouse> warehouseList = warehouseDao.listByOrApp_idOrLikeWarehouse_nameAndLimit(app_id, warehouse_name, m, n);

        for (Warehouse warehouse : warehouseList) {
            warehouse.put(findByWarehouse_id(warehouse.getWarehouse_id()));
        }

        return warehouseList;
    }

    public Warehouse findByWarehouse_id(String warehouse_id) {
        Warehouse warehouse = CacheUtil.get(WAREHOUSE_BY_WAREHOUSE_ID_CACHE, warehouse_id);

        if (warehouse == null) {
            warehouse = warehouseDao.findByWarehouse_id(warehouse_id);

            CacheUtil.put(WAREHOUSE_BY_WAREHOUSE_ID_CACHE, warehouse_id, warehouse);
        }

        return warehouse;
    }

    public Boolean save(String warehouse_id, String app_id, String warehouse_code, String warehouse_name, String warehouse_status, String warehouse_remark, String system_create_user_id) {
        return warehouseDao.save(warehouse_id, app_id, warehouse_code, warehouse_name, warehouse_status, warehouse_remark, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String warehouse_id, String warehouse_code, String warehouse_name, String warehouse_status, String warehouse_remark, String system_update_user_id, Integer system_version) {
        Warehouse warehouse = findByWarehouse_id(warehouse_id);
        if (!warehouse.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = warehouseDao.update(warehouse_id, warehouse_code, warehouse_name, warehouse_status, warehouse_remark, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(WAREHOUSE_BY_WAREHOUSE_ID_CACHE, warehouse_id);
        }

        return result;
    }

    public Boolean deleteByWarehouse_idAndSystem_update_user_idValidateSystem_version(String warehouse_id, String system_update_user_id, Integer system_version) {
        Warehouse warehouse = findByWarehouse_id(warehouse_id);
        if (!warehouse.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = warehouseDao.deleteByWarehouse_idAndSystem_version(warehouse_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(WAREHOUSE_BY_WAREHOUSE_ID_CACHE, warehouse_id);
        }

        return result;
    }

}