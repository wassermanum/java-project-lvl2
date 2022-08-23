package hexlet.code.readers;

import java.io.IOException;

public interface Reader {

    public String read(Object obj) throws IOException;
}
