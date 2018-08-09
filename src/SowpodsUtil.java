import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SowpodsUtil {
    public static HashMap<String, ArrayList<String>> anagramMap = new HashMap<>();

    public static void intSowpodsUtil(int noOfChar){

        File file = new File("./src/sowpods.txt");
        System.out.println(file);

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null ) {
                if (st.length() == noOfChar) {
                    char[] letters = st.toCharArray();
                    Arrays.sort(letters);
                    String key = new String(letters);
                    ArrayList<String> temp;
                    if (!anagramMap.containsKey(key)) {
                        temp = new ArrayList<>();
                    } else {
                        temp = anagramMap.get(key);
                    }
                    temp.add(st);
                    anagramMap.put(key, temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void printMap(HashMap<String, ArrayList<String>> hashMap) {
        for (Object o : hashMap.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            System.out.println(pair.getKey() + " " + pair.getValue().toString());
        }
    }

    public static HashMap getAnagramMap()  {
        return anagramMap;
    }


}
