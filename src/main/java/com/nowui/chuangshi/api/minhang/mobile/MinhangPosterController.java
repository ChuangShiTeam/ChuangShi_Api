package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangPoster;
import com.nowui.chuangshi.api.minhang.service.MinhangPosterService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/poster")
public class MinhangPosterController extends Controller {

    @ActionKey("/mobile/minhang/poster/list")
    public void list() {
    	String request_app_id = getRequest_app_id();
    	
    	List<MinhangPoster> minhang_poster_list = MinhangPosterService.instance.appList(request_app_id);
    	
    	for (MinhangPoster minhangPoster : minhang_poster_list) {
            minhangPoster.put(MinhangPoster.POSTER_IMAGE_FILE, FileService.instance.getOriginalFile(minhangPoster.getPoster_image()));
        }
        
        validateResponse(MinhangPoster.POSTER_ID, MinhangPoster.TASK_ID, MinhangPoster.POSTER_IMAGE, MinhangPoster.POSTER_IMAGE_FILE, MinhangPoster.POSTER_TITLE, MinhangPoster.POSTER_CONTENT, MinhangPoster.SYSTEM_VERSION);

    	
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