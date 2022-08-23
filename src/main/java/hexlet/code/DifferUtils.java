package hexlet.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DifferUtils {

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
        diffs.sort(Comparator.comparing((Diff a) -> a.key().toLowerCase()));
        return diffs;
    }
}
