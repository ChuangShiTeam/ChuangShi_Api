package com.nowui.chuangshi.api.article.system;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/system/article")
public class ArticleController extends Controller {

    private final ArticleService articleService = new ArticleService();

    @ActionKey("/system/article/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/article/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/article/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/article/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/article/delete")
    public void delete() {

        renderSuccessJson();
    }

}