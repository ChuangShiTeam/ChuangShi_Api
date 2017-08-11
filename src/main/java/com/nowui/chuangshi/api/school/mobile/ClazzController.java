package com.nowui.chuangshi.api.school.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.ClazzService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/clazz")
public class ClazzController extends Controller {

    private final ClazzService clazzService = new ClazzService();

    @ActionKey("/mobile/clazz/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/clazz/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/clazz/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/clazz/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/clazz/delete")
    public void delete() {

        renderSuccessJson();
    }

}