#namespace("member_level")

  #sql("countByApp_idOrLikeMember_level_name")
    SELECT COUNT(*) FROM table_member_level
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(member_level_name)
    #set(member_level_name = "%" + member_level_name + "%")
    AND member_level_name LIKE #p(member_level_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeMember_level_name")
    SELECT COUNT(*) FROM table_member_level
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(member_level_name)
    #set(member_level_name = "%" + member_level_name + "%")
    AND member_level_name LIKE #p(member_level_name)
    #end
  #end

  #sql("listByApp_id")
    SELECT
    member_level_id
    FROM table_member_level
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY member_level_sort ASC, system_create_time DESC
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    member_level_id
    FROM table_member_level
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY member_level_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeMember_level_nameAndLimit")
    SELECT
    member_level_id,
    member_level_value
    FROM table_member_level
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(member_level_name)
    #set(member_level_name = "%" + member_level_name + "%")
    AND member_level_name LIKE #p(member_level_name)
    #end
    ORDER BY member_level_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeMember_level_nameAndLimit")
    SELECT
    member_level_id
    FROM table_member_level
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(member_level_name)
    #set(member_level_name = "%" + member_level_name + "%")
    AND member_level_name LIKE #p(member_level_name)
    #end
    ORDER BY member_level_sort ASC, system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByMember_level_id")
    SELECT
    *
    FROM table_member_level
    WHERE system_status = 1
    AND member_level_id = #p(member_level_id)
  #end

  #sql("save")
    INSERT INTO table_member_level (
      member_level_id,
      app_id,
      member_level_name,
      member_level_value,
      member_level_sort,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(member_level_id),
      #p(app_id),
      #p(member_level_name),
      #p(member_level_value),
      #p(member_level_sort),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_member_level SET
    member_level_name = #p(member_level_name),
    member_level_value = #p(member_level_value),
    member_level_sort = #p(member_level_sort),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_level_id = #p(member_level_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByMember_level_idAndSystem_version")
    UPDATE table_member_level SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_level_id = #p(member_level_id)
    AND system_version = #p(system_version)
  #end

#end