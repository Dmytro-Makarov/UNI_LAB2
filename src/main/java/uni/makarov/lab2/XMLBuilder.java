package uni.makarov.lab2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XMLBuilder {
    XMLBuilder(){

    }

    String createNewXML(ArrayList<Resource> resources) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/uni/makarov/lab2/DepartmentDB_Search.xml"));

                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
                writer.write("<DepartmentDataBase>\n");

                writer.flush();
                for (Resource tempResource : resources) {
                    writer.write("  <resource\n");
                    writer.write("      NAME = " + "\"" + tempResource.name + "\"\n");
                    writer.write("      ANNOTATION = " + "\"" + tempResource.annotation + "\"\n");
                    writer.write("      TYPE = " + "\"" + tempResource.type + "\"\n");
                    writer.write("      AUTHOR = " + "\"" + tempResource.author + "\"\n");
                    writer.write("      TERMS_OF_USE = " + "\"" + tempResource.termsOfUse + "\"\n");
                    writer.write("      ADDRESS = " + "\"" + tempResource.address + "\">\n");
                    writer.write("  </resource>\n");
                    writer.flush();
                }
                writer.write("</DepartmentDataBase>");

                writer.flush();


            } catch (IOException e) {
                e.printStackTrace();
            }
        return "src/main/resources/uni/makarov/lab2/DepartmentDB_Search.xml";
    }

}
