#namespace("product_sku_attribute")

  #sql("listByProduct_sku_id")
    SELECT
    *
    FROM table_product_sku_attribute
    WHERE system_status = 1
    AND product_sku_id = #p(product_sku_id)
  #end

  #sql("save")
    INSERT INTO table_product_sku_attribute (
      product_sku_id,
      product_sku_attribute_name,
      product_sku_attribute_value,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      ?,
      ?,
      ?,
      ?,
      ?,
      ?,
      ?,
      ?,
      ?
    )
  #end

  #sql("delete")
    UPDATE table_product_sku_attribute SET
    system_update_user_id = ?,
    system_update_time = ?,
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND product_sku_id = ?
  #end

#end