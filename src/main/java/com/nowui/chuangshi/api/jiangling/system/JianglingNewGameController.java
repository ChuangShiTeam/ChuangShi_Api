package com.nowui.chuangshi.api.jiangling.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/jiangling/new/game")
public class JianglingNewGameController extends Controller {

    @ActionKey("/system/jiangling/new/game/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/new/game/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/new/game/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/new/game/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/new/game/delete")
    public void delete() {

        renderSuccessJson();
    }

}