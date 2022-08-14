package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Formatter {

    public static String format(List<Diff> diffs, Format format) {
        return switch (format) {
            case plain -> PlainFormatter.format(diffs);
            case json -> null;
            default -> StylishFormatter.format(diffs);
        };
    }

}
