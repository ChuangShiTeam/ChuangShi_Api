package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangPoster;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.service.MinhangPosterService;
import com.nowui.chuangshi.api.minhang.service.MinhangTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/poster")
public class MinhangPosterController extends Controller {

    @ActionKey("/mobile/minhang/poster/list")
    public void list() {
        String request_app_id = getRequest_app_id();
        
        List<MinhangPoster> minhang_poster_list = MinhangPosterService.instance.appList(request_app_id);
        
        for (MinhangPoster minhangPoster : minhang_poster_list) {
            minhangPoster.put(MinhangPoster.POSTER_IMAGE_FILE, FileService.instance.getFile(minhangPoster.getPoster_image()));
            MinhangTask minhangTask = MinhangTaskService.instance.find(minhangPoster.getTask_id());
            minhangPoster.put(MinhangTask.TASK_QRCODE_URL, minhangTask.getTask_qrcode_url());
        }
        
        validateResponse(MinhangPoster.POSTER_ID, MinhangPoster.TASK_ID, MinhangTask.TASK_QRCODE_URL, MinhangPoster.POSTER_IMAGE, MinhangPoster.POSTER_IMAGE_FILE, MinhangPoster.POSTER_TITLE, MinhangPoster.POSTER_CONTENT, MinhangPoster.SYSTEM_VERSION);

        renderSuccessJson(minhang_poster_list);
    }

    @ActionKey("/mobile/minhang/poster/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/poster/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/poster/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/poster/delete")
    public void delete() {

        renderSuccessJson();
    }

}