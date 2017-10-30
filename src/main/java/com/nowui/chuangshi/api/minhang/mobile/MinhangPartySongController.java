package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangPartySong;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.service.MinhangPartySongService;
import com.nowui.chuangshi.api.minhang.service.MinhangTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/party/song")
public class MinhangPartySongController extends Controller {

    @ActionKey("/mobile/minhang/party/song/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/song/find")
    public void find() {

        renderSuccessJson();
    }
    
    /**
     * 随机获取一首歌曲
     */
    @ActionKey("/mobile/minhang/party/song/random/find")
    public void randomFind() {
        String request_app_id = getRequest_app_id();
        
        MinhangPartySong minhang_party_song = MinhangPartySongService.instance.randomFind(request_app_id);
        
        MinhangTask minhangTask = MinhangTaskService.instance.find(minhang_party_song.getTask_id());
        minhang_party_song.put(MinhangTask.TASK_QRCODE_URL, minhangTask.getTask_qrcode_url());
        
        validateResponse(MinhangPartySong.PARTY_SONG_ID, MinhangTask.TASK_QRCODE_URL, MinhangPartySong.TASK_ID,  MinhangPartySong.PARTY_SONG_URL, MinhangPartySong.PARTY_SONG_CONTENT, MinhangPartySong.SYSTEM_VERSION);
        
        renderSuccessJson(minhang_party_song);
    }
    
    /**
     * 上一首歌曲
     */
    @ActionKey("/mobile/minhang/party/song/prev")
    public void prev() {
        validateRequest(MinhangPartySong.PARTY_SONG_ID);
        String request_app_id = getRequest_app_id();
        
        MinhangPartySong model = getModel(MinhangPartySong.class);
        
        MinhangPartySong bean = MinhangPartySongService.instance.find(model.getParty_song_id());
        
        MinhangPartySong minhang_party_song = MinhangPartySongService.instance.prevSong(request_app_id, bean.getSystem_create_time());
        
        MinhangTask minhangTask = MinhangTaskService.instance.find(minhang_party_song.getTask_id());
        minhang_party_song.put(MinhangTask.TASK_QRCODE_URL, minhangTask.getTask_qrcode_url());
        
        validateResponse(MinhangPartySong.PARTY_SONG_ID, MinhangTask.TASK_QRCODE_URL, MinhangPartySong.TASK_ID,  MinhangPartySong.PARTY_SONG_URL, MinhangPartySong.PARTY_SONG_CONTENT, MinhangPartySong.SYSTEM_VERSION);
        
        renderSuccessJson(minhang_party_song);
    }
    
    /**
     * 下一首歌曲
     */
    @ActionKey("/mobile/minhang/party/song/next")
    public void next() {
        validateRequest(MinhangPartySong.PARTY_SONG_ID);
        String request_app_id = getRequest_app_id();
        
        MinhangPartySong model = getModel(MinhangPartySong.class);
        
        MinhangPartySong bean = MinhangPartySongService.instance.find(model.getParty_song_id());
        
        MinhangPartySong minhang_party_song = MinhangPartySongService.instance.nextSong(request_app_id, bean.getSystem_create_time());
        
        MinhangTask minhangTask = MinhangTaskService.instance.find(minhang_party_song.getTask_id());
        minhang_party_song.put(MinhangTask.TASK_QRCODE_URL, minhangTask.getTask_qrcode_url());
        
        validateResponse(MinhangPartySong.PARTY_SONG_ID, MinhangTask.TASK_QRCODE_URL, MinhangPartySong.TASK_ID,  MinhangPartySong.PARTY_SONG_URL, MinhangPartySong.PARTY_SONG_CONTENT, MinhangPartySong.SYSTEM_VERSION);
        
        renderSuccessJson(minhang_party_song);
    }

    @ActionKey("/mobile/minhang/party/song/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/song/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/party/song/delete")
    public void delete() {

        renderSuccessJson();
    }

}