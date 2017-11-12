#namespace("article")

  #sql("topCategoryList")
    #for(article_category_id : articleCategoryIdList)
    (SELECT
    table_article.*,
    table_file.file_path
    FROM table_article
    LEFT JOIN table_file ON table_article.article_image = table_file.file_id
    WHERE table_article.system_status = 1
    AND table_article.article_category_id = #p(article_category_id)
    ORDER BY table_article.system_create_time desc
    LIMIT 0, #p(n))
    #if(!for.last)
    UNION ALL
    #end
    #end
  #end
  
  #sql("prevArticle")
  	SELECT *
  	FROM table_article
  	WHERE system_status = 1
    AND article_category_id = #p(article_category_id)
    AND system_create_time > #p(system_create_time)
    ORDER BY system_create_time
    LIMIT 0, 1
  #end
  
  #sql("nextArticle")
  	SELECT *
  	FROM table_article
  	WHERE system_status = 1
    AND article_category_id = #p(article_category_id)
    AND system_create_time < #p(system_create_time)
    ORDER BY system_create_time desc
    LIMIT 0, 1
  #end

#end