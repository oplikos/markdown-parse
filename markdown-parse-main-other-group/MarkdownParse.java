// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket == -1) {
                break;
            }
            if(nextOpenBracket > 0 && markdown.substring(nextOpenBracket - 1,nextOpenBracket).equals("!")) {
                currentIndex++;
                continue;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket == -1) {
                break;
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(openParen == -1) {
                break;
            }
            int tempBracket = nextCloseBracket;
            boolean exited = false;
            while(openParen != nextCloseBracket + 1) {
                nextCloseBracket = markdown.indexOf("]", tempBracket);
                openParen = markdown.indexOf("(", nextCloseBracket);
                if(nextCloseBracket == -1 || openParen == -1) {
                    exited = true;
                    break;
                }
                tempBracket = nextCloseBracket + 1;
            }
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen == -1) {
                break;
            }
            String thePotentialLink = markdown.substring(openParen + 1, closeParen);
            if(!exited && !thePotentialLink.contains("\n")) {
                toReturn.add(thePotentialLink);
            }
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