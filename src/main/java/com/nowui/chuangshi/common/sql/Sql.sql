#namespace("sql")

  #sql("count")
    SELECT COUNT(*) FROM #(table_name)
    #set(condition = "WHERE")
    #for(attribute : attribute_list)
    #(condition) #(attribute.key) = #p(attribute.value)
    #set(condition = "AND")
    #end
  #end

#end