package xyz.wenjiesx.webmvc.version.mapping.controller.integer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wenjiesx.webmvc.version.mapping.annotation.PostMappingWithVersion;

/**
 * @author wenji
 * @Date 2021/4/10
 */
@RestController
@RequestMapping("/test")
public class IntegerVersionTestController {

    @PostMappingWithVersion(value = "/api1", minVersion = "1", maxVersion = "8", order = -1)
    public String method1() {

        return "method1";
    }

    @PostMappingWithVersion(value = "/api1", minVersion = "6", maxVersion = "14", order = 3)
    public String method2() {

        return "method2";
    }

    @PostMappingWithVersion(value = "/api1", minVersion = "11", maxVersion = "20", order = 10)
    public String method3() {

        return "method3";
    }
}
