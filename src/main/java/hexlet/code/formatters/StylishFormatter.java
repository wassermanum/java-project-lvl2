package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.List;

public class StylishFormatter {
    public static String format(List<Diff> diffs) {

        StringBuilder result = new StringBuilder("{\n");

        for (Diff x : diffs) {
            result.append("  ");
            switch (x.status()) {
                case ADDED -> result.append("+ %s: %s\n".formatted(x.key(), x.newValue()));

                case DELETED -> result.append("- %s: %s\n".formatted(x.key(), x.oldValue()));

                case CHANGED -> result.append("- %s: %s\n  + %s: %s\n"
                        .formatted(x.key(), x.oldValue(), x.key(), x.newValue()));

                case UNCHANGED -> result.append("  %s: %s\n".formatted(x.key(), x.oldValue()));

                default -> {
                }
            }
        }

        result.append("}");
        return result.toString();
    }
}
