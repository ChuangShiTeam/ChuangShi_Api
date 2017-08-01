#namespace("stock_out")

  #sql("countByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock_out
    #if(stock_out_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_out.object_id
    #end
    WHERE table_stock_out.system_status = 1
    AND table_stock_out.app_id = #p(app_id)
    #if(warehouse_id)
    AND table_stock_out.warehouse_id = #p(warehouse_id)
    #end
    AND table_stock_out.stock_out_type = #p(stock_out_type)
    #if(stock_out_batch)
    #set(stock_out_batch = "%" + stock_out_batch + "%")
    AND table_stock_out.stock_out_batch = #p(stock_out_batch)
    #end
    #if(stock_out_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock_out
    #if(stock_out_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_out.object_id
    #end
    WHERE table_stock_out.system_status = 1
    #if(app_id)
    AND table_stock_out.app_id = #p(app_id)
    #end
    #if(warehouse_id)
    AND table_stock_out.warehouse_id = #p(warehouse_id)
    #end
    AND table_stock_out.stock_out_type = #p(stock_out_type)
    #if(stock_out_batch)
    #set(stock_out_batch = "%" + stock_out_batch + "%")
    AND table_stock_out.stock_out_batch = #p(stock_out_batch)
    #end
    #if(stock_out_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit")
    SELECT
    stock_out_id
    FROM table_stock_out
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND stock_out_type = #p(stock_out_type)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit")
    SELECT
    table_stock_out.stock_out_id
    FROM table_stock_out
    #if(stock_out_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_out.object_id
    #end
    WHERE table_stock_out.system_status = 1
    AND table_stock_out.app_id = #p(app_id)
    #if(warehouse_id)
    AND table_stock_out.warehouse_id = #p(warehouse_id)
    #end
    AND table_stock_out.stock_out_type = #p(stock_out_type)
    #if(stock_out_batch)
    #set(stock_out_batch = "%" + stock_out_batch + "%")
    AND table_stock_out.stock_out_batch = #p(stock_out_batch)
    #end
    #if(stock_out_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_stock_out.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit")
    SELECT
    table_stock_out.stock_out_id
    FROM table_stock_out
    #if(stock_out_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_out.object_id
    #end
    WHERE table_stock_out.system_status = 1
    #if(app_id)
    AND table_stock_out.app_id = #p(app_id)
    #end
    #if(warehouse_id)
    AND table_stock_out.warehouse_id = #p(warehouse_id)
    #end
    AND table_stock_out.stock_out_type = #p(stock_out_type)
    #if(stock_out_batch)
    #set(stock_out_batch = "%" + stock_out_batch + "%")
    AND table_stock_out.stock_out_batch = #p(stock_out_batch)
    #end
    #if(stock_out_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_stock_out.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listByDelivery_order_id")
    SELECT
    *
    FROM table_stock_out
    WHERE system_status = 1
    AND delivery_order_id = #p(delivery_order_id)
  #end

  #sql("findByStock_out_id")
    SELECT
    *
    FROM table_stock_out
    WHERE system_status = 1
    AND stock_out_id = #p(stock_out_id)
  #end
  
  #sql("findByStock_out_idAndStock_out_type")
    SELECT
    table_stock_out.*,
    table_warehouse.warehouse_name
    #if(stock_out_type == 'MEMBER')
    ,table_user.user_name
    #end
    #if(stock_out_type == 'APP')
    ,table_app.app_name
    #end
    FROM table_stock_out
    LEFT JOIN table_warehouse ON table_warehouse.warehouse_id = table_stock_out.warehouse_id
    #if(stock_out_type == 'MEMBER')
    LEFT JOIN table_user ON table_user.user_id = table_stock_out.object_id
    #end
    #if(stock_out_type == 'APP')
    LEFT JOIN table_app ON table_app.app_id = table_stock_out.object_id
    #end
    WHERE table_stock_out.system_status = 1
    AND table_stock_out.stock_out_id = #p(stock_out_id)
  #end

  #sql("save")
    INSERT INTO table_stock_out (
      stock_out_id,
      app_id,
      warehouse_id,
      delivery_order_id,
      object_id,
      stock_out_batch,
      stock_out_type,
      stock_out_quantity,
      stock_out_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_out_id),
      #p(app_id),
      #p(warehouse_id),
      #p(delivery_order_id),
      #p(object_id),
      #p(stock_out_batch),
      #p(stock_out_type),
      #p(stock_out_quantity),
      #p(stock_out_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_stock_out SET
    warehouse_id = #p(warehouse_id),
    delivery_order_id = #p(delivery_order_id),
    object_id = #p(object_id),
    stock_out_batch = #p(stock_out_batch),
    stock_out_type = #p(stock_out_type),
    stock_out_quantity = #p(stock_out_quantity),
    stock_out_status = #p(stock_out_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND stock_out_id = #p(stock_out_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByStock_out_idAndSystem_version")
    UPDATE table_stock_out SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND stock_out_id = #p(stock_out_id)
    AND system_version = #p(system_version)
  #end

#end