package uni.makarov.lab2;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.ArrayList;

enum apiType{
    DOM,
    SAX,
    XPATH
}

public class AppModel {

    ArrayList<ArrayList<String>> xmlAttributes;

    XMLContext toolContext;

    private final String pathToXML = "src/main/resources/uni/makarov/lab2/DepartmentDB.xml";
    private final String pathToXSLT = "src/main/resources/uni/makarov/lab2/DepartmentDB.xsl";

    AppModel() {
        toolContext = new XMLContext();
    }

    void convertToHTML() {
        Source xml = new StreamSource(new File(pathToXML));
        Source xslt = new StreamSource(pathToXSLT);

        new XMLToHTMLConverter(xml, xslt);
    }

    ArrayList<ArrayList<String>> analyze() {
        XPathAnalyzer analyzer = new XPathAnalyzer();
        xmlAttributes = analyzer.getAttributes(pathToXML);
        return xmlAttributes;
    }

    class XMLContext {
        private Strategy api;

        public XMLContext(Strategy api) {
            this.api = api;
        }

        public XMLContext() { }

        public void setAnalyzer(Strategy api){
            this.api = api;
        }

        public ArrayList<Resource> search(Resource searchAttributes) {
            return api.search(pathToXML, searchAttributes);
        }
    }
}
