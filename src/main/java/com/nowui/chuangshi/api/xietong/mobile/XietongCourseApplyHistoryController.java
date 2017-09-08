package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/course/apply/history")
public class XietongCourseApplyHistoryController extends Controller {

    @ActionKey("/mobile/xietong/course/apply/history/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/apply/history/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/apply/history/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/apply/history/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/apply/history/delete")
    public void delete() {

        renderSuccessJson();
    }

}