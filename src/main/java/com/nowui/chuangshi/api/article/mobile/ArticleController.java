package com.nowui.chuangshi.api.article.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/article")
public class ArticleController extends Controller {

    @ActionKey("/mobile/article/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/delete")
    public void delete() {

        renderSuccessJson();
    }

}