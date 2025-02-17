import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class MarkdownParseTest {
    // @Test
    // public void testDefaultLinkMethod() throws IOException {
    // Path fileName = Path.of("test-file.md");
    // String contents = Files.readString(fileName);
    // assertEquals(List.of("https://something.com", "some-page.html"),
    // MarkdownParse.getLinks(contents));

    // }

    // @Test
    // public void testOneLinkMethod() throws IOException {
    // Path fileName = Path.of("markdown-new-test.md");
    // String contents = Files.readString(fileName);
    // assertEquals(List.of("thiswillbreak.com", "thisisover.com", "peasy.html"),
    // MarkdownParse.getLinks(contents));
    // }

    // @Test
    // public void testTwoLinkMethod() throws IOException {
    // Path fileName = Path.of("markdown-test-two.md");
    // String contents = Files.readString(fileName);
    // assertEquals(List.of("thiswillbreak.com", "thisisover.com", "peasy.html"),
    // MarkdownParse.getLinks(contents));
    // }

    // @Test
    // public void testThreeLinkMethod() throws IOException {
    // Path fileName = Path.of("markdown-test-three.md");
    // String contents = Files.readString(fileName);
    // assertEquals(List.of("thiswillbreak.com", "thisisover.com", "peasy.html"),
    // MarkdownParse.getLinks(contents));
    // }

    // // Tests from Joe's new repos
    // @Test
    // public void joesNewTestTwo() throws IOException {
    // assertEquals(List.of("https://something.com", "some-page.html"),
    // MarkdownParse.getLinks(getContents("test-file2.md")));
    // }

    // @Test
    // public void joesNewTestThree() throws IOException {
    // assertEquals(List.of(),
    // MarkdownParse.getLinks(getContents("test-file3.md")));
    // }

    // @Test
    // public void joesNewTestFour() throws IOException {
    // assertEquals(new ArrayList<String>(),
    // MarkdownParse.getLinks(getContents("test-file4.md")));
    // }

    // @Test
    // public void joesNewTestFive() throws IOException {
    // assertEquals(List.of("page.com"),
    // MarkdownParse.getLinks(getContents("test-file5.md")));
    // }

    // @Test
    // public void joesNewTestSix() throws IOException {
    // assertEquals(List.of("page.com"),
    // MarkdownParse.getLinks(getContents("test-file6.md")));
    // }

    // @Test
    // public void joesNewTestSeven() throws IOException {
    // assertEquals(new ArrayList<String>(),
    // MarkdownParse.getLinks(getContents("test-file7.md")));
    // }

    // @Test
    // public void joesNewTestEight() throws IOException {
    // assertEquals(new ArrayList<String>(),
    // MarkdownParse.getLinks(getContents("test-file8.md")));
    // }

    @Test
    public void snippit1me() throws IOException {
        assertEquals(List.of("'google.com", "google.com", "ucsd.edu"),
                MarkdownParse.getLinks(getContents("Snippet1.md")));
    }

    @Test
    public void snippit2me() throws IOException {
        assertEquals(List.of("a.com", "a.com(())", "example.com"),
                MarkdownParse.getLinks(getContents("Snippet2.md")));
    }

    @Test
    public void snippit3me() throws IOException {
        assertEquals(List.of("https://ucsd-cse15l-w22.github.io/"),
                MarkdownParse.getLinks(getContents("Snippet3.md")));
    }

    @Test
    public void snippit1Other() throws IOException {
        assertEquals(List.of("google.com", "'google.com", "ucsd.edu"),
                MarkdownParseOtherGroup.getLinks(getContents("Snippet1.md")));
    }

    @Test
    public void snippit2Other() throws IOException {
        assertEquals(List.of("a.com", "a.com(())", "example.com"),
                MarkdownParseOtherGroup.getLinks(getContents("Snippet2.md")));
    }

    @Test
    public void snippit3Other() throws IOException {
        assertEquals(List.of("https://ucsd-cse15l-w22.github.io/"),
                MarkdownParseOtherGroup.getLinks(getContents("Snippet3.md")));
    }

    public static String getContents(String filePath) throws IOException {
        Path fileName = Path.of(filePath);
        String contents = Files.readString(fileName);
        return contents;
    }
}
