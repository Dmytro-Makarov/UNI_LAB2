import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uni.makarov.lab2.*;

import java.util.ArrayList;

public class AnalyzerTest {
    final String pathToXML = "tests/tests_resources/test.xml";

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
        Resource resource = dom.search(pathToXML, getSearchAttributes()).get(0);
        Assertions.assertEquals("name2", resource.name);
    }

    @Test
    void saxTest(){
        Strategy sax = new SAXAnalyzer();
        Resource resource = sax.search(pathToXML, getSearchAttributes()).get(0);
        Assertions.assertEquals("name2", resource.name);
    }

    @Test
    void xpathTest(){
        Strategy xpath = new XPathAnalyzer();
        Resource resource = xpath.search(pathToXML, getSearchAttributes()).get(0);
        Assertions.assertEquals("name2", resource.name);
    }
}
