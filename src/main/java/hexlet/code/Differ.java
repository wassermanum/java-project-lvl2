package hexlet.code;

import hexlet.code.readers.FileReader;
import hexlet.code.readers.Reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static hexlet.code.DifferUtils.calculateDiffs;

public class Differ {

    public static String generate(File filepath1, File filepath2, Format format) throws IOException {
        Reader<File> reader = new FileReader();
        List<Diff> diffs = calculateDiffs(
                Parser.parse(reader.read(filepath1)),
                Parser.parse(reader.read(filepath2))
        );
        return Formatter.format(diffs, format);
    }

    public static String generate(File filepath1, File filepath2, String format) throws IOException {
        Reader<File> reader = new FileReader();
        List<Diff> diffs = calculateDiffs(
                Parser.parse(reader.read(filepath1)),
                Parser.parse(reader.read(filepath2))
        );
        return Formatter.format(diffs, Format.valueOf(format));
    }

    public static String generate(String filepath1, String filepath2, Format format) throws IOException {
        return Differ.generate(new File(filepath1), new File(filepath2), format);
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        return Differ.generate(new File(filepath1), new File(filepath2), Format.valueOf(format));
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return Differ.generate(new File(filepath1), new File(filepath2));
    }

    public static String generate(File filepath1, File filepath2) throws IOException {
        return Differ.generate(filepath1, filepath2, Format.stylish);
    }

}
