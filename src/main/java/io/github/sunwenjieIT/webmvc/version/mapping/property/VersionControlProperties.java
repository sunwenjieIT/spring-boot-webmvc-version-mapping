package io.github.sunwenjieIT.webmvc.version.mapping.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import io.github.sunwenjieIT.webmvc.version.mapping.condition.AbstractVersionRequestCondition;
import io.github.sunwenjieIT.webmvc.version.mapping.condition.IntegerVersionRequestCondition;

/**
 * @author wenji
 * @Date 2021/4/10
 */
@ConfigurationProperties("webmvc.version.mapping")
public class VersionControlProperties {

    private String headerKey = "version";

    private Class<? extends AbstractVersionRequestCondition> clazz = IntegerVersionRequestCondition.class;

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public Class<? extends AbstractVersionRequestCondition> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends AbstractVersionRequestCondition> clazz) {
        this.clazz = clazz;
    }

}