#namespace("stock")

  #sql("countByApp_idOrStock_typeOrUser_nameOrStock_actionOrLikeProduct_name")
    SELECT COUNT(*) FROM table_stock
    #if(product_name)
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    #end
    #if(user_name)
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_user.user_id = table_member.user_id
    #end
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(stock_type)
    AND stock_type = #p(stock_type)
    #end
    #if(stock_action)
    AND stock_action = #p(stock_action)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
  #end
  
  #sql("countByOrApp_idOrStock_typeOrUser_nameOrStock_actionOrLikeProduct_name")
    SELECT COUNT(*) FROM table_stock
    #if(product_name)
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    #end
    #if(user_name)
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_user.user_id = table_member.user_id
    #end
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(stock_type)
    AND stock_type = #p(stock_type)
    #end
    #if(stock_action)
    AND stock_action = #p(stock_action)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
  #end

  #sql("listByApp_idAndStock_typeAndSystem_create_timeAndLimit")
    SELECT
    stock_id
    FROM table_stock
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND stock_type = #p(stock_type)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrStock_typeOrUser_nameOrStock_actionOrLikeProduct_nameAndLimit")
    SELECT
    stock_id
    FROM table_stock
    #if(product_name)
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    #end
    #if(user_name)
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_user.user_id = table_member.user_id
    #end
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(stock_type)
    AND stock_type = #p(stock_type)
    #end
    #if(stock_action)
    AND stock_action = #p(stock_action)
    #end
   	#if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrStock_typeOrUser_nameOrStock_actionOrLikeProduct_nameAndLimit")
    SELECT
    stock_id
    FROM table_stock
    #if(product_name)
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    #end
    #if(user_name)
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_user.user_id = table_member.user_id
    #end
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(stock_type)
    AND stock_type = #p(stock_type)
    #end
    #if(stock_action)
    AND stock_action = #p(stock_action)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByStock_id")
    SELECT
    *
    FROM table_stock
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
  #end

  #sql("save")
    INSERT INTO table_stock (
      stock_id,
      app_id,
      product_sku_id,
      object_id,
      stock_type,
      stock_quantity,
      stock_action,
      stock_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_id),
      #p(app_id),
      #p(product_sku_id),
      #p(object_id),
      #p(stock_type),
      #p(stock_quantity),
      #p(stock_action),
      #p(stock_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_stock SET
    product_sku_id = #p(product_sku_id),
    object_id = #p(object_id),
    stock_type = #p(stock_type),
    stock_quantity = #p(stock_quantity),
    stock_action = #p(stock_action),
    stock_status = #p(stock_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByStock_idAndSystem_version")
    UPDATE table_stock SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
    AND system_version = #p(system_version)
  #end

#end