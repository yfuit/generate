package com.yfuit.generate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller

@SpringBootApplication
@Controller
class GenerateApplication {

    static void main(String[] args) {

        SpringApplication.run GenerateApplication, args
        Runtime.currentRuntime.exec "cmd /c start chrome http://localhost:32992/home.html"
    }

}
