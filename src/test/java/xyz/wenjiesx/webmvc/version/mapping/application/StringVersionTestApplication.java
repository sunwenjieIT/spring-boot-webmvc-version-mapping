package xyz.wenjiesx.webmvc.version.mapping.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.wenjiesx.webmvc.version.mapping.annotation.EnableWebVersionControl;
import xyz.wenjiesx.webmvc.version.mapping.controller.string.StringVersionTestController;

/**
 * @author wenji
 * @Date 2021/4/10
 */
@EnableWebVersionControl
@SpringBootApplication(scanBasePackageClasses = StringVersionTestController.class)
public class StringVersionTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(StringVersionTestApplication.class, args);
    }

}
