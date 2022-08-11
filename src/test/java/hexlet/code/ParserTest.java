package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    public void parseTest() throws IOException {
        File file = new File("src/test/resources/file1.json");
        Map<String, Object> parsedMap = Parser.parse(file);
        assertEquals(parsedMap.get("id"), "123");
        assertEquals(parsedMap.get("name"), "loh");
        assertEquals(parsedMap.get("gender"), "male");
    }

    @Test
    public void parseTest2() {
        File file = new File("111");
        assertThrows(IOException.class, () -> Parser.parse(file));
    }
}