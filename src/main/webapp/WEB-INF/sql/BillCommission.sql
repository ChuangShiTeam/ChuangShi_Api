#namespace("bill_commission")

	#sql("listByOrBill_idOrMember_id")
	    SELECT
	    *
	    FROM table_bill_commission
	    WHERE system_status = 1
	    #if(bill_id)
	    AND bill_id = #p(bill_id)
	    #end
	    #if(member_id)
	    AND member_id = #p(member_id)
	    #end
	    ORDER BY system_create_time DESC
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

  #sql("deleteByBill_idAndSystem_version")
    UPDATE table_bill_commission SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND bill_id = #p(bill_id)
    AND system_version = #p(system_version)
  #end

#end