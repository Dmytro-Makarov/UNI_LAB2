import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uni.makarov.lab2.*;

import java.io.File;
import java.util.ArrayList;

public class AnalyzerTest {
    final File testFile = new File("tests/tests_resources/test.xml");

    Resource getSearchAttributes(){
        ArrayList<String> searchAttributes = new ArrayList<>();
        searchAttributes.add("");
        searchAttributes.add("");
        searchAttributes.add("");
        searchAttributes.add("author2");
        searchAttributes.add("");
        searchAttributes.add("");

        return new Resource(searchAttributes);
    }

    @Test
    void domTest(){
        Strategy dom = new DOMAnalyzer();
        Resource resource = dom.search(testFile, getSearchAttributes()).get(0);
        Assertions.assertEquals("name2", resource.name);
    }

    @Test
    void saxTest(){
        Strategy sax = new SAXAnalyzer();
        Resource resource = sax.search(testFile, getSearchAttributes()).get(0);
        Assertions.assertEquals("name2", resource.name);
    }

    @Test
    void xpathTest(){
        Strategy xpath = new XPathAnalyzer();
        Resource resource = xpath.search(testFile, getSearchAttributes()).get(0);
        Assertions.assertEquals("name2", resource.name);
    }
}
