#namespace("supplier_product")

 #sql("findBySupplier_id")
    SELECT 
    * 
    FROM table_supplier_product
    WHERE system_status = 1
    AND supplier_id = #p(supplier_id)
    ORDER BY system_create_time DESC
  #end
  
  #sql("countByApp_idOrLikeSupplier_product_name")
    SELECT COUNT(*) FROM table_supplier_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(supplier_product_name)
    #set(supplier_product_name = "%" + supplier_product_name + "%")
    AND supplier_product_name LIKE #p(supplier_product_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeSupplier_product_name")
    SELECT COUNT(*) FROM table_supplier_product
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(supplier_product_name)
    #set(supplier_product_name = "%" + supplier_product_name + "%")
    AND supplier_product_name LIKE #p(supplier_product_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    supplier_product_id
    FROM table_supplier_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeSupplier_product_nameAndLimit")
    SELECT
    supplier_product_id
    FROM table_supplier_product
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(supplier_product_name)
    #set(supplier_product_name = "%" + supplier_product_name + "%")
    AND supplier_product_name LIKE #p(supplier_product_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeSupplier_product_nameAndLimit")
    SELECT
    supplier_product_id
    FROM table_supplier_product
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(supplier_product_name)
    #set(supplier_product_name = "%" + supplier_product_name + "%")
    AND supplier_product_name LIKE #p(supplier_product_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findBySupplier_product_id")
    SELECT
    *
    FROM table_supplier_product
    WHERE system_status = 1
    AND supplier_product_id = #p(supplier_product_id)
  #end

  #sql("save")
    INSERT INTO table_supplier_product (
      supplier_id,
      product_id,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(supplier_id),
      #p(product_id),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_supplier_product SET
    supplier_id = #p(supplier_id),
    product_id = #p(product_id),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND supplier_product_id = #p(supplier_product_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteBySupplier_product_idAndSystem_version")
    UPDATE table_supplier_product SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND supplier_product_id = #p(supplier_product_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("deleteBySupplier_id")
    UPDATE table_supplier_product SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND supplier_id = #p(supplier_id)
  #end

#end