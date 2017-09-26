package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.page.service.PageService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/article")
public class XietongArticleController extends Controller {
    
    //发布审核文章
    @ActionKey("/admin/xietong/article/publish")
    public void publish() {
        validateRequest(Article.ARTICLE_ID);

        Article model = getModel(Article.class);
        String request_app_id = getRequest_app_id();

        PageService.instance.writeWzxq(request_app_id, model.getArticle_id());

        renderSuccessJson();
    }

}
