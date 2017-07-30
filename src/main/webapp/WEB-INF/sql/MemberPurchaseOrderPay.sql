#namespace("member_purchase_order_pay")

  #sql("listByMember_purchase_order_id")
    SELECT
    *
    FROM table_member_purchase_order_pay
    WHERE system_status = 1
    AND member_purchase_order_id = #p(member_purchase_order_id)
    ORDER BY system_create_time DESC
  #end

  #sql("save")
    INSERT INTO table_member_purchase_order_pay (
      member_purchase_order_id,
      member_purchase_order_pay_type,
      member_purchase_order_pay_number,
      member_purchase_order_pay_account,
      member_purchase_order_pay_time,
      member_purchase_order_pay_result,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(member_purchase_order_id),
      #p(member_purchase_order_pay_type),
      #p(member_purchase_order_pay_number),
      #p(member_purchase_order_pay_account),
      #p(member_purchase_order_pay_time),
      #p(member_purchase_order_pay_result),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByMember_purchase_order_id")
    UPDATE table_member_purchase_order_pay SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_purchase_order_id = #p(member_purchase_order_id)
  #end

#end