package com.nowui.chuangshi.api.feijiu.service;

import com.nowui.chuangshi.api.feijiu.dao.FeijiuFastCreditCardDao;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastCreditCard;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class FeijiuFastCreditCardService extends Service {

    public static final FeijiuFastCreditCardService instance = new FeijiuFastCreditCardService();
    private final String FEIJIU_FAST_CREDIT_CARD_ITEM_CACHE = "feijiu_fast_credit_card_item_cache";
    private final FeijiuFastCreditCardDao feijiuFastCreditCardDao = new FeijiuFastCreditCardDao();

    public Integer adminCount(String app_id, String credit_card_name) {
        Cnd cnd = new Cnd();
        cnd.where(FeijiuFastCreditCard.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastCreditCard.APP_ID, app_id);
        cnd.andAllowEmpty(FeijiuFastCreditCard.CREDIT_CARD_NAME, credit_card_name);

        Integer count = feijiuFastCreditCardDao.count(cnd);
        return count;
    }

    public List<FeijiuFastCreditCard> adminList(String app_id, String credit_card_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(FeijiuFastCreditCard.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastCreditCard.APP_ID, app_id);
        cnd.andAllowEmpty(FeijiuFastCreditCard.CREDIT_CARD_NAME, credit_card_name);
        cnd.asc(FeijiuFastCreditCard.CREDIT_CARD_SORT);
        cnd.desc(FeijiuFastCreditCard.SYSTEM_CREATE_TIME).paginate(m, n);

        List<FeijiuFastCreditCard> feijiuFastCreditCardList = feijiuFastCreditCardDao.primaryKeyList(cnd);
        for (FeijiuFastCreditCard feijiuFastCreditCard : feijiuFastCreditCardList) {
            feijiuFastCreditCard.put(find(feijiuFastCreditCard.getCredit_card_id()));
        }
        return feijiuFastCreditCardList;
    }

    public List<FeijiuFastCreditCard> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(FeijiuFastCreditCard.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastCreditCard.APP_ID, app_id);
        cnd.asc(FeijiuFastCreditCard.CREDIT_CARD_SORT);
        cnd.desc(FeijiuFastCreditCard.SYSTEM_CREATE_TIME);

        List<FeijiuFastCreditCard> feijiuFastCreditCardList = feijiuFastCreditCardDao.primaryKeyList(cnd);
        for (FeijiuFastCreditCard feijiuFastCreditCard : feijiuFastCreditCardList) {
            feijiuFastCreditCard.put(find(feijiuFastCreditCard.getCredit_card_id()));
        }
        return feijiuFastCreditCardList;
    }

    public FeijiuFastCreditCard find(String credit_card_id) {
        FeijiuFastCreditCard certificate = CacheUtil.get(FEIJIU_FAST_CREDIT_CARD_ITEM_CACHE, credit_card_id);

        if (certificate == null) {
            certificate = feijiuFastCreditCardDao.find(credit_card_id);

            CacheUtil.put(FEIJIU_FAST_CREDIT_CARD_ITEM_CACHE, credit_card_id, certificate);
        }

        return certificate;
    }

    public Boolean save(FeijiuFastCreditCard certificate, String system_create_user_id) {
        Boolean success = feijiuFastCreditCardDao.save(certificate, system_create_user_id);
        return success;
    }

    public Boolean update(FeijiuFastCreditCard certificate, String credit_card_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(FeijiuFastCreditCard.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastCreditCard.CREDIT_CARD_ID, credit_card_id);
        cnd.and(FeijiuFastCreditCard.SYSTEM_VERSION, system_version);

        Boolean success = feijiuFastCreditCardDao.update(certificate, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(FEIJIU_FAST_CREDIT_CARD_ITEM_CACHE, credit_card_id);
        }

        return success;
    }

    public Boolean delete(String credit_card_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(FeijiuFastCreditCard.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastCreditCard.CREDIT_CARD_ID, credit_card_id);
        cnd.and(FeijiuFastCreditCard.SYSTEM_VERSION, system_version);

        Boolean success = feijiuFastCreditCardDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(FEIJIU_FAST_CREDIT_CARD_ITEM_CACHE, credit_card_id);
        }

        return success;
    }

}