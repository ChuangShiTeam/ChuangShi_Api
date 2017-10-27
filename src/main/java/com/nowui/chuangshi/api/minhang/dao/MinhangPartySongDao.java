package com.nowui.chuangshi.api.minhang.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.api.minhang.model.MinhangPartySong;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.util.DateUtil;

public class MinhangPartySongDao extends Dao {
    
    private final MinhangPartySong minhang_party_song = new MinhangPartySong();

    public MinhangPartySongDao() {
        setModel(new MinhangPartySong());
    }
    
    public MinhangPartySong prevSong(String app_id, Date system_create_time) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MinhangPartySong.APP_ID, app_id);
        sqlMap.put(MinhangPartySong.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(system_create_time));
        SqlPara sqlPara = Db.getSqlPara("minhang_party_song.prevSong", sqlMap);

        logSql("minhang_party_song", "prevSong", sqlPara);
        
        List<MinhangPartySong> minhang_party_songList = minhang_party_song.find(sqlPara.getSql(), sqlPara.getPara());
        
        if (minhang_party_songList == null || minhang_party_songList.size() == 0) {
           return null; 
        }

        return minhang_party_songList.get(0);
    }
    
    public MinhangPartySong nextSong(String app_id, Date system_create_time) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MinhangPartySong.APP_ID, app_id);
        sqlMap.put(MinhangPartySong.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(system_create_time));
        SqlPara sqlPara = Db.getSqlPara("minhang_party_song.nextSong", sqlMap);

        logSql("minhang_party_song", "nextSong", sqlPara);
        
        List<MinhangPartySong> minhang_party_songList = minhang_party_song.find(sqlPara.getSql(), sqlPara.getPara());
        
        if (minhang_party_songList == null || minhang_party_songList.size() == 0) {
           return null; 
        }

        return minhang_party_songList.get(0);
    }

}