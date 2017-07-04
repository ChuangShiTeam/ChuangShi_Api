#namespace("product_sku_price")

  #sql("save")
    INSERT INTO table_product_sku_price (
      product_sku_id,
      member_level_id,
      member_level_name,
      product_sku_price,
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
      ?,
      ?
    )
  #end

  #sql("update")
    UPDATE table_product_sku_price SET
    member_level_name = ?,
    product_sku_price = ?,
    system_update_user_id = ?,
    system_update_time = ?,
    system_version = system_version + 1
    WHERE system_status = 1
    AND product_sku_id = ?
    AND member_level_id = ?
  #end

  #sql("deleteByProduct_sku_idAndMember_level_id")
    UPDATE table_product_sku_price SET
    system_update_user_id = ?,
    system_update_time = ?,
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND product_sku_id = ?
    AND member_level_id = ?
  #end

#end