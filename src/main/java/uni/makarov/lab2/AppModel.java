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

    ArrayList<Resource> tempSearchResults;

    XMLContext toolContext;

    private File xmlFile;
    private final File xsltFile = new File("src/main/resources/uni/makarov/lab2/DepartmentDB.xsl");

    AppModel() {
        toolContext = new XMLContext();
    }

    void setXMLFile(File xmlFile){
        this.xmlFile = xmlFile;
    }

    void convertToHTML() {
        XMLBuilder xmlBuilder = new XMLBuilder();

        Source xml = null;
        if(tempSearchResults != null) {
            xml = new StreamSource(new File(xmlBuilder.createNewXML(tempSearchResults)));
        } else if(xmlFile != null){
            xml = new StreamSource(xmlFile);
        }

        Source xslt = new StreamSource(xsltFile);

        if(xml != null)
        new XMLToHTMLConverter(xml, xslt);
    }

    ArrayList<ArrayList<String>> analyze() {
        XPathAnalyzer analyzer = new XPathAnalyzer();
        xmlAttributes = analyzer.getAttributes(xmlFile);
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
            if(xmlFile != null) {
                tempSearchResults = api.search(xmlFile, searchAttributes);
                return tempSearchResults;
            } else {
                return null;
            }
        }
    }
}
