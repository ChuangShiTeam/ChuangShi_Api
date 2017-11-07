package com.nowui.chuangshi.api.jiangling.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingGame;
import com.nowui.chuangshi.api.jiangling.service.JianglingGameService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/jiangling/game")
public class JianglingGameController extends Controller {

    @ActionKey("/admin/jiangling/game/list")
    public void list() {
        validateRequest(JianglingGame.SYSTEM_CREATE_TIME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        Integer resultCount = JianglingGameService.instance.adminCount(request_app_id);
        List<JianglingGame> resultList = JianglingGameService.instance.adminList(request_app_id, getM(), getN());

        validateResponse(JianglingGame.GAME_ID, JianglingGame.SYSTEM_CREATE_TIME, JianglingGame.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/game/find")
    public void find() {
        validateRequest(JianglingGame.GAME_ID);

        JianglingGame model = getModel(JianglingGame.class);

        JianglingGame result = JianglingGameService.instance.find(model.getGame_id());

        validateResponse(JianglingGame.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/game/save")
    public void save() {
        validateRequest();

        JianglingGame model = getModel(JianglingGame.class);
        model.setGame_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingGameService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/game/update")
    public void update() {
        validateRequest(JianglingGame.GAME_ID, JianglingGame.SYSTEM_VERSION);

        JianglingGame model = getModel(JianglingGame.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingGameService.instance.update(model, model.getGame_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/game/delete")
    public void delete() {
        validateRequest(JianglingGame.GAME_ID, JianglingGame.SYSTEM_VERSION);

        JianglingGame model = getModel(JianglingGame.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingGameService.instance.delete(model.getGame_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}