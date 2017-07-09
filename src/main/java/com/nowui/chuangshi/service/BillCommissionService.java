package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.BillCommissionCache;
import com.nowui.chuangshi.model.BillCommission;

public class BillCommissionService extends Service {

    private BillCommissionCache billCommissionCache = new BillCommissionCache();

    public Integer countByApp_idOrLikeBill_commission_name(String app_id, String bill_commission_name) {
        return billCommissionCache.countByApp_idOrLikeBill_commission_name(app_id, bill_commission_name);
    }

    public Integer countByOrApp_idOrLikeBill_commission_name(String app_id, String bill_commission_name) {
        return billCommissionCache.countByOrApp_idOrLikeBill_commission_name(app_id, bill_commission_name);
    }

    public List<BillCommission> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return billCommissionCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<BillCommission> listByApp_idOrLikeBill_commission_nameAndLimit(String app_id, String bill_commission_name, int m, int n) {
        return billCommissionCache.listByApp_idOrLikeBill_commission_nameAndLimit(app_id, bill_commission_name, m, n);
    }

    public List<BillCommission> listByOrApp_idOrLikeBill_commission_nameAndLimit(String app_id, String bill_commission_name, int m, int n) {
        return billCommissionCache.listByOrApp_idOrLikeBill_commission_nameAndLimit(app_id, bill_commission_name, m, n);
    }

    public BillCommission findByBill_commission_id(String bill_commission_id) {
        return billCommissionCache.findByBill_commission_id(bill_commission_id);
    }

    public Boolean save(String bill_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission,
            BigDecimal product_sku_commission_amount, String system_create_user_id) {
        return billCommissionCache.save(bill_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount,
                system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String bill_commission_id, String bill_id, String product_sku_id, String member_id, String member_name, String member_level_id,
            String member_level_name, Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_update_user_id, Integer system_version) {
        return billCommissionCache.updateValidateSystem_version(bill_commission_id, bill_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission,
                product_sku_commission_amount, system_update_user_id, system_version);
    }

    public Boolean deleteByBill_commission_idAndSystem_update_user_idValidateSystem_version(String bill_commission_id, String system_update_user_id, Integer system_version) {
        return billCommissionCache.deleteByBill_commission_idAndSystem_update_user_idValidateSystem_version(bill_commission_id, system_update_user_id, system_version);
    }

    public Boolean batchSave(List<BillCommission> billCommissionList) {
        return billCommissionCache.batchSave(billCommissionList);
    }

}