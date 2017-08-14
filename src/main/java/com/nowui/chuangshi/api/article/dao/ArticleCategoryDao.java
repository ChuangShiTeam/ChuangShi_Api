package com.nowui.chuangshi.api.article.dao;

import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.common.dao.Dao;

public class ArticleCategoryDao extends Dao {

    public ArticleCategoryDao() {
        setModel(new ArticleCategory());
    }

}