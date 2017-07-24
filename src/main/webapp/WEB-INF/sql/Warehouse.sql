#namespace("warehouse")

  #sql("countByApp_idOrLikeWarehouse_name")
    SELECT COUNT(*) FROM table_warehouse
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(warehouse_name)
    #set(warehouse_name = "%" + warehouse_name + "%")
    AND warehouse_name LIKE #p(warehouse_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeWarehouse_name")
    SELECT COUNT(*) FROM table_warehouse
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(warehouse_name)
    #set(warehouse_name = "%" + warehouse_name + "%")
    AND warehouse_name LIKE #p(warehouse_name)
    #end
  #end
  
  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    warehouse_id
    FROM table_warehouse
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeWarehouse_nameAndLimit")
    SELECT
    warehouse_id
    FROM table_warehouse
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(warehouse_name)
    #set(warehouse_name = "%" + warehouse_name + "%")
    AND warehouse_name LIKE #p(warehouse_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeWarehouse_nameAndLimit")
    SELECT
    warehouse_id
    FROM table_warehouse
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(warehouse_name)
    #set(warehouse_name = "%" + warehouse_name + "%")
    AND warehouse_name LIKE #p(warehouse_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listByApp_id")
    SELECT
    warehouse_id
    FROM table_warehouse
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
  #end

  #sql("findByWarehouse_id")
    SELECT
    *
    FROM table_warehouse
    WHERE system_status = 1
    AND warehouse_id = #p(warehouse_id)
  #end

  #sql("save")
    INSERT INTO table_warehouse (
      warehouse_id,
      app_id,
      warehouse_code,
      warehouse_name,
      warehouse_status,
      warehouse_remark,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(warehouse_id),
      #p(app_id),
      #p(warehouse_code),
      #p(warehouse_name),
      #p(warehouse_status),
      #p(warehouse_remark),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_warehouse SET
    warehouse_code = #p(warehouse_code),
    warehouse_name = #p(warehouse_name),
    warehouse_status = #p(warehouse_status),
    warehouse_remark = #p(warehouse_remark),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND warehouse_id = #p(warehouse_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByWarehouse_idAndSystem_version")
    UPDATE table_warehouse SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND warehouse_id = #p(warehouse_id)
    AND system_version = #p(system_version)
  #end

#end