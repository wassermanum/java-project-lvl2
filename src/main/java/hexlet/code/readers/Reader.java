package hexlet.code.readers;

import java.io.IOException;

public interface Reader<T> {

    String read(T t) throws IOException;
}
