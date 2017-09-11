package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.page.service.PageService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/article")
public class XietongArticleController extends Controller {
    
    @ActionKey("/admin/xietong/article/save")
    public void save() {
        validateRequest(Article.ARTICLE_CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.ARTICLE_CONTENT, Article.ARTICLE_OUTER_LINK, Article.ARTICLE_IS_OUTER_LINK);

        Article model = getModel(Article.class);
        String article_id = Util.getRandomUUID();
        model.setArticle_id(article_id);
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();

        Boolean result = ArticleService.instance.save(model, request_user_id);
        
        if (result) {
            //生成文章详情页面
            PageService.instance.writeWzxq(request_app_id, article_id);
        }

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/article/update")
    public void update() {
        validateRequest(Article.ARTICLE_ID, Article.ARTICLE_CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.ARTICLE_CONTENT, Article.ARTICLE_OUTER_LINK, Article.ARTICLE_IS_OUTER_LINK, Article.SYSTEM_VERSION);

        Article model = getModel(Article.class);
        String article_id = model.getArticle_id();
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();

        Boolean result = ArticleService.instance.update(model, model.getArticle_id(), request_user_id, model.getSystem_version());

        if (result) {
            //生成文章详情页面
            PageService.instance.writeWzxq(request_app_id, article_id);
        }
        renderSuccessJson(result);
    }
    
    
}
