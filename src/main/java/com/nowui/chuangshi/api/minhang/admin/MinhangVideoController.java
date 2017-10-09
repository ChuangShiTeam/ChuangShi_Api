package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangVideo;
import com.nowui.chuangshi.api.minhang.service.MinhangVideoService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/video")
public class MinhangVideoController extends Controller {

    @ActionKey("/admin/minhang/video/list")
    public void list() {
        validateRequest(MinhangVideo.VIDEO_TITLE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangVideo model = getModel(MinhangVideo.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangVideoService.instance.adminCount(request_app_id, model.getVideo_title());
        List<MinhangVideo> resultList = MinhangVideoService.instance.adminList(request_app_id, model.getVideo_title(), getM(), getN());

        validateResponse(MinhangVideo.VIDEO_ID, MinhangVideo.VIDEO_TITLE, MinhangVideo.VIDEO_URL, MinhangVideo.VIDEO_SORT, MinhangVideo.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/video/find")
    public void find() {
        validateRequest(MinhangVideo.VIDEO_ID);

        MinhangVideo model = getModel(MinhangVideo.class);

        MinhangVideo result = MinhangVideoService.instance.find(model.getVideo_id());

        validateResponse(MinhangVideo.VIDEO_TITLE, MinhangVideo.VIDEO_URL, MinhangVideo.VIDEO_SORT, MinhangVideo.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/video/save")
    public void save() {
        validateRequest(MinhangVideo.VIDEO_TITLE, MinhangVideo.VIDEO_URL, MinhangVideo.VIDEO_SORT);

        MinhangVideo model = getModel(MinhangVideo.class);
        model.setVideo_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangVideoService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/video/update")
    public void update() {
        validateRequest(MinhangVideo.VIDEO_ID, MinhangVideo.VIDEO_TITLE, MinhangVideo.VIDEO_URL, MinhangVideo.VIDEO_SORT, MinhangVideo.SYSTEM_VERSION);

        MinhangVideo model = getModel(MinhangVideo.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangVideoService.instance.update(model, model.getVideo_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/video/delete")
    public void delete() {
        validateRequest(MinhangVideo.VIDEO_ID, MinhangVideo.SYSTEM_VERSION);

        MinhangVideo model = getModel(MinhangVideo.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangVideoService.instance.delete(model.getVideo_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}