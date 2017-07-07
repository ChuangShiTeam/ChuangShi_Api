#namespace("bill")

  #sql("countByApp_idOrLikeBill_name")
    SELECT COUNT(*) FROM table_bill
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(bill_name)
    #set(bill_name = "%" + bill_name + "%")
    AND bill_name LIKE #p(bill_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeBill_name")
    SELECT COUNT(*) FROM table_bill
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(bill_name)
    #set(bill_name = "%" + bill_name + "%")
    AND bill_name LIKE #p(bill_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    bill_id
    FROM table_bill
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeBill_nameAndLimit")
    SELECT
    bill_id
    FROM table_bill
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(bill_name)
    #set(bill_name = "%" + bill_name + "%")
    AND bill_name LIKE #p(bill_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeBill_nameAndLimit")
    SELECT
    bill_id
    FROM table_bill
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(bill_name)
    #set(bill_name = "%" + bill_name + "%")
    AND bill_name LIKE #p(bill_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByBill_id")
    SELECT
    *
    FROM table_bill
    WHERE system_status = 1
    AND bill_id = #p(bill_id)
  #end

  #sql("save")
    INSERT INTO table_bill (
      bill_id,
      app_id,
      user_id,
      bill_type,
      bill_image,
      bill_name,
      bill_amount,
      bill_is_income,
      bill_time,
      bill_flow,
      bill_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(bill_id),
      #p(app_id),
      #p(user_id),
      #p(bill_type),
      #p(bill_image),
      #p(bill_name),
      #p(bill_amount),
      #p(bill_is_income),
      #p(bill_time),
      #p(bill_flow),
      #p(bill_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_bill SET
    user_id = #p(user_id),
    bill_type = #p(bill_type),
    bill_image = #p(bill_image),
    bill_name = #p(bill_name),
    bill_amount = #p(bill_amount),
    bill_is_income = #p(bill_is_income),
    bill_time = #p(bill_time),
    bill_flow = #p(bill_flow),
    bill_status = #p(bill_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND bill_id = #p(bill_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByBill_idAndSystem_version")
    UPDATE table_bill SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND bill_id = #p(bill_id)
    AND system_version = #p(system_version)
  #end

#end