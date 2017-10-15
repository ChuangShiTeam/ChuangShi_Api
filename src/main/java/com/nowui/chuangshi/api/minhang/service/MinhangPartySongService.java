package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangPartySongDao;
import com.nowui.chuangshi.api.minhang.model.MinhangPartySong;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;
import java.util.Random;

public class MinhangPartySongService extends Service {

    public static final MinhangPartySongService instance = new MinhangPartySongService();
    private final String MINHANG_PARTY_SONG_ITEM_CACHE = "minhang_party_song_item_cache";
    private final MinhangPartySongDao minhangPartySongDao = new MinhangPartySongDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartySong.SYSTEM_STATUS, true);
        cnd.and(MinhangPartySong.APP_ID, app_id);

        Integer count = minhangPartySongDao.count(cnd);
        return count;
    }

    public List<MinhangPartySong> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartySong.SYSTEM_STATUS, true);
        cnd.and(MinhangPartySong.APP_ID, app_id);
        cnd.paginate(m, n);

        List<MinhangPartySong> minhang_party_songList = minhangPartySongDao.primaryKeyList(cnd);
        for (MinhangPartySong minhang_party_song : minhang_party_songList) {
            minhang_party_song.put(find(minhang_party_song.getParty_song_id()));
        }
        return minhang_party_songList;
    }

    public MinhangPartySong find(String party_song_id) {
        MinhangPartySong minhang_party_song = CacheUtil.get(MINHANG_PARTY_SONG_ITEM_CACHE, party_song_id);

        if (minhang_party_song == null) {
            minhang_party_song = minhangPartySongDao.find(party_song_id);

            CacheUtil.put(MINHANG_PARTY_SONG_ITEM_CACHE, party_song_id, minhang_party_song);
        }

        return minhang_party_song;
    }
    
    public MinhangPartySong randomFind(String app_id) {
    	Cnd cnd = new Cnd();
        cnd.where(MinhangPartySong.SYSTEM_STATUS, true);
        cnd.and(MinhangPartySong.APP_ID, app_id);

        List<MinhangPartySong> minhang_party_songList = minhangPartySongDao.primaryKeyList(cnd);
        
        if (minhang_party_songList == null || minhang_party_songList.size() == 0) {
        	return new MinhangPartySong();
        }
        
        MinhangPartySong minhang_party_song = minhang_party_songList.get(new Random().nextInt(minhang_party_songList.size()));
        
        return find(minhang_party_song.getParty_song_id());
    }
    
    public List<MinhangPartySong> taskFind(String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartySong.SYSTEM_STATUS, true);
        cnd.and(MinhangPartySong.TASK_ID, task_id);
        
        List<MinhangPartySong> minhang_party_songList = minhangPartySongDao.primaryKeyList(cnd);
        for (MinhangPartySong minhang_party_song : minhang_party_songList) {
            minhang_party_song.put(find(minhang_party_song.getParty_song_id()));
        }
        return minhang_party_songList;
    }

    public Boolean save(MinhangPartySong minhang_party_song, String system_create_user_id) {
        Boolean success = minhangPartySongDao.save(minhang_party_song, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangPartySong minhang_party_song, String party_song_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartySong.SYSTEM_STATUS, true);
        cnd.and(MinhangPartySong.PARTY_SONG_ID, party_song_id);
        cnd.and(MinhangPartySong.SYSTEM_VERSION, system_version);

        Boolean success = minhangPartySongDao.update(minhang_party_song, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_PARTY_SONG_ITEM_CACHE, party_song_id);
        }

        return success;
    }

    public Boolean delete(String party_song_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartySong.SYSTEM_STATUS, true);
        cnd.and(MinhangPartySong.PARTY_SONG_ID, party_song_id);
        cnd.and(MinhangPartySong.SYSTEM_VERSION, system_version);

        Boolean success = minhangPartySongDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_PARTY_SONG_ITEM_CACHE, party_song_id);
        }

        return success;
    }

}