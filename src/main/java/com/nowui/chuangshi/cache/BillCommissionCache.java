package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.BillCommissionDao;
import com.nowui.chuangshi.model.BillCommission;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class BillCommissionCache extends Cache {

    public static final String BILL_COMMISSION_BY_BILL_COMMISSION_ID_CACHE = "bill_commission_by_bill_commission_id_cache";

    private BillCommissionDao billCommissionDao = new BillCommissionDao();

    public Integer countByApp_idOrLikeBill_commission_name(String app_id, String bill_commission_name) {
        return billCommissionDao.countByApp_idOrLikeBill_commission_name(app_id, bill_commission_name);
    }

    public Integer countByOrApp_idOrLikeBill_commission_name(String app_id, String bill_commission_name) {
        return billCommissionDao.countByOrApp_idOrLikeBill_commission_name(app_id, bill_commission_name);
    }

    public List<BillCommission> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<BillCommission> bill_commissionList = billCommissionDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (BillCommission bill_commission : bill_commissionList) {
            bill_commission.put(findByBill_commission_id(bill_commission.getBill_commission_id()));
        }

        return bill_commissionList;
    }

    public List<BillCommission> listByApp_idOrLikeBill_commission_nameAndLimit(String app_id, String bill_commission_name, int m, int n) {
        List<BillCommission> bill_commissionList = billCommissionDao.listByApp_idOrLikeBill_commission_nameAndLimit(app_id, bill_commission_name, m, n);

        for (BillCommission bill_commission : bill_commissionList) {
            bill_commission.put(findByBill_commission_id(bill_commission.getBill_commission_id()));
        }

        return bill_commissionList;
    }

    public List<BillCommission> listByOrApp_idOrLikeBill_commission_nameAndLimit(String app_id, String bill_commission_name, int m, int n) {
        List<BillCommission> bill_commissionList = billCommissionDao.listByOrApp_idOrLikeBill_commission_nameAndLimit(app_id, bill_commission_name, m, n);

        for (BillCommission bill_commission : bill_commissionList) {
            bill_commission.put(findByBill_commission_id(bill_commission.getBill_commission_id()));
        }

        return bill_commissionList;
    }

    public BillCommission findByBill_commission_id(String bill_commission_id) {
        BillCommission bill_commission = CacheUtil.get(BILL_COMMISSION_BY_BILL_COMMISSION_ID_CACHE, bill_commission_id);

        if (bill_commission == null) {
            bill_commission = billCommissionDao.findByBill_commission_id(bill_commission_id);

            CacheUtil.put(BILL_COMMISSION_BY_BILL_COMMISSION_ID_CACHE, bill_commission_id, bill_commission);
        }

        return bill_commission;
    }

    public Boolean save(String bill_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_create_user_id) {
        return billCommissionDao.save(bill_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String bill_commission_id, String bill_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_update_user_id, Integer system_version) {
        BillCommission bill_commission = findByBill_commission_id(bill_commission_id);
        if (!bill_commission.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = billCommissionDao.update(bill_commission_id, bill_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(BILL_COMMISSION_BY_BILL_COMMISSION_ID_CACHE, bill_commission_id);
        }

        return result;
    }

    public Boolean deleteByBill_commission_idAndSystem_update_user_idValidateSystem_version(String bill_commission_id, String system_update_user_id, Integer system_version) {
        BillCommission bill_commission = findByBill_commission_id(bill_commission_id);
        if (!bill_commission.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = billCommissionDao.deleteByBill_commission_idAndSystem_version(bill_commission_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(BILL_COMMISSION_BY_BILL_COMMISSION_ID_CACHE, bill_commission_id);
        }

        return result;
    }

}