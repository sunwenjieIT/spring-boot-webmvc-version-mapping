package io.github.sunwenjieIT.webmvc.version.mapping.application;

import io.github.sunwenjieIT.webmvc.version.mapping.annotation.EnableWebVersionControl;
import io.github.sunwenjieIT.webmvc.version.mapping.controller.string.StringVersionTestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
