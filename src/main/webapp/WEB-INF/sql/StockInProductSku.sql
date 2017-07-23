#namespace("stock_in_product_sku")

  #sql("listByStock_in_id")
    SELECT
    *
    FROM table_stock_in_product_sku
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(stock_in_product_sku_name)
    #set(stock_in_product_sku_name = "%" + stock_in_product_sku_name + "%")
    AND stock_in_product_sku_name LIKE #p(stock_in_product_sku_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByStock_in_product_sku_id")
    SELECT
    *
    FROM table_stock_in_product_sku
    WHERE system_status = 1
    AND stock_in_product_sku_id = #p(stock_in_product_sku_id)
  #end

  #sql("save")
    INSERT INTO table_stock_in_product_sku (
      stock_in_id,
      product_sku_id,
      product_sku_quantity,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_in_id),
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

  #sql("update")
    UPDATE table_stock_in_product_sku SET
    stock_in_id = #p(stock_in_id),
    product_sku_id = #p(product_sku_id),
    product_sku_quantity = #p(product_sku_quantity),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND stock_in_product_sku_id = #p(stock_in_product_sku_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByStock_in_product_sku_idAndSystem_version")
    UPDATE table_stock_in_product_sku SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND stock_in_product_sku_id = #p(stock_in_product_sku_id)
    AND system_version = #p(system_version)
  #end

#end