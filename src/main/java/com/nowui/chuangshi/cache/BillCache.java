package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.BillDao;
import com.nowui.chuangshi.model.Bill;
import com.nowui.chuangshi.util.CacheUtil;

public class BillCache extends Cache {

    public static final String BILL_BY_BILL_ID_CACHE = "bill_by_bill_id_cache";

    private BillDao billDao = new BillDao();

    public Integer countByApp_idOrLikeBill_name(String app_id, String bill_name) {
        return billDao.countByApp_idOrLikeBill_name(app_id, bill_name);
    }

    public Integer countByOrApp_idOrLikeBill_name(String app_id, String bill_name) {
        return billDao.countByOrApp_idOrLikeBill_name(app_id, bill_name);
    }

    public List<Bill> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Bill> billList = billDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Bill bill : billList) {
            bill.put(findByBill_id(bill.getBill_id()));
        }

        return billList;
    }

    public List<Bill> listByApp_idOrLikeBill_nameAndLimit(String app_id, String bill_name, int m, int n) {
        List<Bill> billList = billDao.listByApp_idOrLikeBill_nameAndLimit(app_id, bill_name, m, n);

        for (Bill bill : billList) {
            bill.put(findByBill_id(bill.getBill_id()));
        }

        return billList;
    }

    public List<Bill> listByOrApp_idOrLikeBill_nameAndLimit(String app_id, String bill_name, int m, int n) {
        List<Bill> billList = billDao.listByOrApp_idOrLikeBill_nameAndLimit(app_id, bill_name, m, n);

        for (Bill bill : billList) {
            bill.put(findByBill_id(bill.getBill_id()));
        }

        return billList;
    }

    public Bill findByBill_id(String bill_id) {
        Bill bill = CacheUtil.get(BILL_BY_BILL_ID_CACHE, bill_id);

        if (bill == null) {
            bill = billDao.findByBill_id(bill_id);

            CacheUtil.put(BILL_BY_BILL_ID_CACHE, bill_id, bill);
        }

        return bill;
    }

    public Boolean save(String bill_id, String app_id, String user_id, String bill_type, String bill_image, String bill_name, BigDecimal bill_amount, Boolean bill_is_income, String bill_time,
            String bill_flow, Boolean bill_status, String system_create_user_id) {
        return billDao.save(bill_id, app_id, user_id, bill_type, bill_image, bill_name, bill_amount, bill_is_income, bill_time, bill_flow, bill_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String bill_id, String user_id, String bill_type, String bill_image, String bill_name, BigDecimal bill_amount, Boolean bill_is_income, String bill_time,
            String bill_flow, Boolean bill_status, String system_update_user_id, Integer system_version) {
        Bill bill = findByBill_id(bill_id);
        if (!bill.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = billDao.update(bill_id, user_id, bill_type, bill_image, bill_name, bill_amount, bill_is_income, bill_time, bill_flow, bill_status, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(BILL_BY_BILL_ID_CACHE, bill_id);
        }

        return result;
    }

    public Boolean deleteByBill_idAndSystem_update_user_idValidateSystem_version(String bill_id, String system_update_user_id, Integer system_version) {
        Bill bill = findByBill_id(bill_id);
        if (!bill.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = billDao.deleteByBill_idAndSystem_version(bill_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(BILL_BY_BILL_ID_CACHE, bill_id);
        }

        return result;
    }

    public Boolean batchSave(List<Bill> billList) {
        return billDao.batchSave(billList);
    }

}