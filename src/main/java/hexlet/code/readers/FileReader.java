package hexlet.code.readers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class FileReader implements Reader {

    @Override
    public String read(Object obj) throws IOException {
        if (obj instanceof File) {
            return Files.readString(((File) obj).toPath());
        }
        return null;
    }
}
