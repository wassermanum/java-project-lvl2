package hexlet.code.formatters;

import hexlet.code.Diff;
import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PlainFormatter {

    private static final String FIRST_LINE_PART = "Property '%s' was ";

    public static String format(List<Diff> diffs) {
        StringBuilder result = new StringBuilder();
        List<Diff> diffsClone = new ArrayList<>(diffs);
        diffsClone.sort(Comparator.comparing((Diff a) -> a.key().toLowerCase()));
        for (Diff x : diffsClone) {
            switch (x.status()) {
                case ADDED -> result.append(
                        (FIRST_LINE_PART + "added with value: "
//                                + (isString(x.newValue()) ? "'%s'\n" : "%s\n")).
                                + (valueCheck(x.newValue()) + "\n")).
                                formatted(x.key())
                );
                case DELETED -> result.append(
                        (FIRST_LINE_PART + "removed\n").formatted(x.key())
                );
                case CHANGED -> result.append(
                        (FIRST_LINE_PART + "updated. From "
//                                + (isString(x.oldValue()) ? "'%s' to " : "%s to ")
//                                + (isString(x.newValue()) ? "'%s'\n" : "%s\n")
                                + (valueCheck(x.oldValue()) + " to ")
                                + (valueCheck(x.newValue()) + "\n")
                        ).
                        formatted(x.key())
                );
                default -> {
                }
            }
        }
        return result.toString().trim();
    }

    public static boolean isString(Object obj) {
        if (Objects.isNull(obj)) {
            return false;
        }
        return obj.getClass().equals(String.class);
    }

    public static boolean isComplex(Object obj) {
        if (!Objects.isNull(obj)) {
            return !ClassUtils.isPrimitiveOrWrapper(obj.getClass());
        }
        return false;
    }

    public static String valueCheck(Object obj) {
        if (isString(obj)) {
//            System.out.println(obj + " is string, its class is " + (obj != null ? obj.getClass() : "null"));
            return "'%s'".formatted(obj);
        } else if (isComplex(obj)) {
//            System.out.println(obj + " is complex value, its class is " + (obj != null ? obj.getClass() : "null"));
            return "[complex value]";
        }
//        System.out.println(obj + " is primitive or wrapper, its class is " + (obj != null ? obj.getClass() : "null"));
        return "%s".formatted(obj);
    }
}
