#namespace("feijiu_recommend_product")

  #sql("countByApp_id")
    SELECT COUNT(*) FROM table_feijiu_recommend_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
  #end

  #sql("countByOrApp_id")
    SELECT COUNT(*) FROM table_feijiu_recommend_product
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    product_id
    FROM table_feijiu_recommend_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_id")
    SELECT
    product_id
    FROM table_feijiu_recommend_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
  #end

  #sql("listByApp_idAndLimit")
    SELECT
    product_id
    FROM table_feijiu_recommend_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndLimit")
    SELECT
    product_id
    FROM table_feijiu_recommend_product
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByProduct_id")
    SELECT
    *
    FROM table_feijiu_recommend_product
    WHERE system_status = 1
    AND product_id = #p(product_id)
  #end

  #sql("save")
    INSERT INTO table_feijiu_recommend_product (
      product_id,
      app_id,
      product_name,
      product_image,
      product_content,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(product_id),
      #p(app_id),
      #p(product_name),
      #p(product_image),
      #p(product_content),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_feijiu_recommend_product SET
    product_name = #p(product_name),
    product_image = #p(product_image),
    product_content = #p(product_content),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND product_id = #p(product_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByProduct_idAndSystem_version")
    UPDATE table_feijiu_recommend_product SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND product_id = #p(product_id)
    AND system_version = #p(system_version)
  #end

#end