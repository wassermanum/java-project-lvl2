package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void formatStylishTest() {
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
    public void formatPlainTest() {
        String result = Formatter.format(diffs, Format.plain);
        assertNull(result);
    }

    @Test
    public void formatJsonTest() {
        String result = Formatter.format(diffs, Format.json);
        assertNull(result);
    }

}