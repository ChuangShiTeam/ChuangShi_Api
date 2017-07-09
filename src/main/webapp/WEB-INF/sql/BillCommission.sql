#namespace("bill_commission")

  #sql("countByApp_idOrLikeBill_commission_name")
    SELECT COUNT(*) FROM table_bill_commission
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(bill_commission_name)
    #set(bill_commission_name = "%" + bill_commission_name + "%")
    AND bill_commission_name LIKE #p(bill_commission_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeBill_commission_name")
    SELECT COUNT(*) FROM table_bill_commission
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(bill_commission_name)
    #set(bill_commission_name = "%" + bill_commission_name + "%")
    AND bill_commission_name LIKE #p(bill_commission_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    bill_commission_id
    FROM table_bill_commission
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeBill_commission_nameAndLimit")
    SELECT
    bill_commission_id
    FROM table_bill_commission
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(bill_commission_name)
    #set(bill_commission_name = "%" + bill_commission_name + "%")
    AND bill_commission_name LIKE #p(bill_commission_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeBill_commission_nameAndLimit")
    SELECT
    bill_commission_id
    FROM table_bill_commission
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(bill_commission_name)
    #set(bill_commission_name = "%" + bill_commission_name + "%")
    AND bill_commission_name LIKE #p(bill_commission_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByBill_commission_id")
    SELECT
    *
    FROM table_bill_commission
    WHERE system_status = 1
    AND bill_commission_id = #p(bill_commission_id)
  #end

  #sql("save")
    INSERT INTO table_bill_commission (
      bill_id,
      product_sku_id,
      member_id,
      member_name,
      member_level_id,
      member_level_name,
      product_sku_commission,
      product_sku_commission_amount,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(bill_id),
      #p(product_sku_id),
      #p(member_id),
      #p(member_name),
      #p(member_level_id),
      #p(member_level_name),
      #p(product_sku_commission),
      #p(product_sku_commission_amount),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_bill_commission SET
    bill_id = #p(bill_id),
    product_sku_id = #p(product_sku_id),
    member_id = #p(member_id),
    member_name = #p(member_name),
    member_level_id = #p(member_level_id),
    member_level_name = #p(member_level_name),
    product_sku_commission = #p(product_sku_commission),
    product_sku_commission_amount = #p(product_sku_commission_amount),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND bill_commission_id = #p(bill_commission_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByBill_commission_idAndSystem_version")
    UPDATE table_bill_commission SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND bill_commission_id = #p(bill_commission_id)
    AND system_version = #p(system_version)
  #end

#end