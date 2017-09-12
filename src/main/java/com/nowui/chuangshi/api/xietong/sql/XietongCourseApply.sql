#namespace("xietong_course_apply")

#sql("oneDayCount")
    SELECT COUNT(*)
    FROM table_xietong_course_apply
    LEFT JOIN table_xietong_course ON table_xietong_course.course_id = table_xietong_course_apply.course_id
    WHERE table_xietong_course_apply.system_status = 1
    AND user_id = #p(user_id)
    AND TRUNCATE(table_xietong_course.course_time, -1) = TRUNCATE(#p(course_time), -1)
  #end

#end