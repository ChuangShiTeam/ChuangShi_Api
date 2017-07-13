#namespace("stock")

  #sql("countByApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock
    #if(product_name)
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    #end
    #if(stock_type == 'MEMBER' && user_name)
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_user.user_id = table_member.user_id
    #end
    WHERE table_stock.system_status = 1
    AND table_stock.app_id = #p(app_id)
    AND table_stock.stock_type = #p(stock_type)
    #if(stock_action)
    AND table_stock.stock_action = #p(stock_action)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end
  
  #sql("countByOrApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock
    #if(product_name)
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    #end
    #if(stock_type == 'MEMBER' && user_name)
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_user.user_id = table_member.user_id
    #end
    WHERE table_stock.system_status = 1
    #if(app_id)
    AND table_stock.app_id = #p(app_id)
    #end
    AND stock_type = #p(stock_type)
    #if(stock_action)
    AND stock_action = #p(stock_action)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end
  
  #sql("countByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id")   	
   	SELECT
		COUNT(1)
	FROM
		(
			SELECT
				table_stock.*
			FROM
				table_stock
				LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
				LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
				#if(stock_type == 'MEMBER')
				LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
			    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
			    #end
			    #if(stock_type == 'APP')
			    LEFT JOIN table_app ON table_app.app_id = table_stock.object_id
			    #end
			WHERE
				table_stock.system_status = 1
				AND table_stock.app_id = #p(app_id)
			    AND table_stock.stock_type = #p(stock_type)

			GROUP BY
				table_stock.object_id,
				table_stock.product_sku_id
		) AS temp
  #end
  
  #sql("countByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id")   	
   	SELECT
		COUNT(1)
	FROM
		(
			SELECT
				table_stock.*
			FROM
				table_stock
			WHERE
				table_stock.system_status = 1
				#if(app_id)
			    AND table_stock.app_id = #p(app_id)
			    #end
			    #if(stock_type)
			    AND table_stock.stock_type = #p(stock_type)
			    #end
			GROUP BY
				table_stock.object_id,
				table_stock.product_sku_id
		) AS temp
  #end

  #sql("listByApp_idAndStock_typeAndSystem_create_timeAndLimit")
    SELECT
    stock_id
    FROM table_stock
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND stock_type = #p(stock_type)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_nameAndLimit")
    SELECT
    table_stock.stock_id
    FROM table_stock
    #if(product_name)
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    #end
    #if(stock_type == 'MEMBER' && user_name)
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_user.user_id = table_member.user_id
    #end
    WHERE table_stock.system_status = 1
    AND table_stock.app_id = #p(app_id)
    AND table_stock.stock_type = #p(stock_type)
    #if(stock_action)
    AND table_stock.stock_action = #p(stock_action)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_stock.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_nameAndLimit")
    SELECT
    table_stock.stock_id
    FROM table_stock
    #if(product_name)
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    #end
    #if(stock_type == 'MEMBER' && user_name)
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_user.user_id = table_member.user_id
    #end
    WHERE table_stock.system_status = 1
    #if(app_id)
    AND table_stock.app_id = #p(app_id)
    #end
    AND table_stock.stock_type = #p(stock_type)
    #if(stock_action)
    AND table_stock.stock_action = #p(stock_action)
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_stock.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit")
    SELECT
		IFNULL(
			SUM(
				CASE
				WHEN table_stock.stock_action = 'OUT' THEN
					- 1 * table_stock.stock_quantity
				ELSE
					table_stock.stock_quantity
				END
			),
			0
		) as sum_stock_quantity,
		#if(stock_type == 'MEMBER')
		table_user.user_name,
		#end
		#if(stock_type == 'APP')
		table_app.app_name,
		#end
		table_product.product_name
	FROM
		table_stock
	LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
	LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
	#if(stock_type == 'MEMBER')
	LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
    #end
    #if(stock_type == 'APP')
    LEFT JOIN table_app ON table_app.app_id = table_stock.object_id
    #end
	WHERE 
	 table_stock.system_status = 1
	 AND table_stock.app_id = #p(app_id)
     AND table_stock.stock_type = #p(stock_type)
     #if(product_name)
     #set(product_name = "%" + product_name + "%")
     AND table_product.product_name LIKE #p(product_name)
     #end
     #if(stock_type == 'MEMBER' && user_name)
     #set(user_name = "%" + user_name + "%")
     AND table_user.user_name LIKE #p(user_name)
     #end
	 GROUP BY table_stock.object_id, table_stock.product_sku_id
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit")
    SELECT
		IFNULL(
			SUM(
				CASE
				WHEN table_stock.stock_action = 'OUT' THEN
					- 1 * table_stock.stock_quantity
				ELSE
					table_stock.stock_quantity
				END
			),
			0
		) as sum_stock_quantity,
		#if(stock_type == 'MEMBER')
		table_user.user_name,
		#end
		#if(stock_type == 'APP')
		table_app.app_name,
		#end
		table_product.product_name
	FROM
		table_stock
	LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
	LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
	#if(stock_type == 'MEMBER')
	LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
    #end
    #if(stock_type == 'APP')
    LEFT JOIN table_app ON table_app.app_id = table_stock.object_id
    #end
	WHERE 
	 table_stock.system_status = 1
	 #if(app_id)
	 AND table_stock.app_id = #p(app_id)
     #end
     AND table_stock.stock_type = #p(stock_type)
     #if(product_name)
     #set(product_name = "%" + product_name + "%")
     AND table_product.product_name LIKE #p(product_name)
     #end
     #if(stock_type == 'MEMBER' && user_name)
     #set(user_name = "%" + user_name + "%")
     AND table_user.user_name LIKE #p(user_name)
     #end
	 GROUP BY table_stock.object_id, table_stock.product_sku_id
    LIMIT #p(m), #p(n)
  #end
  
  #sql("findByStock_id")
    SELECT
    *
    FROM table_stock    
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
  #end
  
  #sql("listByApp_idAndObject_idGroupByObject_idAndProduct_sku_id")
    SELECT
		IFNULL(
			SUM(
				CASE
				WHEN table_stock.stock_action = 'OUT' THEN
					- 1 * table_stock.stock_quantity
				ELSE
					table_stock.stock_quantity
				END
			),
			0
		) as sum_stock_quantity,
		table_product.product_name
	FROM
		table_stock
	LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
	LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
	WHERE 
	 table_stock.system_status = 1
	 AND table_stock.app_id = #p(app_id)
     AND table_stock.object_id = #p(object_id)
	 GROUP BY table_stock.object_id, table_stock.product_sku_id
  #end
  
  #sql("listByApp_idAndObject_id")
    SELECT
		table_stock.*
		table_product.product_name
	FROM
		table_stock
	LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
	LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
	WHERE 
	 table_stock.system_status = 1
	 AND table_stock.app_id = #p(app_id)
     AND table_stock.object_id = #p(object_id)
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listByApp_idAndObject_id")
    SELECT
		table_stock.stock_id AS object_id,
		table_stock.product_sku_id,
		table_stock.stock_quantity AS quantity,
		table_stock.stock_receiver_name AS receiver_name,
		'会员发货' AS object_type
	FROM
		table_stock
	WHERE
		table_stock.stock_type = 'MEMBER'
	AND table_stock.stock_action = 'OUT'
	UNION
		SELECT
			table_trade.trade_id AS object_id,
			table_trade_product_sku.product_sku_id,
			table_trade.trade_product_quantity AS quantity,
			table_trade.trade_receiver_name AS receiver_name,
			'会员下订单' AS object_type
		FROM
			table_trade
		LEFT JOIN table_trade_product_sku ON table_trade_product_sku.trade_id = table_trade.trade_id
  #end
  
  #sql("findWithMemberByStock_id")
    SELECT
    table_stock.*,
    table_user.user_name,
    table_product.product_name
    FROM table_stock
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
    WHERE table_stock.system_status = 1
    AND table_stock.stock_id = #p(stock_id)
  #end
  
  #sql("findWithAppByStock_id")
    SELECT
    table_stock.*,
    table_app.app_name,
    table_product.product_name
    FROM table_stock
    LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock.product_sku_id
    LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
    LEFT JOIN table_app ON table_app.app_id = table_stock.object_id
    WHERE table_stock.system_status = 1
    AND table_stock.stock_id = #p(stock_id)
  #end
  
  #sql("sumStock_quantityByObject_idAndProduct_sku_id")
    SELECT
    IFNULL(SUM(case when stock_action = 'OUT' then -1*stock_quantity else stock_quantity end), 0)
    FROM table_stock
    WHERE system_status = 1
    AND object_id = #p(object_id)
    AND product_sku_id = #p(product_sku_id)
  #end
  
  #sql("save")
    INSERT INTO table_stock (
      stock_id,
      app_id,
      product_sku_id,
      object_id,
      stock_type,
      stock_quantity,
      stock_receiver_name,
      stock_receiver_mobile,
      stock_receiver_province,
      stock_receiver_city,
      stock_receiver_area,
      stock_receiver_address,
      stock_action,
      stock_flow,
      stock_is_pay,
      stock_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_id),
      #p(app_id),
      #p(product_sku_id),
      #p(object_id),
      #p(stock_type),
      #p(stock_quantity),
      #p(stock_receiver_name),
      #p(stock_receiver_mobile),
      #p(stock_receiver_province),
      #p(stock_receiver_city),
      #p(stock_receiver_area),
      #p(stock_receiver_address),
      #p(stock_action),
      #p(stock_flow),
      #p(stock_is_pay),
      #p(stock_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_stock SET
    product_sku_id = #p(product_sku_id),
    object_id = #p(object_id),
    stock_type = #p(stock_type),
    stock_quantity = #p(stock_quantity),
    stock_receiver_name = #p(stock_receiver_name),
    stock_receiver_mobile = #p(stock_receiver_mobile),
    stock_receiver_province = #p(stock_receiver_province),
    stock_receiver_city = #p(stock_receiver_city),
    stock_receiver_area = #p(stock_receiver_area),
    stock_receiver_address = #p(stock_receiver_address),
    stock_action = #p(stock_action),
    stock_flow = #p(stock_flow),
    stock_is_pay = #p(stock_is_pay),
    stock_status = #p(stock_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByStock_idAndSystem_version")
    UPDATE table_stock SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
    AND system_version = #p(system_version)
  #end

#end