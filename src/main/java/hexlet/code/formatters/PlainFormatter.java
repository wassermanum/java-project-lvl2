package hexlet.code.formatters;

import hexlet.code.Diff;

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
                        (FIRST_LINE_PART + "added with value: " +
                                (objectCheck(x.newValue()) ? "'%s'\n" : "%s\n")).
                                formatted(x.key(), x.newValue())
                );
                case DELETED -> result.append(
                        (FIRST_LINE_PART + "removed\n").formatted(x.key())
                );
                case CHANGED -> result.append(
                        (FIRST_LINE_PART + "updated. From " + (
                                objectCheck(x.oldValue()) ? "'%s' to " : "%s to ") +
                                (objectCheck(x.newValue()) ? "'%s'\n" : "%s\n")
                        ).
                        formatted(x.key(), x.oldValue(), x.newValue()
                        )
                );
            }
        }
        return result.toString();
    }

    public static boolean objectCheck(Object obj) {
        if (Objects.isNull(obj)) return false;
        return obj.getClass().equals(String.class);
    }
}
