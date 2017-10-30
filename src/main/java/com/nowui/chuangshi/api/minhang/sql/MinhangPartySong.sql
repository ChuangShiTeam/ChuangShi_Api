#namespace("minhang_party_song")

#sql("prevSong")
  	SELECT *
  	FROM table_minhang_party_song
  	WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time > #p(system_create_time)
    ORDER BY system_create_time 
    LIMIT 0, 1
  #end
  
  #sql("nextSong")
  	SELECT *
  	FROM table_minhang_party_song
  	WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < #p(system_create_time)
    ORDER BY system_create_time desc
    LIMIT 0, 1
  #end

#end