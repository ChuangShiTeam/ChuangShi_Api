#namespace("category")

  #sql("countByApp_id")
    SELECT COUNT(*) FROM table_category
    WHERE system_status = 1
    AND app_id = #p(app_id)
  #end

  #sql("countByOrApp_id")
    SELECT COUNT(*) FROM table_category
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
  #end

  #sql("countByOrApp_idAndParent_id")
    SELECT COUNT(*) FROM table_category
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    AND parent_id = #p(parent_id)
  #end

  #sql("countByOrApp_idAndNotParent_idAndCategory_nameAndCategory_type")
    SELECT COUNT(*) FROM table_category
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    AND parent_id != #p(parent_id)
    #if(category_name)
      #set(category_name = "%" + category_name + "%")
      AND category_name LIKE #p(category_name)
    #end
    AND category_type = #p(category_type)
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    category_id
    FROM table_category
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY category_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idAndLimit")
    SELECT
    category_id
    FROM table_category
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY category_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndParent_idAndLimit")
    SELECT
    category_id
    FROM table_category
    WHERE system_status = 1
    #if(app_id)
      AND app_id = #p(app_id)
    #end
    AND parent_id = #p(parent_id)
    ORDER BY category_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndNotParent_idAndCategory_nameAndCategory_typeAndLimit")
    SELECT
    category_id
    FROM table_category
    WHERE system_status = 1
    #if(app_id)
      AND app_id = #p(app_id)
    #end
    AND parent_id != #p(parent_id)
    #if(category_name)
      #set(category_name = "%" + category_name + "%")
      AND category_name LIKE #p(category_name)
    #end
    AND category_type = #p(category_type)
    ORDER BY category_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndLimit")
    SELECT
    category_id
    FROM table_category
    WHERE system_status = 1
    #if(app_id)
      AND app_id = #p(app_id)
    #end
    ORDER BY category_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("treeByParent_id")
    SELECT
    category_id
    FROM table_category
    WHERE system_status = 1
    AND category_path LIKE #p("%" + parent_id + "%")
    ORDER BY category_sort ASC, system_create_time DESC
  #end

  #sql("findByCategory_id")
    SELECT
    *
    FROM table_category
    WHERE system_status = 1
    AND category_id = #p(category_id)
  #end

  #sql("save")
    INSERT INTO table_category (
      category_id,
      app_id,
      parent_id,
      category_name,
      category_image,
      category_key,
      category_value,
      category_path,
      category_sort,
      category_type,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(category_id),
      #p(app_id),
      #p(parent_id),
      #p(category_name),
      #p(category_image),
      #p(category_key),
      #p(category_value),
      #p(category_path),
      #p(category_sort),
      #p(category_type),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_category SET
    parent_id = #p(parent_id),
    category_name = #p(category_name),
    category_image = #p(category_image),
    category_key = #p(category_key),
    category_value = #p(category_value),
    category_path = #p(category_path),
    category_sort = #p(category_sort),
    category_type = #p(category_type),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND category_id = #p(category_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByCategory_idAndSystem_version")
    UPDATE table_category SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND category_id = #p(category_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByParent_id")
    UPDATE table_category SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND parent_id = #p(parent_id)
  #end

#end