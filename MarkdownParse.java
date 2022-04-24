//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        System.out.println("Markdown length " + markdown.length());
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )

        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);

            //Checks for no link (stops infinite loop)
            if (openParen == -1 || closeParen == -1) {
                break;
            }

            //no title fix
            if (openBracket == -1 || closeBracket == -1) {
                openParen = markdown.indexOf("(", currentIndex);
                closeParen = markdown.indexOf(")", currentIndex);
            }

            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;

            //Stops loop if extra line in file
            if (markdown.length()-2 <= currentIndex) {
                break;
            }

            //Need to fix crashing after additional invalid inputs

        }
        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
        System.out.println("test");
    }
}
