package uni.makarov.lab2;

import java.util.ArrayList;

public class Resource {
        public String name;
        public String annotation;
        public String type;
        public String author;
        public String termsOfUse;
        public String address;

        public Resource() { }
        public Resource(ArrayList<String> attributes) {
                name = attributes.get(0);
                annotation = attributes.get(1);
                type = attributes.get(2);
                author = attributes.get(3);
                termsOfUse = attributes.get(4);
                address = attributes.get(5);
        }

        public boolean contains(String str){
                ArrayList<Boolean> doesContain = new ArrayList<>();

                doesContain.add(name.equals(str));
                doesContain.add(annotation.equals(str));
                doesContain.add(type.equals(str));
                doesContain.add(author.equals(str));
                doesContain.add(termsOfUse.equals(str));
                doesContain.add(address.equals(str));

                return doesContain.contains(true);
        }

        public ArrayList<String> getAttributes(){
                ArrayList<String> attributes = new ArrayList<>();
                attributes.add(name);
                attributes.add(annotation);
                attributes.add(type);
                attributes.add(author);
                attributes.add(termsOfUse);
                attributes.add(address);
                return  attributes;
        }
}
