import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {    
        assertEquals(2, 1+1);
    }   

    @Test
    public void checkLinks(){
        MarkdownParse test1 = new MarkdownParse();

        String markdownList = ("[link](https://something.com)");
        ArrayList<String> result = test1.getLinks(markdownList);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("https://something.com");
        
        assertEquals(expected, result);
    }

    @Test
    public void EmptyLinkTest() throws IOException {
        MarkdownParse test1 = new MarkdownParse();
        Path fileName = Path.of("test13-file.md");
        String content = Files.readString(fileName);

        ArrayList<String> result = test1.getLinks(content);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("https://something.com");
        expected.add("some-thing.asdf");
        assertEquals(expected, result);
    }
}
