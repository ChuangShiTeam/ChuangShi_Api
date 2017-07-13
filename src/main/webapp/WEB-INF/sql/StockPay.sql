#namespace("stock_pay")

  #sql("listByStock_id")
    SELECT
    *
    FROM table_stock_pay
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
    ORDER BY system_create_time DESC
  #end

  #sql("save")
    INSERT INTO table_stock_pay (
      stock_id,
      stock_pay_type,
      stock_pay_number,
      stock_pay_account,
      stock_pay_time,
      stock_pay_result,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_id),
      #p(stock_pay_type),
      #p(stock_pay_number),
      #p(stock_pay_account),
      #p(stock_pay_time),
      #p(stock_pay_result),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByStock_idAndSystem_version")
    UPDATE table_stock_pay SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
    AND system_version = #p(system_version)
  #end

#end