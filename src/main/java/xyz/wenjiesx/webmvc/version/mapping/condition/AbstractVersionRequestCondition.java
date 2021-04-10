package xyz.wenjiesx.webmvc.version.mapping.condition;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wenji
 * @Date 2021/4/10
 */
public abstract class AbstractVersionRequestCondition<T extends AbstractVersionRequestCondition<T>>
        implements RequestCondition<T> {

    protected String minVersion;
    protected String maxVersion;
    protected String headerKey;
    protected int    order;

    public AbstractVersionRequestCondition(String minVersion, String maxVersion, Integer order, String headerKey) {
        checkVersionFormat(minVersion);
        checkVersionFormat(maxVersion);
        this.minVersion = minVersion;
        this.maxVersion = maxVersion;
        this.order = order;
        this.headerKey = headerKey;
    }

    /**
     * 对两个版本号进行比较, 前者大返回true, 后者大返回false
     *
     * @param currentVersion
     * @param compareVersion
     * @return
     */
    protected abstract boolean compareVersion(String currentVersion, String compareVersion);

    /**
     * 校验版本号格式
     *
     * @param version
     * @return
     */
    protected abstract boolean isVersionFormatValid(String version);

    /**
     * 初始化过程中的前置版本号配置值校验, 若异常请直接抛出异常
     *
     * @param version
     */
    protected abstract void checkVersionFormat(String version);

    /**
     * 获取到真实的VersionRequestCondition实例
     *
     * @param minVersion
     * @param maxVersion
     * @return
     */
    protected abstract T buildVersionRequestCondition(String minVersion, String maxVersion);



    /**
     * 当前工具不在type层注释, 不存在需要combine的场景, 空实现
     *
     * @param other
     * @return
     */
    @Override
    public T combine(T other) {
        return null;
    }

    /**
     * 判断当前请求是否匹配versionCondition规则
     *
     * @param request
     * @return
     */
    @Override
    public T getMatchingCondition(HttpServletRequest request) {
        String requestVersion = request.getHeader(headerKey);
        if (requestVersion == null || !isVersionFormatValid(requestVersion)) {
            return null;
        }

        if (!matchVersion(requestVersion, minVersion, maxVersion)) {
            return null;
        }

        return buildVersionRequestCondition(minVersion, maxVersion);
    }

    /**
     * 存在复数匹配时的优先级比对
     * order值越小越优先, 若order值相等, this优先于other
     *
     * @param other
     * @param request
     * @return
     */
    @Override
    public int compareTo(T other, HttpServletRequest request) {
        return this.order <= other.order ? -1 : 1;
    }

    protected boolean matchVersion(String requestVersion, String minVersion, String maxVersion) {
        return compareVersion(requestVersion, minVersion) &&
                compareVersion(maxVersion, requestVersion);
    }

}
