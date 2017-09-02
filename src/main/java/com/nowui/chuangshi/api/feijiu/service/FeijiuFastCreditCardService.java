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
        Integer count = feijiuFastCreditCardDao.count(Cnd.where(FeijiuFastCreditCard.APP_ID, app_id).andAllowEmpty(FeijiuFastCreditCard.CREDIT_CARD_NAME, credit_card_name));
        return count;
    }

    public List<FeijiuFastCreditCard> adminList(String app_id, String credit_card_name, Integer m, Integer n) {
        List<FeijiuFastCreditCard> feijiuFastCreditCardList = feijiuFastCreditCardDao.list(Cnd.where(FeijiuFastCreditCard.APP_ID, app_id).andAllowEmpty(FeijiuFastCreditCard.CREDIT_CARD_NAME, credit_card_name).asc(FeijiuFastCreditCard.CREDIT_CARD_SORT).desc(FeijiuFastCreditCard.SYSTEM_CREATE_TIME).paginate(m, n));
        return feijiuFastCreditCardList;
    }

    public List<FeijiuFastCreditCard> appList(String app_id) {
        List<FeijiuFastCreditCard> feijiuFastCreditCardList = feijiuFastCreditCardDao.list(Cnd.where(FeijiuFastCreditCard.APP_ID, app_id).asc(FeijiuFastCreditCard.CREDIT_CARD_SORT).desc(FeijiuFastCreditCard.SYSTEM_CREATE_TIME));
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

    public Boolean save(FeijiuFastCreditCard certificate) {
        Boolean result = feijiuFastCreditCardDao.save(certificate);
        return result;
    }

    public Boolean update(FeijiuFastCreditCard certificate, String credit_card_id, Integer system_version) {
        Boolean result = feijiuFastCreditCardDao.update(certificate, Cnd.where(FeijiuFastCreditCard.CREDIT_CARD_ID, credit_card_id).and(FeijiuFastCreditCard.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(FEIJIU_FAST_CREDIT_CARD_ITEM_CACHE, credit_card_id);
        }

        return result;
    }

    public Boolean delete(String credit_card_id, Integer system_version) {
        Boolean result = feijiuFastCreditCardDao.delete(Cnd.where(FeijiuFastCreditCard.CREDIT_CARD_ID, credit_card_id).and(FeijiuFastCreditCard.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(FEIJIU_FAST_CREDIT_CARD_ITEM_CACHE, credit_card_id);
        }

        return result;
    }

}