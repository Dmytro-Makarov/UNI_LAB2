package uni.makarov.lab2;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class XMLToHTMLConverter {
    XMLToHTMLConverter(Source xml, Source xslt){
        convert(xml, xslt);
    }

    private void convert(Source xml, Source xslt){
        StringWriter sw = new StringWriter();

        try {
            FileWriter fw = new FileWriter("DepartmentDB.html");
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transform = tFactory.newTransformer(xslt);
            transform.transform(xml, new StreamResult(sw));
            fw.write(sw.toString());
            fw.close();
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
