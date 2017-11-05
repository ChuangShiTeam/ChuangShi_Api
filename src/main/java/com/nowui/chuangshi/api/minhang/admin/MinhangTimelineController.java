package com.nowui.chuangshi.api.minhang.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangTimeline;
import com.nowui.chuangshi.api.minhang.service.MinhangTimelineService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/timeline")
public class MinhangTimelineController extends Controller {

    @ActionKey("/admin/minhang/timeline/list")
    public void list() {
        validateRequest(MinhangTimeline.TIMELINE_YEAR, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangTimeline model = getModel(MinhangTimeline.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangTimelineService.instance.adminCount(request_app_id, model.getTimeline_year());
        List<MinhangTimeline> resultList = MinhangTimelineService.instance.adminList(request_app_id, model.getTimeline_year(), getM(), getN());

        for (MinhangTimeline minhangTimeline : resultList) {
            minhangTimeline.put(MinhangTimeline.TIMELINE_IMAGE_FILE, FileService.instance.getFile(minhangTimeline.getTimeline_image()));
        }
        
        validateResponse(MinhangTimeline.TIMELINE_ID, MinhangTimeline.TIMELINE_YEAR, MinhangTimeline.TIMELINE_IMAGE, MinhangTimeline.TIMELINE_IMAGE_FILE, MinhangTimeline.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/timeline/find")
    public void find() {
        validateRequest(MinhangTimeline.TIMELINE_ID);

        MinhangTimeline model = getModel(MinhangTimeline.class);

        MinhangTimeline result = MinhangTimelineService.instance.find(model.getTimeline_id());
        
        result.put(MinhangTimeline.TIMELINE_IMAGE_FILE, FileService.instance.getFile(result.getTimeline_image()));

        validateResponse(MinhangTimeline.TIMELINE_YEAR, MinhangTimeline.TIMELINE_IMAGE, MinhangTimeline.TIMELINE_IMAGE_FILE, MinhangTimeline.TIMELINE_DESCRIPTION, MinhangTimeline.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/timeline/save")
    public void save() {
        validateRequest(MinhangTimeline.TIMELINE_YEAR, MinhangTimeline.TIMELINE_IMAGE, MinhangTimeline.TIMELINE_DESCRIPTION);

        MinhangTimeline model = getModel(MinhangTimeline.class);
        model.setTimeline_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTimelineService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/timeline/update")
    public void update() {
        validateRequest(MinhangTimeline.TIMELINE_ID, MinhangTimeline.TIMELINE_YEAR, MinhangTimeline.TIMELINE_IMAGE, MinhangTimeline.TIMELINE_DESCRIPTION, MinhangTimeline.SYSTEM_VERSION);

        MinhangTimeline model = getModel(MinhangTimeline.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTimelineService.instance.update(model, model.getTimeline_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/timeline/delete")
    public void delete() {
        validateRequest(MinhangTimeline.TIMELINE_ID, MinhangTimeline.SYSTEM_VERSION);

        MinhangTimeline model = getModel(MinhangTimeline.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTimelineService.instance.delete(model.getTimeline_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}