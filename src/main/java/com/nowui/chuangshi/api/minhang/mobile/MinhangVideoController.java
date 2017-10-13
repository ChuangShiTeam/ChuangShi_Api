package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangVideo;
import com.nowui.chuangshi.api.minhang.service.MinhangVideoService;
import com.nowui.chuangshi.api.minhang.service.MinhangVideoTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/mobile/minhang/video")
public class MinhangVideoController extends Controller {

    @ActionKey("/mobile/minhang/video/list")
    public void list() {
    	validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
    	
    	String request_app_id = getRequest_app_id();
        
    	Integer resultCount = MinhangVideoService.instance.mobileCount(request_app_id);
        List<MinhangVideo> resultList = MinhangVideoService.instance.mobileList(request_app_id, getM(), getN());
        for (MinhangVideo result : resultList) {
            result.put(MinhangVideo.VIDEO_TASK_LIST, MinhangVideoTaskService.instance.videoList(result.getVideo_id()));
        }
        validateResponse(MinhangVideo.VIDEO_ID, MinhangVideo.VIDEO_TASK_LIST, MinhangVideo.VIDEO_TITLE, MinhangVideo.VIDEO_URL, MinhangVideo.VIDEO_SORT, MinhangVideo.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/mobile/minhang/video/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/video/delete")
    public void delete() {

        renderSuccessJson();
    }

}