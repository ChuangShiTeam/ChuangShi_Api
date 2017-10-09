package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangTimeline;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangTimelineDao extends Dao {

    public MinhangTimelineDao() {
        setModel(new MinhangTimeline());
    }

}