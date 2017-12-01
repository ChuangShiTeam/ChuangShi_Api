package com.nowui.chuangshi.api.guangqi.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.guangqi.model.GuangqiGameArea;
import com.nowui.chuangshi.api.guangqi.service.GuangqiGameAreaService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/guangqi/game/area")
public class GuangqiGameAreaController extends Controller {

    @ActionKey("/admin/guangqi/game/area/list")
    public void list() {
        validateRequest(GuangqiGameArea.GAME_AREA_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiGameArea model = getModel(GuangqiGameArea.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GuangqiGameAreaService.instance.adminCount(request_app_id, model.getGame_area_name());
        List<GuangqiGameArea> resultList = GuangqiGameAreaService.instance.adminList(request_app_id, model.getGame_area_name(), getM(), getN());
        
        for (GuangqiGameArea guangqiGameArea : resultList) {
        	guangqiGameArea.put(GuangqiGameArea.GAME_AREA_COVER_PICTURE_FILE, FileService.instance.getFile(guangqiGameArea.getGame_area_cover_picture()));
        }

        validateResponse(GuangqiGameArea.GAME_AREA_ID, GuangqiGameArea.GAME_AREA_NAME, GuangqiGameArea.GAME_AREA_COVER_PICTURE_FILE, GuangqiGameArea.GAME_AREA_COVER_PICTURE, GuangqiGameArea.GAME_AREA_SORT, GuangqiGameArea.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/guangqi/game/area/find")
    public void find() {
        validateRequest(GuangqiGameArea.GAME_AREA_ID);

        GuangqiGameArea model = getModel(GuangqiGameArea.class);

        GuangqiGameArea result = GuangqiGameAreaService.instance.find(model.getGame_area_id());
        
        result.put(GuangqiGameArea.GAME_AREA_COVER_PICTURE_FILE, FileService.instance.getFile(result.getGame_area_cover_picture()));

        validateResponse(GuangqiGameArea.GAME_AREA_NAME, GuangqiGameArea.GAME_AREA_COVER_PICTURE, GuangqiGameArea.GAME_AREA_COVER_PICTURE_FILE, GuangqiGameArea.GAME_AREA_SORT, GuangqiGameArea.GAME_AREA_DESCRIPTION, GuangqiGameArea.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/game/area/save")
    public void save() {
        validateRequest(GuangqiGameArea.GAME_AREA_NAME, GuangqiGameArea.GAME_AREA_COVER_PICTURE, GuangqiGameArea.GAME_AREA_SORT, GuangqiGameArea.GAME_AREA_DESCRIPTION);

        GuangqiGameArea model = getModel(GuangqiGameArea.class);
        model.setGame_area_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiGameAreaService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/game/area/update")
    public void update() {
        validateRequest(GuangqiGameArea.GAME_AREA_ID, GuangqiGameArea.GAME_AREA_NAME, GuangqiGameArea.GAME_AREA_COVER_PICTURE, GuangqiGameArea.GAME_AREA_SORT, GuangqiGameArea.GAME_AREA_DESCRIPTION, GuangqiGameArea.SYSTEM_VERSION);

        GuangqiGameArea model = getModel(GuangqiGameArea.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiGameAreaService.instance.update(model, model.getGame_area_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/game/area/delete")
    public void delete() {
        validateRequest(GuangqiGameArea.GAME_AREA_ID, GuangqiGameArea.SYSTEM_VERSION);

        GuangqiGameArea model = getModel(GuangqiGameArea.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiGameAreaService.instance.delete(model.getGame_area_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}