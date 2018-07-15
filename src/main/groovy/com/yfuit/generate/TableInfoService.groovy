package com.yfuit.generate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TableInfoService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    GenerateProperties generateProperties;

    Map<String,Object> getTableInfo(String tableName){

        Map<String,Object> map = new HashMap();
        map.put("tableName",tableName);

        String sql = "select * from information_schema.columns" +
                " where table_schema='${generateProperties.schema}' and table_name = '${tableName}'";
        map.put("columns",jdbcTemplate.queryForList(sql));


        sql = "select * from information_schema.tables " +
                "where table_schema='${generateProperties.schema}' and table_name='${tableName}'";
        map.put("table",jdbcTemplate.queryForMap(sql));

        return map;
    }

}
