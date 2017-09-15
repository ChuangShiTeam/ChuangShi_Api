#namespace("xietong_course_apply")

  #sql("oneDayCount")
    SELECT COUNT(*)
    FROM table_xietong_course_apply
    LEFT JOIN table_xietong_course ON table_xietong_course.course_id = table_xietong_course_apply.course_id
    WHERE table_xietong_course_apply.system_status = 1
    AND user_id = #p(user_id)
    AND TRUNCATE(table_xietong_course.course_time, -1) = TRUNCATE(#p(course_time), -1)
  #end
  
   #sql("save")
    INSERT INTO table_xietong_course_apply (course_apply_id, app_id, course_id, user_id, system_create_user_id, system_create_time, system_update_user_id, system_update_time, system_status, system_version)
    SELECT #p(course_apply_id), #p(app_id), #p(course_id), #p(user_id), #p(system_create_user_id), #p(system_create_time), #p(system_update_user_id), #p(system_update_time), #p(system_status), #p(system_version) FROM dual
    WHERE NOT EXISTS (SELECT * FROM table_xietong_course_apply WHERE course_id = #p(course_id) AND user_id = #p(user_id) AND system_status = 1)
    AND NOT EXISTS (
                    SELECT COUNT(*) AS count, table_xietong_course_apply.course_id, table_xietong_course.course_apply_limit FROM table_xietong_course_apply
                    LEFT JOIN table_xietong_course ON table_xietong_course.course_id = table_xietong_course_apply.course_id
                    WHERE table_xietong_course_apply.course_id = #p(course_id)
                    AND table_xietong_course_apply.system_status = 1
                    HAVING count >= course_apply_limit
                   )
  #end

#end