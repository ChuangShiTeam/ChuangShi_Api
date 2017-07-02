#namespace("admin")

  #sql("countByApp_idOrLikeUser_name")
    SELECT COUNT(*) FROM table_admin
    #if(user_name)
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    #end
    WHERE table_admin.system_status = 1
    AND table_admin.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeUser_name")
    SELECT COUNT(*) FROM table_admin
    #if(user_name)
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    #end
    WHERE table_admin.system_status = 1
    #if(app_id)
    AND table_admin.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    admin_id
    FROM table_admin
    #if(user_name)
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    #end
    WHERE table_admin.system_status = 1
    AND table_admin.app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeUser_nameAndLimit")
    SELECT
    admin_id
    FROM table_admin
    #if(user_name)
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    #end
    WHERE table_admin.system_status = 1
    AND table_admin.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_admin.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeUser_nameAndLimit")
    SELECT
    admin_id
    FROM table_admin
    #if(user_name)
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    #end
    WHERE table_admin.system_status = 1
    #if(app_id)
    AND table_admin.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_admin.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByAdmin_id")
    SELECT
    *
    FROM table_admin
    WHERE system_status = 1
    AND admin_id = #p(admin_id)
  #end

  #sql("save")
    INSERT INTO table_admin (
      admin_id,
      app_id,
      user_id,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(admin_id),
      #p(app_id),
      #p(user_id),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_admin SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND admin_id = #p(admin_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByAdmin_idAndSystem_version")
    UPDATE table_admin SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND admin_id = #p(admin_id)
    AND system_version = #p(system_version)
  #end

#end