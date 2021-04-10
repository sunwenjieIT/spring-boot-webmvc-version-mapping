package xyz.wenjiesx.webmvc.version.mapping.controller.string;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wenjiesx.webmvc.version.mapping.annotation.PostMappingWithVersion;

/**
 * @author wenji
 * @Date 2021/4/10
 */
@RestController
@RequestMapping("/test")
public class StringVersionTestController {

    @PostMappingWithVersion(value = "/api1", minVersion = "1.0.0", maxVersion = "1.5.9", order = -1)
    public String method1() {

        return "method1";
    }

    @PostMappingWithVersion(value = "/api1", minVersion = "1.4.5", maxVersion = "2.1.1", order = 3)
    public String method2() {

        return "method2";
    }

    @PostMappingWithVersion(value = "/api1", minVersion = "2.0", maxVersion = "3.1.2.1", order = 10)
    public String method3() {

        return "method3";
    }


}
