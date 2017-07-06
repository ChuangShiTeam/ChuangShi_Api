#namespace("product")

  #sql("countByApp_idOrLikeProduct_name")
    SELECT COUNT(*) FROM table_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND product_name LIKE #p(product_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeProduct_name")
    SELECT COUNT(*) FROM table_product
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND product_name LIKE #p(product_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    product_id
    FROM table_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeProduct_nameAndLimit")
    SELECT
    product_id
    FROM table_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND product_name LIKE #p(product_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeProduct_nameAndLimit")
    SELECT
    product_id
    FROM table_product
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND product_name LIKE #p(product_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByProduct_id")
    SELECT
    *
    FROM table_product
    WHERE system_status = 1
    AND product_id = #p(product_id)
  #end

  #sql("save")
    INSERT INTO table_product (
      product_id,
      app_id,
      product_category_id,
      product_brand_id,
      product_name,
      product_image,
      product_is_new,
      product_is_recommend,
      product_is_bargain,
      product_is_hot,
      product_is_sold_out,
      product_is_virtual,
      product_content,
      product_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(product_id),
      #p(app_id),
      #p(product_category_id),
      #p(product_brand_id),
      #p(product_name),
      #p(product_image),
      #p(product_is_new),
      #p(product_is_recommend),
      #p(product_is_bargain),
      #p(product_is_hot),
      #p(product_is_sold_out),
      #p(product_is_virtual),
      #p(product_content),
      #p(product_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_product SET
    product_category_id = #p(product_category_id),
    product_brand_id = #p(product_brand_id),
    product_name = #p(product_name),
    product_image = #p(product_image),
    product_is_new = #p(product_is_new),
    product_is_recommend = #p(product_is_recommend),
    product_is_bargain = #p(product_is_bargain),
    product_is_hot = #p(product_is_hot),
    product_is_sold_out = #p(product_is_sold_out),
    product_is_virtual = #p(product_is_virtual),
    product_content = #p(product_content),
    product_status = #p(product_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND product_id = #p(product_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByProduct_idAndSystem_version")
    UPDATE table_product SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND product_id = #p(product_id)
    AND system_version = #p(system_version)
  #end

#end