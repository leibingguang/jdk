package stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 构建流
 */
public class CreateStream {
    /**
     * 构建文件流
     */
    @Test
    public void fileStream() {
        long uniqueWords = 0;
        try (Stream<String> lines =
                     Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.map(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
        }
        System.out.println(uniqueWords);
    }
}
