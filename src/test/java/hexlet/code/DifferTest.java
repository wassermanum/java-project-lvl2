package hexlet.code;

import hexlet.code.readers.FileReader;
import hexlet.code.readers.Reader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {


    private static final List<Diff> diffs = List.of(
            new Diff("gender", null, "male", Status.ADDED),
            new Diff("id", "123", null, Status.DELETED),
            new Diff("mmr", "1000", "9000", Status.CHANGED),
            new Diff("name", "OLEG", "OLEG", Status.UNCHANGED)
    );
    @BeforeAll
    public static void init() throws IOException {
        String expectedStylish1 = """
                {
                  + age: 16
                  - gender: male
                  + gender: female
                    id: 123
                  - name: loh
                }""";

        String expectedStylish2 = """
                {
                  + gender: male
                  - id: 123
                  - mmr: 1000
                  + mmr: 9000
                    name: OLEG
                }""";

        String expectedPlain = """
                Property 'gender' was added with value: 'male'
                Property 'id' was removed
                Property 'mmr' was updated. From '1000' to '9000'
                Property 'xxx' was updated. From null to 0
                Property 'yyy' was updated. From null to [complex value]""";

        Files.writeString(Path.of("src/test/resources/expectedStylish1"), expectedStylish1);
        Files.writeString(Path.of("src/test/resources/expectedStylish2"), expectedStylish2);
        Files.writeString(Path.of("src/test/resources/expectedPlain"), expectedPlain);

    }

    @AfterAll
    public static void clean() throws IOException {
        Files.deleteIfExists(Path.of("src/test/resources/expectedStylish1"));
        Files.deleteIfExists(Path.of("src/test/resources/expectedStylish2"));
        Files.deleteIfExists(Path.of("src/test/resources/expectedPlain"));
    }
    @Test
    public void calculateDiffsTest() throws IOException {
        List<Diff> diffs = List.of(
                new Diff("id", "123", "123", Status.UNCHANGED),
                new Diff("name", "loh", null, Status.DELETED),
                new Diff("gender", "male", "female", Status.CHANGED),
                new Diff("age", null, "16", Status.ADDED)
        );

        Reader<File> reader = new FileReader();

        List<Diff> result = DifferUtils.calculateDiffs(
                Parser.parse(reader.read(new File("src/test/resources/file1.json"))),
                Parser.parse(reader.read(new File("src/test/resources/file2.json")))
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

    @Test
    public void formattersTest() {

    }

}

