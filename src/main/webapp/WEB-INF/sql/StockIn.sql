#namespace("stock_in")

  #sql("countByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock_in
    #if(stock_in_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_in.object_id
    #end
    WHERE table_stock_in.system_status = 1
    AND table_stock_in.app_id = #p(app_id)
    #if(warehouse_id)
    AND table_stock_in.warehouse_id = #p(warehouse_id)
    #end
    AND table_stock_in.stock_in_type = #p(stock_in_type)
    #if(stock_in_batch)
    #set(stock_in_batch = "%" + stock_in_batch + "%")
    AND table_stock_in.stock_in_batch = #p(stock_in_batch)
    #end
    #if(stock_in_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock_in
    #if(stock_in_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_in.object_id
    #end
    WHERE table_stock_in.system_status = 1
    #if(app_id)
    AND table_stock_in.app_id = #p(app_id)
    #end
    #if(warehouse_id)
    AND table_stock_in.warehouse_id = #p(warehouse_id)
    #end
    AND table_stock_in.stock_in_type = #p(stock_in_type)
    #if(stock_in_batch)
    #set(stock_in_batch = "%" + stock_in_batch + "%")
    AND table_stock_in.stock_in_batch = #p(stock_in_batch)
    #end
    #if(stock_in_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit")
    SELECT
    stock_in_id
    FROM table_stock_in
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND stock_in_type = #p(stock_in_type)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit")
    SELECT
    table_stock_in.stock_in_id
    FROM table_stock_in
    #if(stock_in_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_in.object_id
    #end
    WHERE table_stock_in.system_status = 1
    AND table_stock_in.app_id = #p(app_id)
    #if(warehouse_id)
    AND table_stock_in.warehouse_id = #p(warehouse_id)
    #end
    AND table_stock_in.stock_in_type = #p(stock_in_type)
    #if(stock_in_batch)
    #set(stock_in_batch = "%" + stock_in_batch + "%")
    AND table_stock_in.stock_in_batch = #p(stock_in_batch)
    #end
    #if(stock_in_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_stock_in.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit")
    SELECT
    table_stock_in.stock_in_id
    FROM table_stock_in
    #if(stock_in_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_in.object_id
    #end
    WHERE table_stock_in.system_status = 1
    #if(app_id)
    AND table_stock_in.app_id = #p(app_id)
    #end
    #if(warehouse_id)
    AND table_stock_in.warehouse_id = #p(warehouse_id)
    #end
    AND table_stock_in.stock_in_type = #p(stock_in_type)
    #if(stock_in_batch)
    #set(stock_in_batch = "%" + stock_in_batch + "%")
    AND table_stock_in.stock_in_batch = #p(stock_in_batch)
    #end
    #if(stock_in_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_stock_in.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByStock_in_id")
    SELECT
    *
    FROM table_stock_in
    WHERE system_status = 1
    AND stock_in_id = #p(stock_in_id)
  #end
  
  #sql("findByStock_in_idAndStock_in_type")
    SELECT
    table_stock_in.*,
    table_warehouse.warehouse_name
    #if(stock_in_type == 'MEMBER')
    ,table_user.user_name
    #end
    #if(stock_in_type == 'APP')
    ,table_app.app_name
    #end
    FROM table_stock_in
    LEFT JOIN table_warehouse ON table_warehouse.warehouse_id = table_stock_in.warehouse_id
    #if(stock_in_type == 'MEMBER')
    LEFT JOIN table_user ON table_user.user_id = table_stock_in.object_id
    #end
    #if(stock_in_type == 'APP')
    LEFT JOIN table_app ON table_app.app_id = table_stock_in.object_id
    #end
    WHERE table_stock_in.system_status = 1
    AND table_stock_in.stock_in_id = #p(stock_in_id)
  #end

  #sql("save")
    INSERT INTO table_stock_in (
      stock_in_id,
      app_id,
      warehouse_id,
      purchase_order_id,
      object_id,
      stock_in_batch,
      stock_in_type,
      stock_in_quantity,
      stock_in_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_in_id),
      #p(app_id),
      #p(warehouse_id),
      #p(purchase_order_id),
      #p(object_id),
      #p(stock_in_batch),
      #p(stock_in_type),
      #p(stock_in_quantity),
      #p(stock_in_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_stock_in SET
    warehouse_id = #p(warehouse_id),
    purchase_order_id = #p(purchase_order_id),
    object_id = #p(object_id),
    stock_in_batch = #p(stock_in_batch),
    stock_in_type = #p(stock_in_type),
    stock_in_quantity = #p(stock_in_quantity),
    stock_in_status = #p(stock_in_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND stock_in_id = #p(stock_in_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByStock_in_idAndSystem_version")
    UPDATE table_stock_in SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND stock_in_id = #p(stock_in_id)
    AND system_version = #p(system_version)
  #end

#end