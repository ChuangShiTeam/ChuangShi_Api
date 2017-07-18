#namespace("product_image")

  #sql("listByProduct_id")
    SELECT
    product_id,
    file_id,
    product_file_sort
    FROM table_product_image
    WHERE system_status = 1
    AND product_id = #p(product_id)
    ORDER BY product_file_sort ASC, system_create_time DESC
  #end

  #sql("save")
  INSERT INTO table_product_image (
      product_id,
      file_id,
      product_file_sort,
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

  #sql("update")
    #for(product_image : product_image_list)
    UPDATE table_product_image SET
    product_file_sort = #(product_image.product_file_sort),
    system_update_user_id = #(product_image.system_update_user_id),
    system_update_time = #(product_image.system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND product_id = #(product_image.product_id)
    AND file_id = #(product_image.file_id)
    #end
  #end

  #sql("delete")
    #for(product_image : product_image_list)
    UPDATE table_product_image SET
    system_update_user_id = #(product_image.system_update_user_id),
    system_update_time = #(product_image.system_update_time),
    system_version = system_version + 1
    WHERE system_status = 0
    AND product_id = #(product_image.product_id)
    AND file_id = #(product_image.file_id)
    #end
  #end

#end