package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;



public class DifferTest {

    @Test
    public void calculateDiffsTest() throws IOException {
        List<Diff> diffs = List.of(
                new Diff("id", "123", "123", Status.UNCHANGED),
                new Diff("name", "loh", null, Status.DELETED),
                new Diff("gender", "male", "female", Status.CHANGED),
                new Diff("age", null, "16", Status.ADDED)
        );

        List<Diff> result = Differ.calculateDiffs(
                Parser.parse(new File("src/test/resources/file1.json")),
                Parser.parse(new File("src/test/resources/file2.json"))
        );

        assertThat(result).containsAll(diffs);
    }

    @Test
    public void generateTest() throws IOException {
        String expected = """
                {
                  + age: 16
                  - gender: male
                  + gender: female
                    id: 123
                  - name: loh
                }""";

        String result = Differ.generate(
                new File("src/test/resources/file1.json"),
                new File("src/test/resources/file2.json"),
                Format.stylish
        );

        assertEquals(expected, result);
    }
}
