import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SowpodsUtil {
    public static HashMap<Integer, ArrayList<String>> anagramMap ;

    public static void readFromFile() throws IOException {
        anagramMap = new HashMap<>();
        String fileName = "src/sowpods.txt";
        String line;
        FileReader fileReader =
                new FileReader(fileName);
        BufferedReader bufferedReader =
                new BufferedReader(fileReader);
        while((line = bufferedReader.readLine()) != null ) {
            String sortedString = sortString(line);
            if(anagramMap.get(line.length()) == null){
                ArrayList arrayList = new ArrayList();
                arrayList.add(sortedString);
                anagramMap.put(line.length(),arrayList);
            }else {
                ArrayList arrayList = anagramMap.get(line.length());
                if(!arrayList.contains(sortedString)){
                    arrayList.add(sortedString);
                    anagramMap.put(line.length(),arrayList);
                }
            }
        }
        bufferedReader.close();
    }

    public static void printMap(HashMap<String, ArrayList<String>> hashMap) {
        for (Object o : hashMap.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            System.out.println(pair.getKey() + " " + pair.getValue().toString());
        }
    }

    public static HashMap getAnagramMap() throws IOException {
        readFromFile();
        return anagramMap;
    }

    private static String sortString(String anagram) {
        char tempArray[] = anagram.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
}
