package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangPoster;
import com.nowui.chuangshi.api.minhang.service.MinhangPosterService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/poster")
public class MinhangPosterController extends Controller {

    @ActionKey("/admin/minhang/poster/list")
    public void list() {
        validateRequest(MinhangPoster.POSTER_TITLE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangPoster model = getModel(MinhangPoster.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangPosterService.instance.adminCount(request_app_id, model.getPoster_title());
        List<MinhangPoster> resultList = MinhangPosterService.instance.adminList(request_app_id, model.getPoster_title(), getM(), getN());
        for (MinhangPoster minhangPoster : resultList) {
            minhangPoster.put(MinhangPoster.POSTER_IMAGE_FILE, FileService.instance.getFile(minhangPoster.getPoster_image()));
        }
        
        validateResponse(MinhangPoster.POSTER_ID, MinhangPoster.TASK_ID, MinhangPoster.POSTER_IMAGE, MinhangPoster.POSTER_IMAGE_FILE, MinhangPoster.POSTER_TITLE, MinhangPoster.POSTER_CONTENT, MinhangPoster.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/poster/find")
    public void find() {
        validateRequest(MinhangPoster.POSTER_ID);

        MinhangPoster model = getModel(MinhangPoster.class);

        MinhangPoster result = MinhangPosterService.instance.find(model.getPoster_id());
        
        result.put(MinhangPoster.POSTER_IMAGE_FILE, FileService.instance.getFile(result.getPoster_image()));

        validateResponse(MinhangPoster.TASK_ID, MinhangPoster.POSTER_IMAGE, MinhangPoster.POSTER_IMAGE_FILE, MinhangPoster.POSTER_TITLE, MinhangPoster.POSTER_CONTENT, MinhangPoster.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/poster/save")
    public void save() {
        validateRequest(MinhangPoster.TASK_ID, MinhangPoster.POSTER_IMAGE, MinhangPoster.POSTER_TITLE, MinhangPoster.POSTER_CONTENT);

        MinhangPoster model = getModel(MinhangPoster.class);
        model.setPoster_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangPosterService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/poster/update")
    public void update() {
        validateRequest(MinhangPoster.POSTER_ID, MinhangPoster.TASK_ID, MinhangPoster.POSTER_IMAGE, MinhangPoster.POSTER_TITLE, MinhangPoster.POSTER_CONTENT, MinhangPoster.SYSTEM_VERSION);

        MinhangPoster model = getModel(MinhangPoster.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangPosterService.instance.update(model, model.getPoster_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/poster/delete")
    public void delete() {
        validateRequest(MinhangPoster.POSTER_ID, MinhangPoster.SYSTEM_VERSION);

        MinhangPoster model = getModel(MinhangPoster.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangPosterService.instance.delete(model.getPoster_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}