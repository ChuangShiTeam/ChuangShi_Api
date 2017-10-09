package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangTimelineEvent;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangTimelineEventDao extends Dao {

    public MinhangTimelineEventDao() {
        setModel(new MinhangTimelineEvent());
    }

}