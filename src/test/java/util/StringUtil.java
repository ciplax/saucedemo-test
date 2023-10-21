package util;

public class StringUtil {
    public static boolean isEmptyString(String text) {
        if(null == text) {
            return true;
        }

        if("".equalsIgnoreCase(text)) {
            return true;
        }

        return false;
    }
}
