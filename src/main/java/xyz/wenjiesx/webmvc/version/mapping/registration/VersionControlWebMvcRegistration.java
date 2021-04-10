package xyz.wenjiesx.webmvc.version.mapping.registration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import xyz.wenjiesx.webmvc.version.mapping.handler.VersionRequestMappingHandlerMapping;

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
