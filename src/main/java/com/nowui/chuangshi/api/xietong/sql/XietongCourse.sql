#namespace("xietong_course")

  #sql("userList")
    SELECT
    course_id
    FROM table_xietong_course
    WHERE system_status = 1
    AND clazz_id LIKE (SELECT CONCAT('%', clazz_id, '%') FROM table_xietong_student WHERE user_id = #p(user_id))
    ORDER BY course_time, system_create_time DESC
  #end

#end