package com.nowui.chuangshi.api.xietong.admin;

import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.nowui.chuangshi.api.advertisement.service.AdvertisementService;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/article")
public class XietongArticleController extends Controller {
    
    private static Engine engine = Engine.create("article_page_engine");
    
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
            writeWzxq(request_app_id, article_id);
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
            writeWzxq(request_app_id, article_id);
        }
        renderSuccessJson(result);
    }
    
    /**
     * 根据文章详情模板生成文章详情页面
     * @param app_id
     * @param article_id
     */
    private void writeWzxq(String app_id, String article_id) {
        Article article = ArticleService.instance.find(article_id);
        
        //文章非外部链接生成文章详情页面
        if (!article.getArticle_is_outer_link()) {
            
            ArticleCategory articleCategory = ArticleCategoryService.instance.find(article.getArticle_category_id());
            
            List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(app_id);

            List<Map<String, Object>> websiteMenuList = WebsiteMenuService.instance.tree(app_id);
            
            List<Map<String, Object>> indexBannerList = AdvertisementService.instance.adminIndexBannerList(app_id);
            
            Article prevArticle = ArticleService.instance.prevArticle(article_id);
            
            Article nextArticle = ArticleService.instance.nextArticle(article_id);

            Kv templateMap = Kv.create();
            templateMap.put("host", Config.host);
            templateMap.put("articleCategoryList", articleCategoryList);
            templateMap.put("article", article);
            templateMap.put("articleCategory", articleCategory);
            templateMap.put("websiteMenuList", websiteMenuList);
            templateMap.put("indexBannerList", indexBannerList);
            templateMap.put("prevArticle", prevArticle);
            templateMap.put("nextArticle", nextArticle);
            
            engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/xietong/");

            Template template = engine.getTemplate("wzxq.template");

            String content = template.renderToString(templateMap);

            App app = AppService.instance.find(app_id);

            if (ValidateUtil.isNullOrEmpty(app.getApp_website_path())) {
                throw new RuntimeException("路径不能为空");
            }

            FileUtil.writeFile(content, app.getApp_website_path() + article.getArticle_id() + ".html");
            
        }
        
    }

}
