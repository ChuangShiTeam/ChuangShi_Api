#namespace("menu_api")

  #sql("countByMenu_idAndApi_id")
    SELECT COUNT(*) FROM table_menu_api
    WHERE system_status = 1
    AND menu_id = #p(menu_id)
    AND api_id = #p(api_id)
  #end

  #sql("listByMenu_id")
    SELECT
    *
    FROM table_menu_api
    WHERE system_status = 1
    AND menu_id = #p(menu_id)
    ORDER BY menu_api_sort ASC, system_create_time DESC
  #end

  #sql("save")
    INSERT INTO table_menu_api (
      menu_id,
      api_id,
      menu_api_sort,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(menu_id),
      #p(api_id),
      #p(menu_api_sort),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByMenu_id")
    UPDATE table_menu_api SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND menu_id = #p(menu_id)
  #end

  #sql("deleteByMenu_idAndApi_id")
    UPDATE table_menu_api SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND menu_id = #p(menu_id)
    AND api_id = #p(api_id)
  #end

#end