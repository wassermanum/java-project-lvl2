package hexlet.code;

import java.util.*;

public class DifferUtils {

    public static List<Diff> calculateDiffs(Map<String, Object> map1, Map<String, Object> map2) {

        List<Diff> diffs = new ArrayList<>();
        Set<String> keySet = new HashSet<>(map1.keySet());
        keySet.addAll(map2.keySet());

        keySet.forEach(key -> {
            if (!map1.containsKey(key)) {
                diffs.add(new Diff(key, null, map2.get(key), Status.ADDED));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                    diffs.add(new Diff(key, map2.get(key), map2.get(key), Status.UNCHANGED));
            } else if (!map2.containsKey(key)) {
                diffs.add(new Diff(key, map1.get(key), null, Status.DELETED));
            } else {
                diffs.add(new Diff(key, map1.get(key), map2.get(key), Status.CHANGED));
            }
        });

        diffs.sort(Comparator.comparing((Diff a) -> a.key().toLowerCase()));
        return diffs;
    }
}
