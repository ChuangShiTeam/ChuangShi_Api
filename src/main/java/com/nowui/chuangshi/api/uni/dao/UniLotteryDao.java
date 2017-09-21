package com.nowui.chuangshi.api.uni.dao;

import com.nowui.chuangshi.api.uni.model.UniLottery;
import com.nowui.chuangshi.common.dao.Dao;

public class UniLotteryDao extends Dao {

    public UniLotteryDao() {
        setModel(new UniLottery());
    }

}