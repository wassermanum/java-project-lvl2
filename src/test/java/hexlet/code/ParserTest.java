package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    public void parseTest() throws IOException {
        String data = Files.readString(Path.of("src/test/resources/file1.json"));
        Map<String, Object> parsedMap = Parser.parse(data);
        assertEquals(parsedMap.get("id"), "123");
        assertEquals(parsedMap.get("name"), "loh");
        assertEquals(parsedMap.get("gender"), "male");
    }

    @Test
    public void parseTest2() {
        assertThrows(IOException.class, () -> Parser.parse("{file: 123"));
    }

    @Test
    public void parseTest3() throws IOException {
        Map<String, Object> parsedMap = Parser.parse("{women: 123}");
        assertTrue(parsedMap.containsKey("women"));
    }
}
