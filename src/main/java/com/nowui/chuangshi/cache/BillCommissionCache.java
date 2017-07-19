package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.util.List;

import com.nowui.chuangshi.dao.BillCommissionDao;
import com.nowui.chuangshi.model.BillCommission;

public class BillCommissionCache extends Cache {

    private BillCommissionDao billCommissionDao = new BillCommissionDao();


    public List<BillCommission> listByOrBill_idOrMember_id(String bill_id, String member_id) {
        return billCommissionDao.listByOrBill_idOrMember_id(bill_id, member_id);
    }

    public Boolean save(String bill_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, BigDecimal product_sku_commission, BigDecimal product_sku_commission_amount, String system_create_user_id) {
        return billCommissionDao.save(bill_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount, system_create_user_id);
    }

    public Boolean deleteByBill_idAndSystem_update_user_idValidateSystem_version(String bill_id, String system_update_user_id, Integer system_version) {

        boolean result = billCommissionDao.deleteByBill_idAndSystem_version(bill_id, system_update_user_id, system_version);

        return result;
    }

}