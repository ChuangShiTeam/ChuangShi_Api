#namespace("delivery_order_product_sku")

  #sql("listByDelivery_order_id")
    SELECT
    table_delivery_order_product_sku.*,
    table_product.product_name
    FROM table_delivery_order_product_sku
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_delivery_order_product_sku.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    WHERE table_delivery_order_product_sku.system_status = 1
    AND table_delivery_order_product_sku.delivery_order_id = #p(delivery_order_id)
    ORDER BY table_delivery_order_product_sku.system_create_time DESC
  #end

  #sql("save")
    INSERT INTO table_delivery_order_product_sku (
      delivery_order_id,
      product_sku_id,
      product_sku_quantity,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(delivery_order_id),
      #p(product_sku_id),
      #p(product_sku_quantity),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByDelivery_order_id")
    UPDATE table_delivery_order_product_sku SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND delivery_order_id = #p(delivery_order_id)
  #end

#end