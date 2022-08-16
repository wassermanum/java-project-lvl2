package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Differ {

    public static String generate(File filepath1, File filepath2, Format format) throws IOException {
        List<Diff> diffs = calculateDiffs(Parser.parse(filepath1), Parser.parse(filepath2));
//        System.out.println(Formatter.format(diffs, format));
        return Formatter.format(diffs, format);
    }

    public static String generate(String filepath1, String filepath2, Format format) throws IOException {
        return Differ.generate(new File(filepath1), new File(filepath2), format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return Differ.generate(new File(filepath1), new File(filepath2));
    }

    public static String generate(File filepath1, File filepath2) throws IOException {
        return Differ.generate(filepath1, filepath2, Format.stylish);
    }

    public static List<Diff> calculateDiffs(Map<String, Object> map1, Map<String, Object> map2) {
        List<Diff> diffs = new ArrayList<>();
        map2.forEach((key, value) -> {
            if (!map1.containsKey(key)) {
                diffs.add(new Diff(key, null, value, Status.ADDED));
            } else {
                if (Objects.equals(map1.get(key), value)) {
                    diffs.add(new Diff(key, value, value, Status.UNCHANGED));
                } else {
                    diffs.add(new Diff(key, map1.get(key), value, Status.CHANGED));
                }
            }
        });
        map1.forEach((key, value) -> {
            if (!map2.containsKey(key)) {
                diffs.add(new Diff(key, value, null, Status.DELETED));
            }
        });
        return diffs;
    }
}
