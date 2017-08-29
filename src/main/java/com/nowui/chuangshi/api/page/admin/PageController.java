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
        Cnd cnd = Cnd.where(Page.APP_ID, model.getApp_id()).andAllowEmpty(Page.PAGE_NAME, model.getPage_name());

        Integer resultCount = PageService.me.count(cnd);
        List<Page> resultList = PageService.me.list(cnd.asc(Page.PAGE_SORT).paginate(getM(), getN()));

        validateResponse(Page.PAGE_ID, Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/page/find")
    public void find() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);

        Page result = PageService.me.findById(model.getPage_id());

        validateResponse(Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/all/write")
    public void allWrite() {
        String request_app_id = getRequest_app_id();

        List<ArticleCategory> articleCategoryList = ArticleCategoryService.me.list(Cnd.where(ArticleCategory.APP_ID, request_app_id).asc(ArticleCategory.ARTICLE_CATEGORY_SORT));

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < articleCategoryList.size(); i++) {
            ArticleCategory articleCategory = articleCategoryList.get(i);

            stringBuffer.append("select \n");
            stringBuffer.append("table_article.*, table_file.file_path \n");
            stringBuffer.append("from table_article \n");
            stringBuffer.append("left join table_file on table_article.article_image = table_file.file_id \n");
            stringBuffer.append("where table_article.article_category_id = '" + articleCategory.getArticle_category_id() + "' \n");
            stringBuffer.append("limit 0, 7 \n");
            if (i + 1 < articleCategoryList.size()) {
                stringBuffer.append("union all \n");
            }
        }
        System.out.println(stringBuffer.toString());
        List<Article> articleList = new Article().find(stringBuffer.toString());

        List<Page> pageList = PageService.me.list(Cnd.where(Page.APP_ID, request_app_id));

        List<Map<String, Object>> websiteMenuList = WebsiteMenuService.me.tree(request_app_id);

        engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/xietong/");

        for (Page page : pageList) {
            Template template = engine.getTemplate(page.getPage_template());

            Kv templateMap = Kv.create();
            templateMap.put("articleCategoryList", articleCategoryList);
            templateMap.put("articleList", articleList);
            templateMap.put("websiteMenuList", websiteMenuList);
            templateMap.put("page_name", page.getPage_name());
            templateMap.put("page_content", page.getPage_content());
            templateMap.put("page_url", page.getPage_url());

            String content = template.renderToString(templateMap);

            FileUtil.writeFile(content, "/usr/local/www/xietong/website/" + page.getPage_url());
//            FileUtil.writeFile(content, "/Users/yongqiangzhong/Documents/Publish/XieTong_Website/" + page.getPage_url());
        }

        renderSuccessJson();
    }

    @ActionKey("/admin/page/write")
    public void write() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);
        String request_app_id = getRequest_app_id();

        List<ArticleCategory> articleCategoryList = ArticleCategoryService.me.list(Cnd.where(ArticleCategory.APP_ID, request_app_id).asc(ArticleCategory.ARTICLE_CATEGORY_SORT));

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < articleCategoryList.size(); i++) {
            ArticleCategory articleCategory = articleCategoryList.get(i);

            stringBuffer.append("select \n");
            stringBuffer.append("table_article.*, table_file.file_path \n");
            stringBuffer.append("from table_article \n");
            stringBuffer.append("left join table_file on table_article.article_image = table_file.file_id \n");
            stringBuffer.append("where table_article.article_category_id = '" + articleCategory.getArticle_category_id() + "' \n");
            stringBuffer.append("limit 0, 7 \n");
            if (i + 1 < articleCategoryList.size()) {
                stringBuffer.append("union all \n");
            }
        }
        System.out.println(stringBuffer.toString());
        List<Article> articleList = new Article().find(stringBuffer.toString());

        Page page = PageService.me.findById(model.getPage_id());

        engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/xietong/");

        Template template = engine.getTemplate(page.getPage_template());

        List<Map<String, Object>> websiteMenuList = WebsiteMenuService.me.tree(request_app_id);

        Kv templateMap = Kv.create();
        templateMap.put("articleCategoryList", articleCategoryList);
        templateMap.put("articleList", articleList);
        templateMap.put("websiteMenuList", websiteMenuList);
        templateMap.put("page_name", page.getPage_name());
        templateMap.put("page_content", page.getPage_content());
        templateMap.put("page_url", page.getPage_url());

        String content = template.renderToString(templateMap);

        FileUtil.writeFile(content, "/usr/local/www/xietong/website/" + page.getPage_url());
//        FileUtil.writeFile(content, "/Users/yongqiangzhong/Documents/Publish/XieTong_Website/" + page.getPage_url());


        renderSuccessJson();
    }

    @ActionKey("/admin/page/save")
    public void save() {
        validateRequest(Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT);

        Page model = getModel(Page.class);
        model.setPage_id(Util.getRandomUUID());

        Boolean result = PageService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/update")
    public void update() {
        validateRequest(Page.PAGE_ID, Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);

        Boolean result = PageService.me.update(model, Cnd.where(model.PAGE_ID, model.getPage_id()).and(Page.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/delete")
    public void delete() {
        validateRequest(Page.PAGE_ID, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);

        Boolean result = PageService.me.delete(model, Cnd.where(model.PAGE_ID, model.getPage_id()).and(Page.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}