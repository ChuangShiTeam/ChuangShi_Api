#namespace("menu")

  #sql("listByApp_id")
    SELECT
    *
    FROM table_menu
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY menu_sort ASC, system_create_time DESC
  #end

  #sql("listByMenu_parent_pathLikeMenu_parent_id")
    SELECT
    menu_id
    FROM table_menu
    WHERE system_status = 1
    AND menu_parent_path LIKE #p("%" + menu_parent_id + "%")
  #end

  #sql("listByApp_idOrLikeMenu_name")
    SELECT
    *
    FROM table_menu
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND menu_parent_id != "0"
    UNION ALL
    SELECT
    *
    FROM table_menu
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND menu_parent_id = "0"
    #if(menu_name)
    #set(menu_name = "%" + menu_name + "%")
    AND menu_name LIKE #p(menu_name)
    #end
    ORDER BY menu_sort ASC, system_create_time DESC
  #end

  #sql("listByOrApp_idOrLikeMenu_name")
    SELECT
    menu_id, menu_sort, system_create_time
    FROM table_menu
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    AND menu_parent_id != "0"
    UNION ALL
    SELECT
    menu_id, menu_sort, system_create_time
    FROM table_menu
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    AND menu_parent_id = "0"
    #if(menu_name)
    #set(menu_name = "%" + menu_name + "%")
    AND menu_name LIKE #p(menu_name)
    #end
    ORDER BY menu_sort ASC, system_create_time DESC
  #end

  #sql("findByMenu_id")
    SELECT
    *
    FROM table_menu
    WHERE system_status = 1
    AND menu_id = #p(menu_id)
  #end

  #sql("save")
    INSERT INTO table_menu (
      menu_id,
      app_id,
      menu_parent_id,
      menu_name,
      menu_image,
      menu_url,
      menu_sort,
      menu_parent_path,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(menu_id),
      #p(app_id),
      #p(menu_parent_id),
      #p(menu_name),
      #p(menu_image),
      #p(menu_url),
      #p(menu_sort),
      #p(menu_parent_path),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_menu SET
    menu_parent_id = #p(menu_parent_id),
    menu_name = #p(menu_name),
    menu_image = #p(menu_image),
    menu_url = #p(menu_url),
    menu_sort = #p(menu_sort),
    menu_parent_path = #p(menu_parent_path),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND menu_id = #p(menu_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByMenu_idAndSystem_version")
    UPDATE table_menu SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND menu_id = #p(menu_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByMenu_parent_id")
    UPDATE table_menu SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND menu_parent_id = #p(menu_parent_id)
  #end

#end