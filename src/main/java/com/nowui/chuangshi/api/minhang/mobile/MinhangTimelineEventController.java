package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/timeline/event")
public class MinhangTimelineEventController extends Controller {

    @ActionKey("/mobile/minhang/timeline/event/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/event/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/event/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/event/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/timeline/event/delete")
    public void delete() {

        renderSuccessJson();
    }

}