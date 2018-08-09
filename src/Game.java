import java.lang.reflect.Array;
import java.util.*;

public class Game {

    String secretWord;
    Map<String,ArrayList<String>> anagramMap;
    List<String> keysToSearch;

    public Game() {
        anagramMap=SowpodsUtil.getAnagramMap();
        Random rand = new Random();
        keysToSearch = new ArrayList<String>(anagramMap.keySet());
        ArrayList<String>randomList = anagramMap.get(keysToSearch.get(rand.nextInt(keysToSearch.size())));
        secretWord =randomList.get(rand.nextInt(randomList.size()));
    }

    public void play(){
        Scanner scan = new Scanner(System.in);
        int userGuess=0;
        int sysGuess=0;
        int i=0;
        String guessWord;
        while(true){
            System.out.print("Enter guess word");
            guessWord=scan.next();
            System.out.println("No of matches"+noOfMatches(guessWord));
            System.out.print("Enter guessed letters");
            guessWord=scan.next();
            userGuess=noOfMatches(guessWord);
            if(userGuess==secretWord.length()){
                break;
            }
            guessWord=anagramMap.get(keysToSearch.get(0)).get(0);
            anagramMap.remove(keysToSearch.get(0));
            keysToSearch.remove(0);
            System.out.println("Computer's guess word : "+guessWord+"\nEnter no of matches with your secret word");
            sysGuess = scan.nextInt();
            if(sysGuess==0)
                prune(guessWord);
            if(sysGuess==secretWord.length())
                break;
        }

        if(userGuess==secretWord.length()){
            System.out.println("Your guess are correct... YOU WIN");
        }
        else
            System.out.println("System guessed your word... SYSTEM WINS");
    }

    private int noOfMatches(String guessWord){
        int matchCount = 0;
        for(int i=0;i<secretWord.length();i++){
            if(guessWord.contains(secretWord.charAt(i)+""))
                matchCount++;
        }
        return matchCount;
    }

    private void prune(String guessWord){
        ArrayList<String> keysToRemove = new ArrayList<>();
        for(char c : guessWord.toCharArray()){
            for(String key : keysToSearch){
                if(key.contains(c+"")){
                    keysToRemove.add(key);
                }
            }
        }
        keysToSearch.removeAll(keysToRemove);
    }



}
