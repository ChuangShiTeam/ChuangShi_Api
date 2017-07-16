#namespace("stock")

  #sql("countByApp_idAndStock_typeOrStock_actionOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock
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
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end
  
  #sql("countByOrApp_idAndStock_typeOrStock_actionOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock
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
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end
  
  #sql("countOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no")
    SELECT COUNT(*) FROM table_stock
    LEFT JOIN table_express ON table_stock.stock_id = table_express.stock_id
    WHERE table_stock.system_status = 1
    AND table_stock.app_id = #p(app_id)
    AND table_stock.stock_type = #p(stock_type)
    AND table_stock.stock_action = 'OUT'
    #if(express_sender_name)
    #set(express_sender_name = "%" + express_sender_name + "%")
    AND table_express.express_sender_name LIKE #p(express_sender_name)
    #end
    #if(stock_receiver_name)
    #set(stock_receiver_name = "%" + stock_receiver_name + "%")
    AND table_stock.stock_receiver_name LIKE #p(stock_receiver_name)
    #end
    #if(express_no)
    #set(express_no = "%" + express_no + "%")
    AND table_express.express_no LIKE #p(express_no)
    #end
  #end
  
  #sql("countOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no")
    SELECT COUNT(*) FROM table_stock
    LEFT JOIN table_express ON table_stock.stock_id = table_express.stock_id
    WHERE table_stock.system_status = 1
    #if(app_id)
    AND table_stock.app_id = #p(app_id)
    #end
    AND table_stock.stock_type = #p(stock_type)
    AND table_stock.stock_action = 'OUT'
    #if(express_sender_name)
    #set(express_sender_name = "%" + express_sender_name + "%")
    AND table_express.express_sender_name LIKE #p(express_sender_name)
    #end
    #if(stock_receiver_name)
    #set(stock_receiver_name = "%" + stock_receiver_name + "%")
    AND table_stock.stock_receiver_name LIKE #p(stock_receiver_name)
    #end
    #if(express_no)
    #set(express_no = "%" + express_no + "%")
    AND table_express.express_no LIKE #p(express_no)
    #end
  #end
  
  #sql("countByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id")   	
   	SELECT
		COUNT(1)
	FROM
		(
			SELECT
				IFNULL(
					SUM(
						CASE
						WHEN table_stock.stock_action = 'OUT' THEN
							- 1 * table_stock_product_sku.product_sku_quantity
						ELSE
							table_stock_product_sku.product_sku_quantity
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
				table_stock_product_sku 
			LEFT JOIN table_stock on table_stock_product_sku.stock_id = table_stock.stock_id
			LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock_product_sku.product_sku_id
			LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
			#if(stock_type == 'MEMBER')
			LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
		    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
		    #end
		    #if(stock_type == 'APP')
		    LEFT JOIN table_app ON table_app.app_id = table_stock.app_id
		    #end
			WHERE 
			 table_stock_product_sku.system_status = 1
		    AND table_stock.system_status = 1
		    AND table_stock.app_id = #p(app_id)
		    #if(stock_type == 'APP')
		    AND (table_stock.stock_type = 'APP' OR table_stock.stock_type = 'TRADE')
		    #end
		    #if(stock_type == 'MEMBER')
		    AND table_stock.stock_type = 'MEMBER'
		    #end
		    #if(product_name)
		    #set(product_name = "%" + product_name + "%")
		    AND table_product.product_name LIKE #p(product_name)
		    #end
		    #if(stock_type == 'MEMBER' && user_name)
		    #set(user_name = "%" + user_name + "%")
		    AND table_user.user_name LIKE #p(user_name)
		    #end
			GROUP BY table_stock.object_id, table_stock_product_sku.product_sku_id
		) AS temp
  #end
  
  #sql("countByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id")   	
   	SELECT
		COUNT(1)
	FROM
		(
			SELECT
				IFNULL(
					SUM(
						CASE
						WHEN table_stock.stock_action = 'OUT' THEN
							- 1 * table_stock_product_sku.product_sku_quantity
						ELSE
							table_stock_product_sku.product_sku_quantity
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
				table_stock_product_sku 
			LEFT JOIN table_stock on table_stock_product_sku.stock_id = table_stock.stock_id
			LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock_product_sku.product_sku_id
			LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
			#if(stock_type == 'MEMBER')
			LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
		    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
		    #end
		    #if(stock_type == 'APP')
		    LEFT JOIN table_app ON table_app.app_id = table_stock.app_id
		    #end
		    #if(stock_type == 'MEMBER')
		    AND table_stock.stock_type = 'MEMBER'
		    #end
			WHERE 
			 table_stock_product_sku.system_status = 1
		    AND table_stock.system_status = 1
		    #if(app_id)
		    AND table_stock.app_id = #p(app_id)
		    #end
		    #if(stock_type == 'APP')
		    AND (table_stock.stock_type = 'APP' OR table_stock.stock_type = 'TRADE')
		    #end
		    #if(product_name)
		    #set(product_name = "%" + product_name + "%")
		    AND table_product.product_name LIKE #p(product_name)
		    #end
		    #if(stock_type == 'MEMBER' && user_name)
		    #set(user_name = "%" + user_name + "%")
		    AND table_user.user_name LIKE #p(user_name)
		    #end
			GROUP BY table_stock.object_id, table_stock_product_sku.product_sku_id
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

  #sql("listByApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit")
    SELECT
    table_stock.stock_id
    FROM table_stock
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
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_stock.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit")
    SELECT
    table_stock.stock_id
    FROM table_stock
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
					- 1 * table_stock_product_sku.product_sku_quantity
				ELSE
					table_stock_product_sku.product_sku_quantity
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
		table_stock_product_sku 
	LEFT JOIN table_stock on table_stock_product_sku.stock_id = table_stock.stock_id
	LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock_product_sku.product_sku_id
	LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
	#if(stock_type == 'MEMBER')
	LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
    #end
    #if(stock_type == 'APP')
    LEFT JOIN table_app ON table_app.app_id = table_stock.app_id
    #end
	WHERE 
	 table_stock_product_sku.system_status = 1
    AND table_stock.system_status = 1
    AND table_stock.app_id = #p(app_id)
    #if(stock_type == 'APP')
    AND (table_stock.stock_type = 'APP' OR table_stock.stock_type = 'TRADE')
    #end
    #if(stock_type == 'MEMBER')
    AND table_stock.stock_type = 'MEMBER'
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
	GROUP BY table_stock.object_id, table_stock_product_sku.product_sku_id
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit")
    SELECT
		IFNULL(
			SUM(
				CASE
				WHEN table_stock.stock_action = 'OUT' THEN
					- 1 * table_stock_product_sku.product_sku_quantity
				ELSE
					table_stock_product_sku.product_sku_quantity
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
		table_stock_product_sku 
	LEFT JOIN table_stock on table_stock_product_sku.stock_id = table_stock.stock_id
	LEFT JOIN table_product_sku ON table_product_sku.product_sku_id = table_stock_product_sku.product_sku_id
	LEFT JOIN table_product ON table_product.product_id = table_product_sku.product_id
	#if(stock_type == 'MEMBER')
	LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
    #end
    #if(stock_type == 'APP')
    LEFT JOIN table_app ON table_app.app_id = table_stock.app_id
    #end
	WHERE 
	 table_stock_product_sku.system_status = 1
    AND table_stock.system_status = 1
    #if(app_id)
    AND table_stock.app_id = #p(app_id)
    #end
    #if(stock_type == 'APP')
    AND (table_stock.stock_type = 'APP' OR table_stock.stock_type = 'TRADE')
    #end
    #if(stock_type == 'MEMBER')
    AND table_stock.stock_type = 'MEMBER'
    #end
    #if(product_name)
    #set(product_name = "%" + product_name + "%")
    AND table_product.product_name LIKE #p(product_name)
    #end
    #if(stock_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
	GROUP BY table_stock.object_id, table_stock_product_sku.product_sku_id
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no")
    SELECT 
    	table_stock.*,
    	table_express.express_sender_name,
    	table_express.express_no
    FROM table_stock
    LEFT JOIN table_express ON table_stock.stock_id = table_express.stock_id
    WHERE table_stock.system_status = 1
    AND table_stock.app_id = #p(app_id)
    AND table_stock.stock_action = 'OUT'
    #if(express_sender_name)
    #set(express_sender_name = "%" + express_sender_name + "%")
    AND table_express.express_sender_name LIKE #p(express_sender_name)
    #end
    #if(stock_receiver_name)
    #set(stock_receiver_name = "%" + stock_receiver_name + "%")
    AND table_stock.stock_receiver_name LIKE #p(stock_receiver_name)
    #end
    #if(express_no)
    #set(express_no = "%" + express_no + "%")
    AND table_express.express_no LIKE #p(express_no)
    #end
    ORDER BY table_stock.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no")
    SELECT 
    	table_stock.*,
    	table_express.express_sender_name,
    	table_express.express_no
    FROM table_stock
    LEFT JOIN table_express ON table_stock.stock_id = table_express.stock_id
    WHERE table_stock.system_status = 1
    #if(app_id)
    AND table_stock.app_id = #p(app_id)
    #end
    AND table_stock.stock_action = 'OUT'
    #if(express_sender_name)
    #set(express_sender_name = "%" + express_sender_name + "%")
    AND table_express.express_sender_name LIKE #p(express_sender_name)
    #end
    #if(stock_receiver_name)
    #set(stock_receiver_name = "%" + stock_receiver_name + "%")
    AND table_stock.stock_receiver_name LIKE #p(stock_receiver_name)
    #end
    #if(express_no)
    #set(express_no = "%" + express_no + "%")
    AND table_express.express_no LIKE #p(express_no)
    #end
    ORDER BY table_stock.system_create_time DESC
    LIMIT #p(m), #p(n)
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
  
  #sql("findByStock_id")
    SELECT
    *
    FROM table_stock    
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
  #end
  
  #sql("findWithMemberByStock_id")
    SELECT
    table_stock.*,
    table_user.user_name
    FROM table_stock
    LEFT JOIN table_member ON table_member.member_id = table_stock.object_id
    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
    WHERE table_stock.system_status = 1
    AND table_stock.stock_id = #p(stock_id)
  #end
  
  #sql("findWithTradeByStock_id")
    SELECT
    table_stock.*,
    table_user.user_name
    FROM table_stock
    LEFT JOIN table_trade ON table_trade.trade_id = table_stock.object_id
    LEFT JOIN table_user ON table_trade.user_id = table_user.user_id
    WHERE table_stock.system_status = 1
    AND table_stock.stock_id = #p(stock_id)
  #end
  
  #sql("findWithAppByStock_id")
    SELECT
    table_stock.*,
    table_app.app_name
    FROM table_stock
    LEFT JOIN table_app ON table_app.app_id = table_stock.object_id
    WHERE table_stock.system_status = 1
    AND table_stock.stock_id = #p(stock_id)
  #end
  
  #sql("sumStock_quantityByObject_idAndProduct_sku_id")
    SELECT
    IFNULL(SUM(case when table_stock.stock_action = 'OUT' then -1*table_stock_product_sku.product_sku_quantity else table_stock_product_sku.product_sku_quantity end), 0)
    FROM table_stock_product_sku
    LEFT JOIN table_stock ON table_stock_product_sku.stock_id = table_stock.stock_id
    WHERE table_stock.system_status = 1
    AND table_stock_product_sku.system_status = 1
    AND table_stock.object_id = #p(object_id)
    AND table_stock_product_sku.product_sku_id = #p(product_sku_id)
  #end
  
  #sql("save")
    INSERT INTO table_stock (
      stock_id,
      app_id,
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
      stock_express_pay_way,
      stock_express_shipper_code,
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
      #p(stock_express_pay_way),
      #p(stock_express_shipper_code),
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
    stock_express_pay_way = #p(stock_express_pay_way),
    stock_express_shipper_code = #p(stock_express_shipper_code),
    stock_is_pay = #p(stock_is_pay),
    stock_status = #p(stock_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("updateStock_flowByStock_idAndSystem_version")
    UPDATE table_stock SET
    stock_flow = #p(stock_flow),
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