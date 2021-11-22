package uni.makarov.lab2;

import java.io.File;
import java.util.ArrayList;

public interface Strategy {
    ArrayList<Resource> search(File file, Resource searchAttributes);

    default void filterResults(ArrayList<Resource> searchResults, ArrayList<String> searchArr){
        int searchResultsSize = searchResults.size();
        for(int i = 0; i < searchArr.size(); i++){

            if(searchArr.get(i).equals(""))
                continue;

            for(int j = 0; j < searchResultsSize; j++){
                if(!searchResults.get(j).contains(searchArr.get(i))){
                    searchResults.remove(j);
                    searchResultsSize--;
                    j--;
                }
            }
        }
    }
}
