package uni.makarov.lab2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SAXAnalyzer implements Strategy{
    @Override
    public ArrayList<Resource> search(File file, Resource searchAttributes) {
        ArrayList<Resource> searchResult = new ArrayList<>();

        try {
            SAXHandler handler = new SAXHandler();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);

            SAXParser parser = factory.newSAXParser();

            handler.setHandlerArr(searchResult);

            parser.parse(file, handler);

            searchResult = handler.getHandlerArr();

            filterResults(searchResult, searchAttributes.getAttributes());

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return searchResult;
    }


    class SAXHandler extends DefaultHandler {
        private ArrayList<Resource> handlerArr;

        void setHandlerArr(ArrayList<Resource> arr){
            handlerArr = arr;
        }

        ArrayList<Resource> getHandlerArr(){
            return handlerArr;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            int length = attributes.getLength();

            for(int i = 0; i < length; i += 6){
                Resource currentResource = new Resource();

                currentResource.name = attributes.getValue(i);
                currentResource.annotation = attributes.getValue(i+1);
                currentResource.type = attributes.getValue(i+2);
                currentResource.author = attributes.getValue(i+3);
                currentResource.termsOfUse = attributes.getValue(i+4);
                currentResource.address = attributes.getValue(i+5);

                handlerArr.add(currentResource);
            }

        }
    }
}
