package hexlet.code.readers;

import java.io.IOException;

public interface Reader {

    String read(Object obj) throws IOException;
}
