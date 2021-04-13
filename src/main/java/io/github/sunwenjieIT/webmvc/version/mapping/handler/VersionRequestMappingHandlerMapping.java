package io.github.sunwenjieIT.webmvc.version.mapping.handler;

import io.github.sunwenjieIT.webmvc.version.mapping.annotation.ClientVersion;
import io.github.sunwenjieIT.webmvc.version.mapping.annotation.PostMappingWithVersion;
import io.github.sunwenjieIT.webmvc.version.mapping.property.VersionControlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import io.github.sunwenjieIT.webmvc.version.mapping.condition.AbstractVersionRequestCondition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author wenji
 * @Date 2021/4/10
 */
public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Autowired
    private VersionControlProperties versionControlProperties;

    //注入一个 VersionMethodConditionAdapter list组

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {

        PostMappingWithVersion postMappingWithVersion = AnnotatedElementUtils.findMergedAnnotation(method, PostMappingWithVersion.class);
        ClientVersion          clientVersion          = AnnotatedElementUtils.findMergedAnnotation(method, ClientVersion.class);
        if (postMappingWithVersion == null && clientVersion == null)
            return null;

        String minVersion = postMappingWithVersion == null ? clientVersion.minVersion() : postMappingWithVersion.minVersion();
        String maxVersion = postMappingWithVersion == null ? clientVersion.maxVersion() : postMappingWithVersion.maxVersion();
        int    order      = postMappingWithVersion == null ? clientVersion.order() : postMappingWithVersion.order();

        String headerKey = versionControlProperties.getHeaderKey();

        Class<? extends AbstractVersionRequestCondition> clazz = versionControlProperties.getClazz();

        try {
            Constructor<? extends AbstractVersionRequestCondition> constructor = clazz.getConstructor(String.class, String.class, Integer.class, String.class);
            return constructor.newInstance(minVersion, maxVersion, order, headerKey);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("version request condition " + clazz.getName() +
                    " must has constructor with params(minVersion, maxVersion, order, headerKey)", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("version request condition " + clazz.getName() + " newInstance by constructor(" + minVersion + ", " +
                    maxVersion + "," + order + "," + headerKey + ") error.", e);
        }
    }
}