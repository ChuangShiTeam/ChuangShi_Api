package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangPartySong;
import com.nowui.chuangshi.api.minhang.service.MinhangPartySongService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/party/song")
public class MinhangPartySongController extends Controller {

    @ActionKey("/admin/minhang/party/song/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangPartySong model = getModel(MinhangPartySong.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangPartySongService.instance.adminCount(request_app_id);
        List<MinhangPartySong> resultList = MinhangPartySongService.instance.adminList(request_app_id, getM(), getN());

        validateResponse(MinhangPartySong.PARTY_SONG_ID, MinhangPartySong.TASK_ID, MinhangPartySong.PARTY_SONG_URL, MinhangPartySong.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/party/song/find")
    public void find() {
        validateRequest(MinhangPartySong.PARTY_SONG_ID);

        MinhangPartySong model = getModel(MinhangPartySong.class);

        MinhangPartySong result = MinhangPartySongService.instance.find(model.getParty_song_id());

        validateResponse(MinhangPartySong.TASK_ID,  MinhangPartySong.PARTY_SONG_URL, MinhangPartySong.PARTY_SONG_CONTENT, MinhangPartySong.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/party/song/save")
    public void save() {
        validateRequest(MinhangPartySong.TASK_ID, MinhangPartySong.PARTY_SONG_CONTENT, MinhangPartySong.PARTY_SONG_URL);

        MinhangPartySong model = getModel(MinhangPartySong.class);
        model.setParty_song_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangPartySongService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/party/song/update")
    public void update() {
        validateRequest(MinhangPartySong.PARTY_SONG_ID, MinhangPartySong.TASK_ID, MinhangPartySong.PARTY_SONG_CONTENT, MinhangPartySong.PARTY_SONG_URL, MinhangPartySong.SYSTEM_VERSION);

        MinhangPartySong model = getModel(MinhangPartySong.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangPartySongService.instance.update(model, model.getParty_song_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/party/song/delete")
    public void delete() {
        validateRequest(MinhangPartySong.PARTY_SONG_ID, MinhangPartySong.SYSTEM_VERSION);

        MinhangPartySong model = getModel(MinhangPartySong.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangPartySongService.instance.delete(model.getParty_song_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}