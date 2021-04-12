package io.github.sunwenjieIT.webmvc.version.mapping.registration;

import io.github.sunwenjieIT.webmvc.version.mapping.handler.VersionRequestMappingHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author wenji
 * @Date 2021/4/10
 */
public class VersionControlWebMvcRegistration implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new VersionRequestMappingHandlerMapping();
    }

}
