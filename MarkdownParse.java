

// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        
        int currentIndex = 0, nextOpenBracket = 0, nextCloseBracket = 0, openParen = 0, closeParen = 0;
        while (currentIndex < markdown.length()) {
            nextOpenBracket = markdown.indexOf("[", currentIndex);

            if (nextOpenBracket != -1) { // if there is an open bracket
                nextCloseBracket = markdown.indexOf("]", nextOpenBracket); // find its closed bracket
            } else { // else there is no open bracket
                nextCloseBracket = currentIndex + 1;
            }

            openParen = markdown.indexOf("(", nextCloseBracket);

            if (openParen != -1) { // if there is an open parenthesis
                closeParen = markdown.indexOf(")", openParen); // find its closed parenthesis
            } else { // else there is no open parenthesis
                openParen = nextCloseBracket + 1;// yeah i think this should be nextclosebracket
                closeParen = markdown.indexOf("\n", openParen);
            }
            toReturn.add(markdown.substring(openParen  , closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
