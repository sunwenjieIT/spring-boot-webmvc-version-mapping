package xyz.wenjiesx.webmvc.version.mapping.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.wenjiesx.webmvc.version.mapping.annotation.EnableWebVersionControl;
import xyz.wenjiesx.webmvc.version.mapping.controller.integer.IntegerVersionTestController;

/**
 * @author wenji
 * @Date 2021/4/10
 */
@EnableWebVersionControl
@SpringBootApplication(scanBasePackageClasses = IntegerVersionTestController.class)
public class IntegerVersionTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegerVersionTestApplication.class, args);
    }

}
