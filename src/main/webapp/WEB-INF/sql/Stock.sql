#namespace("stock")

  #sql("countByApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock
    #if(product_name)
    LEFT JOIN table_product ON table_product.product_id = table_stock.product_id
    #end
    #if(stock_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock.object_id
    #end
    WHERE table_stock.system_status = 1
    AND table_stock.app_id = #p(app_id)
    #if(warehouse_id)
    AND table_stock.warehouse_id = #p(warehouse_id)
    #end
    #if(stock_type)
    AND table_stock.stock_type = #p(stock_type)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end
  
  #sql("countByOrApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock
    #if(product_name)
    LEFT JOIN table_product ON table_product.product_id = table_stock.product_id
    #end
    #if(stock_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock.object_id
    #end
    WHERE table_stock.system_status = 1
    #if(app_id)
    AND table_stock.app_id = #p(app_id)
    #end
    #if(warehouse_id)
    AND table_stock.warehouse_id = #p(warehouse_id)
    #end
    #if(stock_type)
    AND table_stock.stock_type = #p(stock_type)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end
  
  #sql("sumQuantityByApp_idOrWarehouse_idAndObject_idAndProduct_sku_id")
    SELECT IFNULL(sum(stock_quantity), 0) FROM table_stock
    WHERE system_status = 1
    AND table_stock.app_id = #p(app_id)
    #if(warehouse_id)
    AND warehouse_id = #p(warehouse_id)
    #end
    AND object_id = #p(object_id)
    AND product_sku_id = #p(product_sku_id)
  #end
  
  #sql("sumQuantityByObject_id")
    SELECT IFNULL(sum(stock_quantity), 0) FROM table_stock
    WHERE system_status = 1
    AND object_id = #p(object_id)
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    stock_id
    FROM table_stock
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit")
    SELECT
    stock_id
    FROM table_stock
    #if(product_name)
    LEFT JOIN table_product ON table_product.product_id = table_stock.product_id
    #end
    #if(stock_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock.object_id
    #end
    WHERE table_stock.system_status = 1
    AND table_stock.app_id = #p(app_id)
    #if(warehouse_id)
    AND table_stock.warehouse_id = #p(warehouse_id)
    #end
    #if(stock_type)
    AND table_stock.stock_type = #p(stock_type)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrWarehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit")
    SELECT
    stock_id
    FROM table_stock
    #if(product_name)
    LEFT JOIN table_product ON table_product.product_id = table_stock.product_id
    #end
    #if(stock_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock.object_id
    #end
    WHERE table_stock.system_status = 1
    #if(app_id)
    AND table_stock.app_id = #p(app_id)
    #end
    #if(warehouse_id)
    AND table_stock.warehouse_id = #p(warehouse_id)
    #end
    #if(stock_type)
    AND table_stock.stock_type = #p(stock_type)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
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
  
  #sql("findByWarehouse_idAndProduct_sku_idAndStock_type")
    SELECT
    *
    FROM table_stock
    WHERE system_status = 1
    AND warehouse_id = #p(warehouse_id)
    AND product_sku_id = #p(product_sku_id)
    AND stock_type = #p(stock_type)
  #end
  
  #sql("findByStock_idAndStock_type")
    SELECT
    table_stock.*,
    table_product.product_name
    #if(stock_type == 'MEMBER')
    ,table_user.user_name
    #end
    #if(stock_type == 'APP')
    ,table_app.app_name
    #end
    FROM table_stock
    LEFT JOIN table_product ON table_product.product_id = table_stock.product_id
    #if(stock_type == 'MEMBER')
    LEFT JOIN table_user ON table_user.user_id = table_stock.object_id
    #end
    #if(stock_type == 'APP')
    LEFT JOIN table_app ON table_app.app_id = table_stock.object_id
    #end
    WHERE table_stock.system_status = 1
    AND table_stock.stock_id = #p(stock_id)
  #end

  #sql("save")
    INSERT INTO table_stock (
      stock_id,
      app_id,
      warehouse_id,
      object_id,
      stock_type,
      product_category_id,
      product_id,
      product_sku_id,
      stock_quantity,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_id),
      #p(app_id),
      #p(warehouse_id),
      #p(object_id),
      #p(stock_type),
      #p(product_category_id),
      #p(product_id),
      #p(product_sku_id),
      #p(stock_quantity),
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
    warehouse_id = #p(warehouse_id),
    object_id = #p(object_id),
    stock_type = #p(stock_type),
    product_category_id = #p(product_category_id),
    product_id = #p(product_id),
    product_sku_id = #p(product_sku_id),
    stock_quantity = #p(stock_quantity),
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