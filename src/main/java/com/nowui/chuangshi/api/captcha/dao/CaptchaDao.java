package com.nowui.chuangshi.api.captcha.dao;

import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.common.dao.Dao;

public class CaptchaDao extends Dao {

    public CaptchaDao() {
        setModel(new Captcha());
    }

}