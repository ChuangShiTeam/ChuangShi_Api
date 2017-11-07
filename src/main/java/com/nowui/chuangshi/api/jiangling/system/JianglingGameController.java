package com.nowui.chuangshi.api.jiangling.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/jiangling/game")
public class JianglingGameController extends Controller {

    @ActionKey("/system/jiangling/game/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/game/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/game/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/game/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/game/delete")
    public void delete() {

        renderSuccessJson();
    }

}