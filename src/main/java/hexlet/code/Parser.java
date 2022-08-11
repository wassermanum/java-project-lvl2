package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(File filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(mapper.readTree(filepath), new TypeReference<>() {
        });
    }
}
