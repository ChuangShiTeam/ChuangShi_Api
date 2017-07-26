#namespace("customer")

  #sql("countByApp_idOrLikeCustomer_name")
    SELECT COUNT(*) FROM table_customer
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(customer_name)
    #set(customer_name = "%" + customer_name + "%")
    AND customer_name LIKE #p(customer_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeCustomer_name")
    SELECT COUNT(*) FROM table_customer
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(customer_name)
    #set(customer_name = "%" + customer_name + "%")
    AND customer_name LIKE #p(customer_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    customer_id
    FROM table_customer
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeCustomer_nameAndLimit")
    SELECT
    *
    FROM table_customer
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(customer_name)
    #set(customer_name = "%" + customer_name + "%")
    AND customer_name LIKE #p(customer_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeCustomer_nameAndLimit")
    SELECT
    customer_id
    FROM table_customer
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(customer_name)
    #set(customer_name = "%" + customer_name + "%")
    AND customer_name LIKE #p(customer_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByCustomer_id")
    SELECT
    *
    FROM table_customer
    WHERE system_status = 1
    AND customer_id = #p(customer_id)
  #end

  #sql("save")
    INSERT INTO table_customer (
      customer_id,
      app_id,
      customer_name,
      customer_sex,
      customer_birthday,
      customer_tel,
      customer_mobile,
      customer_postcode,
      customer_id_card,
      customer_province,
      customer_city,
      customer_area,
      customer_address,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(customer_id),
      #p(app_id),
      #p(customer_name),
      #p(customer_sex),
      #p(customer_birthday),
      #p(customer_tel),
      #p(customer_mobile),
      #p(customer_postcode),
      #p(customer_id_card),
      #p(customer_province),
      #p(customer_city),
      #p(customer_area),
      #p(customer_address),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_customer SET
    customer_name = #p(customer_name),
    customer_sex = #p(customer_sex),
    customer_birthday = #p(customer_birthday),
    customer_tel = #p(customer_tel),
    customer_mobile = #p(customer_mobile),
    customer_postcode = #p(customer_postcode),
    customer_id_card = #p(customer_id_card),
    customer_province = #p(customer_province),
    customer_city = #p(customer_city),
    customer_area = #p(customer_area),
    customer_address = #p(customer_address),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND customer_id = #p(customer_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByCustomer_idAndSystem_version")
    UPDATE table_customer SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND customer_id = #p(customer_id)
    AND system_version = #p(system_version)
  #end

#end