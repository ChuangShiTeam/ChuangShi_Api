package com.nowui.chuangshi.api.guangqi.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.guangqi.model.GuangqiWonderfulShow;
import com.nowui.chuangshi.api.guangqi.service.GuangqiWonderfulShowService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/guangqi/wonderful/show")
public class GuangqiWonderfulShowController extends Controller {

    @ActionKey("/admin/guangqi/wonderful/show/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        Integer resultCount = GuangqiWonderfulShowService.instance.adminCount(request_app_id);
        List<GuangqiWonderfulShow> resultList = GuangqiWonderfulShowService.instance.adminList(request_app_id, getM(), getN());

        for (GuangqiWonderfulShow result : resultList) {
        	result.put(GuangqiWonderfulShow.WONDERFUL_SHOW_COVER_PICTURE_FILE, FileService.instance.getFile(result.getWonderful_show_cover_picture()));
        }
        
        validateResponse(GuangqiWonderfulShow.WONDERFUL_SHOW_ID, GuangqiWonderfulShow.WONDERFUL_SHOW_COVER_PICTURE_FILE, GuangqiWonderfulShow.WONDERFUL_SHOW_COVER_PICTURE, GuangqiWonderfulShow.WONDERFUL_SHOW_VIDEO, GuangqiWonderfulShow.WONDERFUL_SHOW_SORT, GuangqiWonderfulShow.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/guangqi/wonderful/show/find")
    public void find() {
        validateRequest(GuangqiWonderfulShow.WONDERFUL_SHOW_ID);

        GuangqiWonderfulShow model = getModel(GuangqiWonderfulShow.class);

        GuangqiWonderfulShow result = GuangqiWonderfulShowService.instance.find(model.getWonderful_show_id());

    	result.put(GuangqiWonderfulShow.WONDERFUL_SHOW_COVER_PICTURE_FILE, FileService.instance.getFile(result.getWonderful_show_cover_picture()));

        validateResponse(GuangqiWonderfulShow.WONDERFUL_SHOW_COVER_PICTURE, GuangqiWonderfulShow.WONDERFUL_SHOW_COVER_PICTURE_FILE, GuangqiWonderfulShow.WONDERFUL_SHOW_VIDEO, GuangqiWonderfulShow.WONDERFUL_SHOW_SORT, GuangqiWonderfulShow.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/wonderful/show/save")
    public void save() {
        validateRequest(GuangqiWonderfulShow.WONDERFUL_SHOW_COVER_PICTURE, GuangqiWonderfulShow.WONDERFUL_SHOW_VIDEO, GuangqiWonderfulShow.WONDERFUL_SHOW_SORT);

        GuangqiWonderfulShow model = getModel(GuangqiWonderfulShow.class);
        model.setWonderful_show_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiWonderfulShowService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/wonderful/show/update")
    public void update() {
        validateRequest(GuangqiWonderfulShow.WONDERFUL_SHOW_ID, GuangqiWonderfulShow.WONDERFUL_SHOW_COVER_PICTURE, GuangqiWonderfulShow.WONDERFUL_SHOW_VIDEO, GuangqiWonderfulShow.WONDERFUL_SHOW_SORT, GuangqiWonderfulShow.SYSTEM_VERSION);

        GuangqiWonderfulShow model = getModel(GuangqiWonderfulShow.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiWonderfulShowService.instance.update(model, model.getWonderful_show_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/wonderful/show/delete")
    public void delete() {
        validateRequest(GuangqiWonderfulShow.WONDERFUL_SHOW_ID, GuangqiWonderfulShow.SYSTEM_VERSION);

        GuangqiWonderfulShow model = getModel(GuangqiWonderfulShow.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiWonderfulShowService.instance.delete(model.getWonderful_show_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}