#namespace("guangqi_prize")

  #sql("countByApp_id")
    SELECT COUNT(*) FROM table_guangqi_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
  #end

  #sql("countByOrApp_id")
    SELECT COUNT(*) FROM table_guangqi_prize
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    prize_id
    FROM table_guangqi_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY prize_sort DESC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idAndPrize_nameAndLimit")
    SELECT
    prize_id
    FROM table_guangqi_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(prize_name)
    #set(prize_name = "%" + prize_name + "%")
    AND prize_name LIKE #p(prize_name)
    #end
    ORDER BY prize_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_id")
    SELECT
    prize_id
    FROM table_guangqi_prize
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY prize_sort ASC, system_create_time DESC
  #end

  #sql("listByOrApp_idAndLimit")
    SELECT
    prize_id
    FROM table_guangqi_prize
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    ORDER BY prize_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByPrize_id")
    SELECT
    *
    FROM table_guangqi_prize
    WHERE system_status = 1
    AND prize_id = #p(prize_id)
  #end

  #sql("save")
    INSERT INTO table_guangqi_prize (
      prize_id,
      app_id,
      prize_name,
      prize_probability,
      prize_quantity,
      prize_limit,
      prize_sort,
      prize_is_default,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(prize_id),
      #p(app_id),
      #p(prize_name),
      #p(prize_probability),
      #p(prize_quantity),
      #p(prize_limit),
      #p(prize_sort),
      #p(prize_is_default),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_guangqi_prize SET
    prize_name = #p(prize_name),
    prize_probability = #p(prize_probability),
    prize_quantity = #p(prize_quantity),
    prize_limit = #p(prize_limit),
    prize_sort = #p(prize_sort),
    prize_is_default = #p(prize_is_default),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND prize_id = #p(prize_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByPrize_idAndSystem_version")
    UPDATE table_guangqi_prize SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND prize_id = #p(prize_id)
    AND system_version = #p(system_version)
  #end

#end