package cn.com.architecture.learn;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping("/")
    String hello() {
        return "Hello SpringBootApplication";
    }
}
