package uni.makarov.lab2;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class XPathAnalyzer implements Strategy {
    @Override
    public ArrayList<Resource> search(String filePath, Resource searchAttributes) {
        ArrayList<Resource> searchResult = new ArrayList<>();

        ArrayList<String> searchArr = searchAttributes.getAttributes();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(new File(filePath));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/DepartmentDataBase/resource/@*";

            NodeList nl = (NodeList) xPath.evaluate(expression, xmlDocument, XPathConstants.NODESET);
            int length = nl.getLength();
            for (int i = 0; i < length; i += 6){

                Resource currentResource = new Resource();

                for (int j = i;j < i+6; j++){
                Attr attr = (Attr) nl.item(j);
                String name = attr.getName();
                String value = attr.getValue();

                switch (name) {
                    case "NAME" -> currentResource.name = value;
                    case "ANNOTATION" -> currentResource.annotation = value;
                    case "TYPE" -> currentResource.type = value;
                    case "AUTHOR" -> currentResource.author = value;
                    case "TERMS_OF_USE" -> currentResource.termsOfUse = value;
                    case "ADDRESS" -> currentResource.address = value;
                }
                }
                searchResult.add(currentResource);
            }
        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

        filterResults(searchResult, searchArr);

        return searchResult;
    }

    public ArrayList<ArrayList<String>> getAttributes(String filePath) {
        ArrayList<String> nameArr = new ArrayList<>();
        ArrayList<String> annotationArr = new ArrayList<>();
        ArrayList<String> typeArr = new ArrayList<>();
        ArrayList<String> authorArr = new ArrayList<>();
        ArrayList<String> touArr = new ArrayList<>();
        ArrayList<String> addressArr = new ArrayList<>();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(new File(filePath));
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nl = (NodeList) xPath.evaluate("/DepartmentDataBase/resource/@*",xmlDocument, XPathConstants.NODESET);
            int length = nl.getLength();
            for (int i = 0; i < length; i++){
                Attr attr = (Attr) nl.item(i);
                String name = attr.getName();
                String value = attr.getValue();

                switch (name) {
                    case "NAME" -> {
                        if(!nameArr.contains(value))
                            nameArr.add(value);
                    }
                    case "ANNOTATION" -> {
                        if(!annotationArr.contains(value))
                            annotationArr.add(value);
                    }
                    case "TYPE" -> {
                        if(!typeArr.contains(value))
                            typeArr.add(value);
                    }
                    case "AUTHOR" -> {
                        if(!authorArr.contains(value))
                            authorArr.add(value);
                    }
                    case "TERMS_OF_USE" -> {
                        if(!touArr.contains(value))
                            touArr.add(value);
                    }
                    case "ADDRESS" -> {
                        if(!addressArr.contains(value))
                            addressArr.add(value);
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

        Collections.sort(nameArr);
        Collections.sort(annotationArr);
        Collections.sort(typeArr);
        Collections.sort(authorArr);
        Collections.sort(touArr);
        Collections.sort(addressArr);

        ArrayList<ArrayList<String>> attributes = new ArrayList<>();

        attributes.add(nameArr);
        attributes.add(annotationArr);
        attributes.add(typeArr);
        attributes.add(authorArr);
        attributes.add(touArr);
        attributes.add(addressArr);

        return attributes;
    }
}
