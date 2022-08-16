package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonStathamFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {

    public static String format(List<Diff> diffs, Format format) throws JsonProcessingException {
        return switch (format) {
            case plain -> PlainFormatter.format(diffs);
            case json -> JsonStathamFormatter.format(diffs);
            default -> StylishFormatter.format(diffs);
        };
    }

}
