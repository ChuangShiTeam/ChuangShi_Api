package com.nowui.chuangshi.api.minhang.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangTimeline;
import com.nowui.chuangshi.api.minhang.service.MinhangTimelineService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/timeline")
public class MinhangTimelineController extends Controller {

    @ActionKey("/mobile/minhang/timeline/list")
    public void list() {
        
        String request_app_id = getRequest_app_id();

        List<MinhangTimeline> resultList = MinhangTimelineService.instance.appList(request_app_id);

        for (MinhangTimeline minhangTimeline : resultList) {
            minhangTimeline.put(MinhangTimeline.TIMELINE_IMAGE_FILE, FileService.instance.getFile(minhangTimeline.getTimeline_image()));
        }
        
        validateResponse(MinhangTimeline.TIMELINE_ID, MinhangTimeline.TIMELINE_YEAR, MinhangTimeline.TIMELINE_IMAGE, MinhangTimeline.TIMELINE_IMAGE_FILE, MinhangTimeline.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/minhang/timeline/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/delete")
    public void delete() {

        renderSuccessJson();
    }

}