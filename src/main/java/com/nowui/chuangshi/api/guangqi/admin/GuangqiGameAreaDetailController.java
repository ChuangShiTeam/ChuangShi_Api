package com.nowui.chuangshi.api.guangqi.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.guangqi.model.GuangqiGameAreaDetail;
import com.nowui.chuangshi.api.guangqi.service.GuangqiGameAreaDetailService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/guangqi/game/area/detail")
public class GuangqiGameAreaDetailController extends Controller {

    @ActionKey("/admin/guangqi/game/area/detail/list")
    public void list() {
        validateRequest(GuangqiGameAreaDetail.GAME_AREA_ID, GuangqiGameAreaDetail.GAME_AREA_DETAIL_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiGameAreaDetail model = getModel(GuangqiGameAreaDetail.class);

        Integer resultCount = GuangqiGameAreaDetailService.instance.adminCount(model.getGame_area_id(), model.getGame_area_detail_type());
        List<GuangqiGameAreaDetail> resultList = GuangqiGameAreaDetailService.instance.adminList(model.getGame_area_id(), model.getGame_area_detail_type(), getM(), getN());

        for (GuangqiGameAreaDetail result : resultList) {
        	result.put(GuangqiGameAreaDetail.GAME_AREA_DETAIL_IMAGE_FILE, FileService.instance.getFile(result.getGame_area_detail_image()));
        }
        
        validateResponse(GuangqiGameAreaDetail.GAME_AREA_DETAIL_ID, GuangqiGameAreaDetail.GAME_AREA_DETAIL_IMAGE_FILE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_TYPE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_IMAGE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_VIDEO, GuangqiGameAreaDetail.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/guangqi/game/area/detail/find")
    public void find() {
        validateRequest(GuangqiGameAreaDetail.GAME_AREA_DETAIL_ID);

        GuangqiGameAreaDetail model = getModel(GuangqiGameAreaDetail.class);

        GuangqiGameAreaDetail result = GuangqiGameAreaDetailService.instance.find(model.getGame_area_detail_id());

    	result.put(GuangqiGameAreaDetail.GAME_AREA_DETAIL_IMAGE_FILE, FileService.instance.getFile(result.getGame_area_detail_image()));

        validateResponse(GuangqiGameAreaDetail.GAME_AREA_ID, GuangqiGameAreaDetail.GAME_AREA_DETAIL_IMAGE_FILE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_TYPE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_IMAGE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_VIDEO, GuangqiGameAreaDetail.GAME_AREA_DETAIL_SORT, GuangqiGameAreaDetail.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/game/area/detail/save")
    public void save() {
        validateRequest(GuangqiGameAreaDetail.GAME_AREA_ID, GuangqiGameAreaDetail.GAME_AREA_DETAIL_TYPE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_IMAGE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_VIDEO, GuangqiGameAreaDetail.GAME_AREA_DETAIL_SORT);

        GuangqiGameAreaDetail model = getModel(GuangqiGameAreaDetail.class);
        model.setGame_area_detail_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiGameAreaDetailService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/game/area/detail/update")
    public void update() {
        validateRequest(GuangqiGameAreaDetail.GAME_AREA_DETAIL_ID, GuangqiGameAreaDetail.GAME_AREA_ID, GuangqiGameAreaDetail.GAME_AREA_DETAIL_TYPE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_IMAGE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_VIDEO, GuangqiGameAreaDetail.GAME_AREA_DETAIL_SORT, GuangqiGameAreaDetail.SYSTEM_VERSION);

        GuangqiGameAreaDetail model = getModel(GuangqiGameAreaDetail.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiGameAreaDetailService.instance.update(model, model.getGame_area_detail_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/game/area/detail/delete")
    public void delete() {
        validateRequest(GuangqiGameAreaDetail.GAME_AREA_DETAIL_ID, GuangqiGameAreaDetail.SYSTEM_VERSION);

        GuangqiGameAreaDetail model = getModel(GuangqiGameAreaDetail.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiGameAreaDetailService.instance.delete(model.getGame_area_detail_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}