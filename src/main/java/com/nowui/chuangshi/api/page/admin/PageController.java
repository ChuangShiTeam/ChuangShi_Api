package com.nowui.chuangshi.api.page.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
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
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.Util;

import java.util.List;
import java.util.Map;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/page")
public class PageController extends Controller {

    private static Engine engine = Engine.create("code_engine");

    @ActionKey("/admin/page/list")
    public void list() {
        validateRequest(Page.PAGE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Page model = getModel(Page.class);
        String request_app_id = getRequest_app_id();
        Cnd cnd = Cnd.where(Page.APP_ID, model.getApp_id()).andAllowEmpty(Page.PAGE_NAME, model.getPage_name());

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

        Engine engine = Engine.create("code_engine");
        engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/xietong/");

        for (Page page : pageList) {
            write(request_app_id, page, websiteMenuList, articleCategoryList, articleList);
        }

        renderSuccessJson();
    }

    @ActionKey("/admin/page/write")
    public void write() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);
        String request_app_id = getRequest_app_id();

        List<Map<String, Object>> websiteMenuList = WebsiteMenuService.instance.tree(request_app_id);

        List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);

        List<Article> articleList = ArticleService.instance.topCategoryList(articleCategoryList, 7);

        Page page = PageService.instance.find(model.getPage_id());

        write(request_app_id, page, websiteMenuList, articleCategoryList, articleList);

        renderSuccessJson();
    }

    private void write(String request_app_id, Page page, List<Map<String, Object>> websiteMenuList, List<ArticleCategory> articleCategoryList, List<Article> articleList) {
        engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/xietong/");

        Template template = engine.getTemplate(page.getPage_template());

        Kv templateMap = Kv.create();
        templateMap.put("articleCategoryList", articleCategoryList);
        templateMap.put("articleList", articleList);
        templateMap.put("websiteMenuList", websiteMenuList);
        templateMap.put("page_name", page.getPage_name());
        templateMap.put("page_content", page.getPage_content());
        templateMap.put("page_url", page.getPage_url());

        String content = template.renderToString(templateMap);

//        FileUtil.writeFile(content, "/usr/local/www/xietong/website/" + page.getPage_url());
        FileUtil.writeFile(content, "/Users/yongqiangzhong/Documents/Publish/XieTong_Website/" + page.getPage_url());
    }

    @ActionKey("/admin/page/save")
    public void save() {
        validateRequest(Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT);

        Page model = getModel(Page.class);
        model.setPage_id(Util.getRandomUUID());

        Boolean result = PageService.instance.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/update")
    public void update() {
        validateRequest(Page.PAGE_ID, Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);

        Boolean result = PageService.instance.update(model, model.getPage_id(), model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/delete")
    public void delete() {
        validateRequest(Page.PAGE_ID, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);

        Boolean result = PageService.instance.delete(model.getPage_id(), model.getSystem_version());

        renderSuccessJson(result);
    }

}