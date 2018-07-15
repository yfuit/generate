package com.yfuit.generate

import com.yfuit.generate.tpl.tplController
import com.yfuit.generate.tpl.tplMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GenerateController {

    @Autowired
    TableInfoService tableInfoService;

    @ResponseBody
    @RequestMapping("/generate.html")
    String generate(String packageName,String tableName,String method){

        println("packageName:${packageName},tableName:${tableName},method:${method}")

        def tpl = "请编写demo";
        switch (method){
            case "Controller": tpl= tplController.content;break;
            case "Mapper": tpl = tplMapper.content;break;
        }

        def template = new groovy.text.StreamingTemplateEngine().createTemplate(tpl)
        Map<String,Object> map = tableInfoService.getTableInfo(tableName);
        map.put("packageName",packageName)
        String value = template.make(map);
        //println("value:${value}");
        return value;
    }

}
