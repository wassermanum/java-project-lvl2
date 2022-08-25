package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DifferTest {


    private static final List<Diff> DIFF_LIST = List.of(
            new Diff("gender", null, "male", Status.ADDED),
            new Diff("id", 123, null, Status.DELETED),
            new Diff("mmr", 1000, 9000, Status.CHANGED),
            new Diff("name", "OLEG", "OLEG", Status.UNCHANGED),
            new Diff("xxx", null, 0, Status.CHANGED),
            new Diff("yyy", null, new int[]{1, 2, 4}, Status.CHANGED),
            new Diff("zzz", null, "Letters", Status.ADDED)
    );

    @BeforeAll
    public static void init() throws IOException {
        String expectedStylish = """
                {
                  + age: 16
                  - gender: male
                  + gender: female
                    id: 123
                  - name: loh
                }""";

        String expectedPlain = """
                Property 'gender' was added with value: 'male'
                Property 'id' was removed
                Property 'mmr' was updated. From 1000 to 9000
                Property 'xxx' was updated. From null to 0
                Property 'yyy' was updated. From null to [complex value]
                Property 'zzz' was added with value: 'Letters'""";

        Files.writeString(Path.of("src/test/resources/expectedStylish"), expectedStylish);
        Files.writeString(Path.of("src/test/resources/expectedPlain"), expectedPlain);

    }

    @AfterAll
    public static void clean() throws IOException {
        Files.deleteIfExists(Path.of("src/test/resources/expectedStylish1"));
        Files.deleteIfExists(Path.of("src/test/resources/expectedStylish2"));
        Files.deleteIfExists(Path.of("src/test/resources/expectedPlain"));
    }


    @Test
    public void generateTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String actualStylishDefault = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json");
        String actualStylish = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "stylish");
        String actualPlain = Differ.generate(
                "src/test/resources/file1.yml",
                "src/test/resources/file2.yml",
                "plain");
        String actualJson = Differ.generate(
                "src/test/resources/file1.yml",
                "src/test/resources/file2.yml",
                "json");


        String expectedStylish = Files.readString(Path.of("src/test/resources/expectedStylish"));
        String expectedPlain = Files.readString(Path.of("src/test/resources/expectedPlain"));
        String expectedJson = objectMapper.writeValueAsString(DIFF_LIST);


        assertEquals(actualStylishDefault, expectedStylish);
        assertEquals(actualStylish, expectedStylish);
        assertEquals(actualPlain, expectedPlain);
        assertEquals(expectedJson, actualJson);

        assertEquals(expectedPlain, Differ.generate(
                Path.of("src/test/resources/file1.yml").toFile(),
                Path.of("src/test/resources/file2.yml").toFile(),
                "plain"
                )
        );
    }

    @Test
    public void generateWrongTest() {
        assertThrows(IOException.class, () -> Differ.generate(
                "src/test/resources/expectedStylish1",
                "src/test/resources/expectedStylish2")
        );
    }

}

