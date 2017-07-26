#namespace("customer_attribute_value")

  #sql("listByCustomer_id")
    SELECT
    *
    FROM table_customer_attribute_value
    WHERE system_status = 1
    AND customer_id = #p(customer_id)
    ORDER BY system_create_time DESC
  #end

  #sql("save")
    INSERT INTO table_customer_attribute_value (
      customer_attribute_id,
      customer_id,
      customer_attribute_value,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(customer_attribute_id),
      #p(customer_id),
      #p(customer_attribute_value),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByCustomer_idAndSystem_update_user_id")
    UPDATE table_customer_attribute_value SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND customer_id = #p(customer_id)
  #end

#end