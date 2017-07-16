#namespace("supplier")

  #sql("countByApp_id")
    SELECT COUNT(*) FROM table_supplier
    WHERE system_status = 1
    AND app_id = #p(app_id)
  #end

  #sql("countByOrApp_idOrLikeSupplier_name")
    SELECT COUNT(*) FROM table_supplier
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(supplier_name)
    #set(supplier_name = "%" + supplier_name + "%")
    AND supplier_name LIKE #p(supplier_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    supplier_id
    FROM table_supplier
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idAndLimit")
    SELECT
    *
    FROM table_supplier
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeSupplier_nameAndLimit")
    SELECT
    supplier_id
    FROM table_supplier
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(supplier_name)
    #set(supplier_name = "%" + supplier_name + "%")
    AND supplier_name LIKE #p(supplier_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findBySupplier_id")
    SELECT
    *
    FROM table_supplier
    WHERE system_status = 1
    AND supplier_id = #p(supplier_id)
  #end

  #sql("save")
    INSERT INTO table_supplier (
      supplier_id,
      app_id,
      user_id,
      supplier_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(supplier_id),
      #p(app_id),
      #p(user_id),
      #p(supplier_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_supplier SET
    user_id = #p(user_id),
    supplier_status = #p(supplier_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND supplier_id = #p(supplier_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteBySupplier_idAndSystem_version")
    UPDATE table_supplier SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND supplier_id = #p(supplier_id)
    AND system_version = #p(system_version)
  #end

#end