#namespace("member_delivery_order_product_sku")

  #sql("listByMember_delivery_order_id")
    SELECT
    *
    FROM table_member_delivery_order_product_sku
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
    ORDER BY system_create_time DESC
  #end

  #sql("save")
    INSERT INTO table_member_delivery_order_product_sku (
      member_delivery_order_id,
      product_sku_id,
      product_snap_id,
      product_sku_quantity,
      product_sku_amount,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(member_delivery_order_id),
      #p(product_sku_id),
      #p(product_snap_id),
      #p(product_sku_quantity),
      #p(product_sku_amount),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByMember_delivery_order_id")
    UPDATE table_member_delivery_order_product_sku SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
  #end

#end