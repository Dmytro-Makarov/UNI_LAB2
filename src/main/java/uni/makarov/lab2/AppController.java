package uni.makarov.lab2;

import java.io.File;
import java.util.ArrayList;

public class AppController {

    private final AppModel model;

    AppController(AppModel model){
        this.model = model;
    }

    void changeAPI(apiType type){
        switch(type){
            case DOM -> model.toolContext.setAnalyzer(new DOMAnalyzer());
            case SAX -> model.toolContext.setAnalyzer(new SAXAnalyzer());
            case XPATH -> model.toolContext.setAnalyzer(new XPathAnalyzer());
        }
    }

    ArrayList<ArrayList<String>> getAttributes(){
        return model.analyze();
    }

    ArrayList<Resource> searchRequest(ArrayList<String> attributesArr){
        return model.toolContext.search(new Resource(attributesArr));
    }

    void setXMLFile(File xmlFile){
        model.setXMLFile(xmlFile);
    }

    void convertRequest(){
        model.convertToHTML();
    }
}
