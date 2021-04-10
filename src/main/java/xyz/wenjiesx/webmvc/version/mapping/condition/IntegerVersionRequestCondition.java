package xyz.wenjiesx.webmvc.version.mapping.condition;

/**
 * @author wenji
 * @Date 2021/4/10
 */
public final class IntegerVersionRequestCondition
        extends AbstractVersionRequestCondition<IntegerVersionRequestCondition> {

    public IntegerVersionRequestCondition(String minVersion, String maxVersion, Integer order, String headerKey) {
        super(minVersion, maxVersion, order, headerKey);
    }

    @Override
    protected boolean compareVersion(String currentVersion, String compareVersion) {
        int current = Integer.parseInt(currentVersion);
        int compare = Integer.parseInt(compareVersion);

        return current == compare || current > compare;
    }

    @Override
    protected void checkVersionFormat(String version) {
        assert version != null;
        if (!isVersionFormatValid(version)) {
            throw new IllegalArgumentException("PostMappingWithVersion param version: " + version + " invalid, it must be number.");
        }
    }

    @Override
    protected boolean isVersionFormatValid(String version) {
        return isNumeric(version);
    }

    @Override
    protected IntegerVersionRequestCondition buildVersionRequestCondition(String minVersion, String maxVersion) {
        return this;
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
