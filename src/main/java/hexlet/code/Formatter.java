package hexlet.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Formatter {

    public static String format(List<Diff> diffs, Format format) {
        return switch (format) {
            case plain -> null;
            case json -> null;
            default -> formatStylish(diffs);
        };
    }

    /*
    AAA:
    bbb:
    ccc:
    AAA:
     */

    public static String formatStylish(List<Diff> diffs) {
        List<Diff> diffsClone = new ArrayList<>(diffs);
        diffsClone.sort(Comparator.comparing(Diff::key));
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (Diff x : diffsClone) {
            result.append("  ");
            switch (x.status()) {
                case ADDED -> result.
                        append("+ ").
                        append(x.key()).
                        append(": ").
                        append(x.newValue()).
                        append("\n");
                case DELETED -> result.
                        append("- ").
                        append(x.key()).
                        append(": ").
                        append(x.oldValue()).
                        append("\n");
                case CHANGED -> result.
                        append("- ").
                        append(x.key()).
                        append(": ").
                        append(x.oldValue()).
                        append("\n").
                        append("  + ").
                        append(x.key()).
                        append(": ").
                        append(x.newValue()).
                        append("\n");
                case UNCHANGED -> result.
                        append("  ").
                        append(x.key()).
                        append(": ").
                        append(x.oldValue()).
                        append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
