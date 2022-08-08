package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Differ {

    public static String generate(File filepath1, File filepath2, Format format) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.convertValue(mapper.readTree(filepath1), new TypeReference<>() {
        });
        Map<String, Object> map2 = mapper.convertValue(mapper.readTree(filepath2), new TypeReference<>() {
        });
        List<Diff> diffs = calculate(map1, map2);
        System.out.println(map1);
        System.out.println(Formatter.format(diffs, format));
        return Formatter.format(diffs, format);
    }

    public static List<Diff> calculate (Map<String, Object> map1, Map<String, Object> map2) {
        List<Diff> diffs = new ArrayList<>();
        map2.forEach((key, value) -> {
            if (!map1.containsKey(key)) {
                diffs.add(new Diff(key, null, value, Status.added));
            } else {
                if (Objects.equals(map1.get(key), value)) {
                    diffs.add(new Diff(key, value, value, Status.unchanged));
                } else {
                    diffs.add(new Diff(key, map1.get(key), value, Status.changed));
                }
            }
        });
        map1.forEach((key, value) -> {
            if (!map2.containsKey(key)) {
                diffs.add(new Diff(key, value, null, Status.deleted));
            }
        });
        return diffs;
    }
}
