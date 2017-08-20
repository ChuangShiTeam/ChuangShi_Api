package com.nowui.chuangshi.api.jiangling.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingGame;
import com.nowui.chuangshi.api.jiangling.service.JianglingGameService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/jiangling/game")
public class JianglingGameController extends Controller {

    @ActionKey("/admin/jiangling/game/list")
    public void list() {
        validateRequest(JianglingGame.SYSTEM_CREATE_TIME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        JianglingGame model = getModel(JianglingGame.class);
        Cnd cnd = Cnd.where(JianglingGame.APP_ID, model.getApp_id());

        Integer resultCount = JianglingGameService.me.count(cnd);
        List<JianglingGame> resultList = JianglingGameService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(JianglingGame.GAME_ID, JianglingGame.SYSTEM_CREATE_TIME, JianglingGame.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/game/find")
    public void find() {
        validateRequest(JianglingGame.GAME_ID);

        JianglingGame model = getModel(JianglingGame.class);

        JianglingGame result = JianglingGameService.me.findById(model.getGame_id());

        validateResponse(JianglingGame.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/game/save")
    public void save() {
        validateRequest();

        JianglingGame model = getModel(JianglingGame.class);
        model.setGame_id(Util.getRandomUUID());

        Boolean result = JianglingGameService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/game/update")
    public void update() {
        validateRequest(JianglingGame.GAME_ID, JianglingGame.SYSTEM_VERSION);

        JianglingGame model = getModel(JianglingGame.class);

        Boolean result = JianglingGameService.me.update(model, Cnd.where(model.GAME_ID, model.getGame_id()).and(JianglingGame.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/game/delete")
    public void delete() {
        validateRequest(JianglingGame.GAME_ID, JianglingGame.SYSTEM_VERSION);

        JianglingGame model = getModel(JianglingGame.class);

        Boolean result = JianglingGameService.me.delete(model, Cnd.where(model.GAME_ID, model.getGame_id()).and(JianglingGame.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}