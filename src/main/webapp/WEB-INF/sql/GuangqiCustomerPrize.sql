#namespace("guangqi_customer_prize")

  #sql("countByApp_id")
    SELECT COUNT(*) FROM table_guangqi_customer_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
  #end

  #sql("countByOrApp_id")
    SELECT COUNT(*) FROM table_guangqi_customer_prize
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
  #end

  #sql("countByApp_idAndCustomer_id")
    SELECT COUNT(*) FROM table_guangqi_customer_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND customer_id = #p(customer_id)
  #end

  #sql("countByApp_idAndPrize_id")
    SELECT COUNT(*) FROM table_guangqi_customer_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND prize_id = #p(prize_id)
  #end

  #sql("countByApp_idAndPrize_idAndCustomer_prize_date")
    SELECT COUNT(*) FROM table_guangqi_customer_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND prize_id = #p(prize_id)
    AND customer_prize_date = #p(customer_prize_date)
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    customer_prize_id
    FROM table_guangqi_customer_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_id")
    SELECT
    customer_prize_id
    FROM table_guangqi_customer_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
  #end

  #sql("listByApp_idAndLimit")
    SELECT
    customer_prize_id
    FROM table_guangqi_customer_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndLimit")
    SELECT
    customer_prize_id
    FROM table_guangqi_customer_prize
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByCustomer_prize_id")
    SELECT
    *
    FROM table_guangqi_customer_prize
    WHERE system_status = 1
    AND customer_prize_id = #p(customer_prize_id)
  #end

  #sql("save")
    INSERT INTO table_guangqi_customer_prize (
      customer_prize_id,
      app_id,
      customer_id,
      prize_id,
      customer_prize_date,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(customer_prize_id),
      #p(app_id),
      #p(customer_id),
      #p(prize_id),
      #p(customer_prize_date),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_guangqi_customer_prize SET
    customer_id = #p(customer_id),
    prize_id = #p(prize_id),
    customer_prize_date = #p(customer_prize_date),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND customer_prize_id = #p(customer_prize_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByCustomer_prize_idAndSystem_version")
    UPDATE table_guangqi_customer_prize SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND customer_prize_id = #p(customer_prize_id)
    AND system_version = #p(system_version)
  #end

#end