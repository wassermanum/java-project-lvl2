package hexlet.code;

import java.util.Comparator;
import java.util.List;

public class Formatter {

    public static String format(List<Diff> diffs, Format format) {
        return switch (format) {
            case stylish -> formatStylish(diffs);
        };
    }

    public static String formatStylish(List<Diff> diffs) {
        diffs.sort(Comparator.comparing(Diff::getKey));
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (Diff x : diffs) {
            result.append("  ");
            switch (x.getStatus()) {
                case added -> result.
                        append("+ ").
                        append(x.getKey()).
                        append(": ").
                        append(x.getNewValue()).
                        append("\n");
                case deleted -> result.
                        append("- ").
                        append(x.getKey()).
                        append(": ").
                        append(x.getOldValue()).
                        append("\n");
                case changed -> result.
                        append("- ").
                        append(x.getKey()).
                        append(": ").
                        append(x.getOldValue()).
                        append("\n").
                        append("  + ").
                        append(x.getKey()).
                        append(": ").
                        append(x.getNewValue()).
                        append("\n");
                case unchanged -> result.
                        append("  ").
                        append(x.getKey()).
                        append(": ").
                        append(x.getOldValue()).
                        append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
