package hexlet.code.readers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class FileReader implements Reader<File> {

    @Override
    public String read(File file) throws IOException {
        return Files.readString(file.toPath());
    }
}
