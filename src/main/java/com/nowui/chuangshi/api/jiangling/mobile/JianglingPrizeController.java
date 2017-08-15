package com.nowui.chuangshi.api.jiangling.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.service.JianglingPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/jiangling/prize")
public class JianglingPrizeController extends Controller {

    private final JianglingPrizeService jianglingPrizeService = new JianglingPrizeService();

    @ActionKey("/mobile/jiangling/prize/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/prize/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/prize/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/prize/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/prize/delete")
    public void delete() {

        renderSuccessJson();
    }

}