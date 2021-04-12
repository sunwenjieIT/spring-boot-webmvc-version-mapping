package io.github.sunwenjieIT.webmvc.version.mapping.annotation;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import io.github.sunwenjieIT.webmvc.version.mapping.property.VersionControlProperties;
import io.github.sunwenjieIT.webmvc.version.mapping.registration.VersionControlWebMvcRegistration;

import java.lang.annotation.*;

/**
 * @author wenji
 * @Date 2021/4/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({VersionControlWebMvcRegistration.class})
@EnableConfigurationProperties(VersionControlProperties.class)
public @interface EnableWebVersionControl {
}