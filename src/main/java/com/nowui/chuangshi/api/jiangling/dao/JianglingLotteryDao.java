package com.nowui.chuangshi.api.jiangling.dao;

import com.nowui.chuangshi.api.jiangling.model.JianglingLottery;
import com.nowui.chuangshi.common.dao.Dao;

public class JianglingLotteryDao extends Dao {

    public JianglingLotteryDao() {
        setModel(new JianglingLottery());
    }

}