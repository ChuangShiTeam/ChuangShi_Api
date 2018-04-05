package com.nowui.chuangshi.api.jiangling.admin;


import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingNewGame;
import com.nowui.chuangshi.api.jiangling.service.JianglingNewGameService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;


@ControllerKey("/admin/jiangling/new/game")
public class JianglingNewGameController extends Controller {

    @ActionKey("/admin/jiangling/new/game/list")
    public void list() {
        validateRequest(JianglingNewGame.SYSTEM_CREATE_TIME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        Integer resultCount = JianglingNewGameService.instance.adminCount(request_app_id);
        List<JianglingNewGame> resultList = JianglingNewGameService.instance.adminList(request_app_id, getM(), getN());

        validateResponse(JianglingNewGame.NEW_GAME_ID, JianglingNewGame.SYSTEM_CREATE_TIME, JianglingNewGame.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/new/game/find")
    public void find() {
        validateRequest(JianglingNewGame.NEW_GAME_ID);

        JianglingNewGame model = getModel(JianglingNewGame.class);

        JianglingNewGame result = JianglingNewGameService.instance.find(model.getNew_game_id());

        validateResponse(JianglingNewGame.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/new/game/save")
    public void save() {
        validateRequest();

        JianglingNewGame model = getModel(JianglingNewGame.class);
        model.setNew_game_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingNewGameService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/new/game/update")
    public void update() {
        validateRequest(JianglingNewGame.NEW_GAME_ID, JianglingNewGame.SYSTEM_VERSION);

        JianglingNewGame model = getModel(JianglingNewGame.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingNewGameService.instance.update(model, model.getNew_game_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/new/game/delete")
    public void delete() {
        validateRequest(JianglingNewGame.NEW_GAME_ID, JianglingNewGame.SYSTEM_VERSION);

        JianglingNewGame model = getModel(JianglingNewGame.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingNewGameService.instance.delete(model.getNew_game_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}