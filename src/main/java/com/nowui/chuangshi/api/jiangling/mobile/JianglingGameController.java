package com.nowui.chuangshi.api.jiangling.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/jiangling/game")
public class JianglingGameController extends Controller {

    @ActionKey("/mobile/jiangling/game/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/game/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/game/save")
    public void save() {
        validateRequest("token_list");

        JSONObject jsonObject = getParameterJSONObject();
        JSONArray jsonArray = jsonObject.getJSONArray("token_list");

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject tokenJsonObject = jsonArray.getJSONObject(i);

        }

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/game/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/game/delete")
    public void delete() {

        renderSuccessJson();
    }

}