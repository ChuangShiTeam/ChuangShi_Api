package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.WarehouseCache;
import com.nowui.chuangshi.model.Warehouse;
import com.nowui.chuangshi.type.WarehouseStatus;
import com.nowui.chuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class WarehouseService extends Service {

    private WarehouseCache warehouseCache = new WarehouseCache();

    public Integer countByApp_idOrLikeWarehouse_name(String app_id, String warehouse_name) {
        return warehouseCache.countByApp_idOrLikeWarehouse_name(app_id, warehouse_name);
    }

    public Integer countByOrApp_idOrLikeWarehouse_name(String app_id, String warehouse_name) {
        return warehouseCache.countByOrApp_idOrLikeWarehouse_name(app_id, warehouse_name);
    }

    public List<Warehouse> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return warehouseCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Warehouse> listByApp_idOrLikeWarehouse_nameAndLimit(String app_id, String warehouse_name, int m, int n) {
        return warehouseCache.listByApp_idOrLikeWarehouse_nameAndLimit(app_id, warehouse_name, m, n);
    }

    public List<Warehouse> listByOrApp_idOrLikeWarehouse_nameAndLimit(String app_id, String warehouse_name, int m, int n) {
        return warehouseCache.listByOrApp_idOrLikeWarehouse_nameAndLimit(app_id, warehouse_name, m, n);
    }

    public Warehouse findByWarehouse_id(String warehouse_id) {
        return warehouseCache.findByWarehouse_id(warehouse_id);
    }

    public Boolean save(String warehouse_id, String app_id, String warehouse_code, String warehouse_name, String warehouse_status, String warehouse_remark, String system_create_user_id) {
        return warehouseCache.save(warehouse_id, app_id, warehouse_code, warehouse_name, warehouse_status, warehouse_remark, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String warehouse_id, String warehouse_code, String warehouse_name, String warehouse_status, String warehouse_remark, String system_update_user_id, Integer system_version) {
        return warehouseCache.updateValidateSystem_version(warehouse_id, warehouse_code, warehouse_name, warehouse_status, warehouse_remark, system_update_user_id, system_version);
    }

    public Boolean deleteByWarehouse_idAndSystem_update_user_idValidateSystem_version(String warehouse_id, String system_update_user_id, Integer system_version) {
        return warehouseCache.deleteByWarehouse_idAndSystem_update_user_idValidateSystem_version(warehouse_id, system_update_user_id, system_version);
    }
    
    /**
     * 创建默认仓库
     * @param app_id
     * @param system_create_user_id
     * @return
     */
    public Boolean createDefault(String app_id, String system_create_user_id) {
        Integer count = countByApp_idOrLikeWarehouse_name(app_id, null);
        if (count != null && count > 0) {
            return false;
        }
        String warehouse_id = Util.getRandomUUID(); 
        String warehouse_code = "0001";
        String warehouse_name = "默认仓库";
        String warehouse_status = WarehouseStatus.AVAILABLE.getKey();
        String warehouse_remark = "";
        return save(warehouse_id, app_id, warehouse_code, warehouse_name, warehouse_status, warehouse_remark, system_create_user_id);
    }

}