#namespace("feijiu_fast_customer")

  #sql("countByApp_idAndCustomer_name")
    SELECT COUNT(*) FROM table_feijiu_fast_customer
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(customer_name)
    #set(customer_name = "%" + customer_name + "%")
    AND customer_name LIKE #p(customer_name)
    #end
  #end

  #sql("countByOrApp_id")
    SELECT COUNT(*) FROM table_feijiu_fast_customer
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    customer_id
    FROM table_feijiu_fast_customer
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_id")
    SELECT
    customer_id
    FROM table_feijiu_fast_customer
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
  #end

  #sql("listByApp_idAndCustomer_nameAndLimit")
    SELECT
    customer_id
    FROM table_feijiu_fast_customer
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(customer_name)
    #set(customer_name = "%" + customer_name + "%")
    AND customer_name LIKE #p(customer_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndLimit")
    SELECT
    customer_id
    FROM table_feijiu_fast_customer
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByCustomer_id")
    SELECT
    *
    FROM table_feijiu_fast_customer
    WHERE system_status = 1
    AND customer_id = #p(customer_id)
  #end

  #sql("save")
    INSERT INTO table_feijiu_fast_customer (
      customer_id,
      app_id,
      customer_name,
      customer_phone,
      customer_birthday,
      customer_city,
      customer_sex,
      customer_id_card,
      customer_money,
      customer_fang,
      customer_che,
      customer_xin,
      customer_shou,
      customer_dai,
      customer_gong,
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
      #p(customer_phone),
      #p(customer_birthday),
      #p(customer_city),
      #p(customer_sex),
      #p(customer_id_card),
      #p(customer_money),
      #p(customer_fang),
      #p(customer_che),
      #p(customer_xin),
      #p(customer_shou),
      #p(customer_dai),
      #p(customer_gong),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_feijiu_fast_customer SET
    customer_name = #p(customer_name),
    customer_phone = #p(customer_phone),
    customer_birthday = #p(customer_birthday),
    customer_city = #p(customer_city),
    customer_sex = #p(customer_sex),
    customer_id_card = #p(customer_id_card),
    customer_money = #p(customer_money),
    customer_fang = #p(customer_fang),
    customer_che = #p(customer_che),
    customer_xin = #p(customer_xin),
    customer_shou = #p(customer_shou),
    customer_dai = #p(customer_dai),
    customer_gong = #p(customer_gong),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND customer_id = #p(customer_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByCustomer_idAndSystem_version")
    UPDATE table_feijiu_fast_customer SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND customer_id = #p(customer_id)
    AND system_version = #p(system_version)
  #end

#end