package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormatterTest {

    private static List<Diff> diffs;

    @BeforeAll
    public static void init() {
        diffs = List.of(
                new Diff("id", "123", null, Status.DELETED),
                new Diff("gender", null, "male", Status.ADDED),
                new Diff("name", "OLEG", "OLEG", Status.UNCHANGED),
                new Diff("mmr", "1000", "9000", Status.CHANGED)
                );
    }

    @Test
    public void formatStylishTest() throws JsonProcessingException {
        String result = Formatter.format(diffs, Format.stylish);
        assertEquals("""
                {
                  + gender: male
                  - id: 123
                  - mmr: 1000
                  + mmr: 9000
                    name: OLEG
                }""",
                result);
    }

    @Test
    public void formatPlainTest() throws JsonProcessingException {
        List<Diff> diffsClone = new ArrayList<>(diffs);
        diffsClone.add(new Diff("xxx", null, 0, Status.CHANGED));
        int[] ints = {0, 0};
        diffsClone.add(new Diff("yyy", null, ints, Status.CHANGED));
        String actual = Formatter.format(diffsClone, Format.plain);
        String expected = """
                Property 'gender' was added with value: 'male'
                Property 'id' was removed
                Property 'mmr' was updated. From '1000' to '9000'
                Property 'xxx' was updated. From null to 0
                Property 'yyy' was updated. From null to [complex value]""";
        assertEquals(expected, actual);
    }

    @Test
    public void formatJsonTest() throws JsonProcessingException {
        String result = Formatter.format(diffs, Format.json);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Diff> actual = objectMapper.readValue(result, new TypeReference<List<Diff>>() {
        });
        assertTrue(diffs.containsAll(actual));
    }

}
