package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/timeline")
public class MinhangTimelineController extends Controller {

    @ActionKey("/mobile/minhang/timeline/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/delete")
    public void delete() {

        renderSuccessJson();
    }

}