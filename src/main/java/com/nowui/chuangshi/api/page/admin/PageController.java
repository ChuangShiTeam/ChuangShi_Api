package com.nowui.chuangshi.api.page.admin;

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
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.api.page.service.PageService;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.List;
import java.util.Map;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/page")
public class PageController extends Controller {

    private static Engine engine = Engine.create("page_engine");

    @ActionKey("/admin/page/list")
    public void list() {
        validateRequest(Page.PAGE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Page model = getModel(Page.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = PageService.instance.adminCount(request_app_id, model.getPage_name());
        List<Page> resultList = PageService.instance.adminList(request_app_id, model.getPage_name(), getM(), getN());

        validateResponse(Page.PAGE_ID, Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/page/find")
    public void find() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);

        Page result = PageService.instance.find(model.getPage_id());

        validateResponse(Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/all/write")
    public void allWrite() {
        String request_app_id = getRequest_app_id();

        List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);

        List<Article> articleList = ArticleService.instance.topCategoryList(articleCategoryList, 7);

        List<Page> pageList = PageService.instance.appList(request_app_id);

        List<Map<String, Object>> websiteMenuList = WebsiteMenuService.instance.tree(request_app_id);
        
        List<Map<String, Object>> indexBannerList = AdvertisementService.instance.adminIndexBannerList(request_app_id);

        Kv templateMap = Kv.create();
        templateMap.put("host", Config.host);
        templateMap.put("articleCategoryList", articleCategoryList);
        templateMap.put("articleList", articleList);
        templateMap.put("websiteMenuList", websiteMenuList);
        templateMap.put("indexBannerList", indexBannerList);

        for (Page page : pageList) {
            templateMap.put("page_name", page.getPage_name());
            templateMap.put("page_content", page.getPage_content());
            templateMap.put("page_url", page.getPage_url());

            write(request_app_id, page, templateMap);
        }

        renderSuccessJson();
    }

    @ActionKey("/admin/page/write")
    public void write() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);
        String request_app_id = getRequest_app_id();

        Page page = PageService.instance.find(model.getPage_id());

        List<Map<String, Object>> websiteMenuList = WebsiteMenuService.instance.tree(request_app_id);
        
        List<Map<String, Object>> indexBannerList = AdvertisementService.instance.adminIndexBannerList(request_app_id);

        Kv templateMap = Kv.create();
        templateMap.put("host", Config.host);
        templateMap.put("websiteMenuList", websiteMenuList);
        templateMap.put("indexBannerList", indexBannerList);
        templateMap.put("page_name", page.getPage_name());
        templateMap.put("page_content", page.getPage_content());
        templateMap.put("page_url", page.getPage_url());

        if (page.getPage_url().equals("index.html")) {
            List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);
            templateMap.put("articleCategoryList", articleCategoryList);

            List<Article> articleList = ArticleService.instance.topCategoryList(articleCategoryList, 7);
            templateMap.put("articleList", articleList);
        }

        write(request_app_id, page, templateMap);

        renderSuccessJson();
    }

    private void write(String app_id, Page page, Kv templateMap) {
        engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/xietong/");

        Template template = engine.getTemplate(page.getPage_template());

        String content = template.renderToString(templateMap);

        App app = AppService.instance.find(app_id);

        if (ValidateUtil.isNullOrEmpty(app.getApp_website_path())) {
            throw new RuntimeException("路径不能为空");
        }

        FileUtil.writeFile(content, app.getApp_website_path() + page.getPage_url());
    }

    @ActionKey("/admin/page/save")
    public void save() {
        validateRequest(Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT);

        Page model = getModel(Page.class);
        model.setPage_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = PageService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/update")
    public void update() {
        validateRequest(Page.PAGE_ID, Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);
        String request_user_id = getRequest_user_id();

        Boolean result = PageService.instance.update(model, model.getPage_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/delete")
    public void delete() {
        validateRequest(Page.PAGE_ID, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);
        String request_user_id = getRequest_user_id();

        Boolean result = PageService.instance.delete(model.getPage_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}