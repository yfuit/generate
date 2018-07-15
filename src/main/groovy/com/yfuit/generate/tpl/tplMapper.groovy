package com.yfuit.generate.tpl;

public class tplMapper {

static def content = '''\
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.ttm.dao.BannerDao">

    <resultMap type="${packageName}.${tableName.toPO()}" id="${tableName.toEO()}Mapper">
<% columns.each({ %>\
<% if("PRI".equals(it.COLUMN_KEY)){ %>\
        <id column="${it.COLUMN_NAME}" property="${it.COLUMN_NAME.toEO()}" />
<%}else{%>\
        <result column="${it.COLUMN_NAME}" property="${it.COLUMN_NAME.toEO()}" />
<%}%>\
<% });%>\
    </resultMap>
    
    <select id="query"  resultMap="${tableName.toPO()}Mapper">
       select a.* from ${tableName} a
        where a.deleted = 'Y'
        <if test="s_title != null and s_title != ''">
          and a.title like CONCAT('%', #{s_title}, '%') 
        </if>
        order by a.sort asc
        limit #{pageCurrent},#{pageLimit} 
    </select>
    
</mapper>
''';

}
