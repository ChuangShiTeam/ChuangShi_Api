#namespace("customer_attribute")

  #sql("countByApp_idOrLikeCustomer_attribute_name")
    SELECT COUNT(*) FROM table_customer_attribute
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(customer_attribute_name)
    #set(customer_attribute_name = "%" + customer_attribute_name + "%")
    AND customer_attribute_name LIKE #p(customer_attribute_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeCustomer_attribute_name")
    SELECT COUNT(*) FROM table_customer_attribute
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(customer_attribute_name)
    #set(customer_attribute_name = "%" + customer_attribute_name + "%")
    AND customer_attribute_name LIKE #p(customer_attribute_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    customer_attribute_id
    FROM table_customer_attribute
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

    #sql("listByApp_id")
    SELECT
    *
    FROM table_customer_attribute
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY customer_attribute_sort asc
  #end
  
  #sql("listByApp_idOrLikeCustomer_attribute_nameAndLimit")
    SELECT
    *
    FROM table_customer_attribute
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(customer_attribute_name)
    #set(customer_attribute_name = "%" + customer_attribute_name + "%")
    AND customer_attribute_name LIKE #p(customer_attribute_name)
    #end
    ORDER BY customer_attribute_sort DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeCustomer_attribute_nameAndLimit")
    SELECT
    customer_attribute_id
    FROM table_customer_attribute
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(customer_attribute_name)
    #set(customer_attribute_name = "%" + customer_attribute_name + "%")
    AND customer_attribute_name LIKE #p(customer_attribute_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByCustomer_attribute_id")
    SELECT
    *
    FROM table_customer_attribute
    WHERE system_status = 1
    AND customer_attribute_id = #p(customer_attribute_id)
  #end

  #sql("save")
    INSERT INTO table_customer_attribute (
      customer_attribute_id,
      app_id,
      customer_attribute_name,
      customer_attribute_key,
      customer_attribute_input_type,
      customer_attribute_data_type,
      customer_attribute_default_value,
      customer_attribute_sort,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(customer_attribute_id),
      #p(app_id),
      #p(customer_attribute_name),
      #p(customer_attribute_key),
      #p(customer_attribute_input_type),
      #p(customer_attribute_data_type),
      #p(customer_attribute_default_value),
      #p(customer_attribute_sort),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_customer_attribute SET
    customer_attribute_name = #p(customer_attribute_name),
    customer_attribute_key = #p(customer_attribute_key),
    customer_attribute_input_type = #p(customer_attribute_input_type),
    customer_attribute_data_type = #p(customer_attribute_data_type),
    customer_attribute_default_value = #p(customer_attribute_default_value),
    customer_attribute_sort = #p(customer_attribute_sort),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND customer_attribute_id = #p(customer_attribute_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByCustomer_attribute_idAndSystem_version")
    UPDATE table_customer_attribute SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND customer_attribute_id = #p(customer_attribute_id)
    AND system_version = #p(system_version)
  #end

#end