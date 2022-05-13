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

            if (isEscapeCharacter(markdown, openBracket)){
                openBracket = markdown.indexOf("[", openBracket);
            }
            
            int closeBracket = markdown.indexOf("]", openBracket);

            System.out.println(closeBracket);
            if (isEscapeCharacter(markdown, closeBracket)){
                closeBracket = markdown.indexOf("]", closeBracket+1);
                System.out.println(closeBracket);
            }

            int openParen = markdown.indexOf("(", closeBracket+1);
            System.out.println(openParen);
            if (isEscapeCharacter(markdown, openParen)){
                openParen = markdown.indexOf("(", openParen+1);
                System.out.println(openParen);
            }

            int closeParen = markdown.indexOf(")", openParen);

            if (isEscapeCharacter(markdown, closeParen)){
                closeParen = markdown.indexOf(")", closeParen+1);
            }

            if(Math.abs(openParen-closeParen) == 1) {
                break;
            }

            if (openParen + 1 <= closeParen){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }

            //no title fix
            if (openBracket == -1 || closeBracket == -1) {
                openParen = markdown.indexOf("(", currentIndex);
                closeParen = markdown.indexOf(")", currentIndex);
            }

            //Checks for no link
            if (openParen == -1 || closeParen == -1) {
                break;
            }   
            currentIndex = closeParen + 1;

            //Stops loop if extra line in file
            if (markdown.length()-2 <= currentIndex) {
                break;
            }
            

        }
        return toReturn;
    }

    private static boolean isEscapeCharacter(String md, int index){
        if (index - 1 > -1 && md.charAt(index-1) == '\\'){
            System.out.println("true");
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
        System.out.println("Terminal test");
        System.out.println("Changed!");
    }
}
