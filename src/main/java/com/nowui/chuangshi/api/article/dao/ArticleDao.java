package com.nowui.chuangshi.api.article.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.DateUtil;

public class ArticleDao extends Dao {

    private final Article article = new Article();

    public ArticleDao() {
        setModel(article);
    }

    public List<Article> topCategoryList(List<String> articleCategoryIdList, Integer n) {
        Kv sqlMap = Kv.create();
        sqlMap.put("articleCategoryIdList", articleCategoryIdList);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("article.topCategoryList", sqlMap);

        logSql("article", "topCategoryList", sqlPara);

        return article.find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public Article prevArticle(String article_category_id, Date system_create_time) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Article.ARTICLE_CATEGORY_ID, article_category_id);
        sqlMap.put(Article.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(system_create_time));
        SqlPara sqlPara = Db.getSqlPara("article.prevArticle", sqlMap);

        logSql("article", "prevArticle", sqlPara);
        
        List<Article> articleList = article.find(sqlPara.getSql(), sqlPara.getPara());
        
        if (articleList == null || articleList.size() == 0) {
           return null; 
        }

        return articleList.get(0);
    }
    
    public Article nextArticle(String article_category_id, Date system_create_time) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Article.ARTICLE_CATEGORY_ID, article_category_id);
        sqlMap.put(Article.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(system_create_time));
        SqlPara sqlPara = Db.getSqlPara("article.nextArticle", sqlMap);

        logSql("article", "nextArticle", sqlPara);
        
        List<Article> articleList = article.find(sqlPara.getSql(), sqlPara.getPara());
        
        if (articleList == null || articleList.size() == 0) {
           return null; 
        }

        return articleList.get(0);
    }

}