import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;

public class MarkdownParseTest extends MarkdownParse{
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
}
