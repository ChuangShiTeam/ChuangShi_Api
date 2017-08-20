package com.nowui.chuangshi.api.captcha.cache;

import com.nowui.chuangshi.api.captcha.dao.CaptchaDao;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.common.cache.Cache;

public class CaptchaCache extends Cache {

    public static final String CAPTCHA_ITEM_CACHE = "captcha_item_cache";

    public CaptchaCache() {
        setDao(new CaptchaDao());

        setItemCache(CAPTCHA_ITEM_CACHE, Captcha.CAPTCHA_ID);
    }

}