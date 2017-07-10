package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.BillCache;
import com.nowui.chuangshi.dao.BillDao;
import com.nowui.chuangshi.model.Bill;

public class BillService extends Service {

    private BillCache billCache = new BillCache();

    private BillDao billDao = new BillDao();

    public Integer countByApp_idOrLikeBill_name(String app_id, String bill_name) {
        return billCache.countByApp_idOrLikeBill_name(app_id, bill_name);
    }

    public Integer countByOrApp_idOrLikeBill_name(String app_id, String bill_name) {
        return billCache.countByOrApp_idOrLikeBill_name(app_id, bill_name);
    }

    public List<Bill> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return billCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Bill> listByApp_idOrLikeBill_nameAndLimit(String app_id, String bill_name, int m, int n) {
        return billCache.listByApp_idOrLikeBill_nameAndLimit(app_id, bill_name, m, n);
    }

    public List<Bill> listByOrApp_idOrLikeBill_nameAndLimit(String app_id, String bill_name, int m, int n) {
        return billCache.listByOrApp_idOrLikeBill_nameAndLimit(app_id, bill_name, m, n);
    }

    public Bill findByBill_id(String bill_id) {
        return billCache.findByBill_id(bill_id);
    }

    public Boolean save(String bill_id, String app_id, String user_id, String bill_type, String bill_image, String bill_name, BigDecimal bill_amount, Boolean bill_is_income, Date bill_time,
            String bill_flow, Boolean bill_status, String system_create_user_id) {
        return billCache.save(bill_id, app_id, user_id, bill_type, bill_image, bill_name, bill_amount, bill_is_income, bill_time, bill_flow, bill_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String bill_id, String user_id, String bill_type, String bill_image, String bill_name, BigDecimal bill_amount, Boolean bill_is_income, Date bill_time,
            String bill_flow, Boolean bill_status, String system_update_user_id, Integer system_version) {
        return billCache.updateValidateSystem_version(bill_id, user_id, bill_type, bill_image, bill_name, bill_amount, bill_is_income, bill_time, bill_flow, bill_status, system_update_user_id,
                system_version);
    }

    public Boolean deleteByBill_idAndSystem_update_user_idValidateSystem_version(String bill_id, String system_update_user_id, Integer system_version) {
        return billCache.deleteByBill_idAndSystem_update_user_idValidateSystem_version(bill_id, system_update_user_id, system_version);
    }

    /**
     * 批量添加账单
     * 
     * @param billList
     * @return
     */
    public Boolean batchSave(List<Bill> billList) {
        return billDao.batchSave(billList);
    }

    public int[] batchSave() {
        return billDao.batchSave();
    }

}