package uni.makarov.lab2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMAnalyzer implements Strategy{
    @Override
    public ArrayList<Resource> search(File file, Resource searchAttributes) {

        ArrayList<Resource> searchResult = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("resource");

            for(int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Resource currentResource = new Resource();

                    Element element = (Element) node;

                    currentResource.name = element.getAttribute("NAME");
                    currentResource.annotation = element.getAttribute("ANNOTATION");
                    currentResource.type = element.getAttribute("TYPE");
                    currentResource.author = element.getAttribute("AUTHOR");
                    currentResource.termsOfUse = element.getAttribute("TERMS_OF_USE");
                    currentResource.address = element.getAttribute("ADDRESS");

                    searchResult.add(currentResource);
                }
            }

            filterResults(searchResult, searchAttributes.getAttributes());

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return searchResult;
    }

}
