package io.github.sunwenjieIT.webmvc.version.mapping.condition;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author wenji
 * @Date 2021/4/10
 */
public final class StringVersionRequestCondition
        extends AbstractVersionRequestCondition<StringVersionRequestCondition> {

    public static Pattern VERSION_NAME_PATTERN = Pattern.compile("^(\\d+)(\\.\\d+)*$");

    public StringVersionRequestCondition(String minVersion, String maxVersion, Integer order, String headerKey) {
        super(minVersion, maxVersion, order, headerKey);
    }

    @Override
    protected boolean compareVersion(String currentVersion, String compareVersion) {
        if (currentVersion.equals(compareVersion))
            return true;

        int[] currentSubVersionArr = getSubVersionArr(currentVersion);
        int[] compareVersionArr    = getSubVersionArr(compareVersion);

        return compareVersion(currentSubVersionArr, compareVersionArr);
    }

    @Override
    protected void checkVersionFormat(String version) {
        assert version != null;
        if (!isVersionFormatValid(version)) {
            throw new IllegalArgumentException("PostMappingWithVersion param version: " + version + " invalid, it must be version str, eg: 2.1.6");
        }
    }

    @Override
    protected boolean isVersionFormatValid(String version) {
        return VERSION_NAME_PATTERN.matcher(version).matches();
    }

    @Override
    protected StringVersionRequestCondition buildVersionRequestCondition(String minVersion, String maxVersion) {
        return this;
    }

    public static int[] getSubVersionArr(String version) {
        return Arrays.stream(version.split("\\."))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static boolean compareVersion(int[] currentVersionArr, int[] compareVersionArr) {

        for (int i = 0; i < Math.min(currentVersionArr.length, compareVersionArr.length); i++) {
            int tmpCurrent = currentVersionArr[i];
            int tmpCompare = compareVersionArr[i];
            if (tmpCompare == tmpCurrent)
                continue;

            return tmpCurrent > tmpCompare;
        }

        return currentVersionArr.length >= compareVersionArr.length;
    }

}