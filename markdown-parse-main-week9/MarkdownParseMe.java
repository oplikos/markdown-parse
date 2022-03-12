// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseMe {
    public static ArrayList<String> getLinks(String markdown) {
        boolean debug = false;
        int nextOpenBracket=0;
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        ArrayList<Integer> periodList = new ArrayList<Integer>();
        //creating a array list with all the expected char
        ArrayList<Character> stopCharacters = new ArrayList<Character>();
            char[] strs = { '(',')','[',']','\n'};
            for(char st:strs)
                stopCharacters.add(st); 
        //initilizing all variabls
        int currentIndex = 0,startIndex = 0 , endIndex = 0,nextPeriodIndex =0 ;
        //check for '.' in the file (if exsist that means there is a link)
        nextOpenBracket = markdown.indexOf("[", currentIndex);   
        if (nextOpenBracket ==-1){
            return toReturn;
        }
        while(currentIndex < markdown.length()) {
            nextPeriodIndex = markdown.indexOf(".", currentIndex + 1);
            if(nextPeriodIndex != -1) {
                periodList.add(nextPeriodIndex);
                currentIndex = nextPeriodIndex;
            } else {
                break;
            }
        }
        // for each . copy the wording before it and after until a space or new line it and save it as the link 
        for(int periodIndex: periodList) {
            if(debug)
                System.out.println(periodIndex);
            startIndex = periodIndex;
            endIndex = periodIndex;
            while(startIndex >= 0) {
                if(stopCharacters.contains(markdown.charAt(startIndex))) {
                    startIndex++;
                    break;
                }
                startIndex--;
            }
            while(endIndex < markdown.length()) {
                if(endIndex+1 == ')') 
                    break;
                if(stopCharacters.contains(markdown.charAt(endIndex))) {
                    endIndex--;
                    break;
                }
                endIndex++;
            }
            toReturn.add(markdown.substring(startIndex, endIndex + 1));
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
        boolean debug = false;
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        if(debug) 
            System.out.println(contents);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}