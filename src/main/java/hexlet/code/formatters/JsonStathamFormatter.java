package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.Diff;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JsonStathamFormatter {
    public static String format(List<Diff> diffs) throws JsonProcessingException {

        List<Diff> diffsClone = new ArrayList<>(diffs);
        diffsClone.sort(Comparator.comparing((Diff a) -> a.key().toLowerCase()));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(diffsClone);
    }
}
