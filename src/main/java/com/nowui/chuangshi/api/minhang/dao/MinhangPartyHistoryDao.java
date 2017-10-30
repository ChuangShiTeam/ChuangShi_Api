package com.nowui.chuangshi.api.minhang.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.api.minhang.model.MinhangPartyHistory;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.util.DateUtil;

public class MinhangPartyHistoryDao extends Dao {
	
	private final MinhangPartyHistory minhang_party_history = new MinhangPartyHistory();

    public MinhangPartyHistoryDao() {
        setModel(new MinhangPartyHistory());
    }
    
    public MinhangPartyHistory prevHistory(String app_id, Date system_create_time) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MinhangPartyHistory.APP_ID, app_id);
        sqlMap.put(MinhangPartyHistory.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(system_create_time));
        SqlPara sqlPara = Db.getSqlPara("minhang_party_history.prevHistory", sqlMap);

        logSql("minhang_party_history", "prevHistory", sqlPara);
        
        List<MinhangPartyHistory> minhang_party_historyList = minhang_party_history.find(sqlPara.getSql(), sqlPara.getPara());
        
        if (minhang_party_historyList == null || minhang_party_historyList.size() == 0) {
           return null; 
        }

        return minhang_party_historyList.get(0);
    }
    
    public MinhangPartyHistory nextHistory(String app_id, Date system_create_time) {
    	Kv sqlMap = Kv.create();
        sqlMap.put(MinhangPartyHistory.APP_ID, app_id);
        sqlMap.put(MinhangPartyHistory.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(system_create_time));
        SqlPara sqlPara = Db.getSqlPara("minhang_party_history.nextHistory", sqlMap);

        logSql("minhang_party_history", "nextHistory", sqlPara);
        
        List<MinhangPartyHistory> minhang_party_historyList = minhang_party_history.find(sqlPara.getSql(), sqlPara.getPara());
        
        if (minhang_party_historyList == null || minhang_party_historyList.size() == 0) {
           return null; 
        }

        return minhang_party_historyList.get(0);
    }

}