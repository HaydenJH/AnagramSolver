import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class AnagramSolver {
	
	private static BufferedReader r;
	private static String path = "wl.txt";
	private static ArrayList<String> words;
	private static ArrayList<String> results = new ArrayList<String>();
	private static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	
	public static void main(String... args) {
	
		try{
			r = new BufferedReader(new FileReader(path)); //open wordlist file and build an arraylist from it
			words = buildWordlistFromFile();
		}catch (IOException e){
			e.printStackTrace();
			System.out.println("failed to load wordlist");
		}
		
		buildHashMap();	
		results = solveAnagram(args[0]);
		
		if(!(results.isEmpty())){
			for(String s : results){
				System.out.println(s);
			}
		}else{
			System.out.println("No words were found that match that anagram!");
		}
	}

	//returns an ArrayList loaded with words from our wordlist
	private static ArrayList<String> buildWordlistFromFile() throws IOException {
		
		ArrayList<String> words = new ArrayList<String>();
		String temp;
		
		while((temp = r.readLine()) != null){
			words.add(temp);
		}
		
		return words;
	}
	
	//sorts a string by its characters, the idea being an anagram sorts to the same string as the word it comes from
	private static String sortString(String s){
		
		char[] c = s.toLowerCase().toCharArray();
		Arrays.sort(c);
		
		return String.valueOf(c);
	}
	
	//builds a hashmap of the words in wordlist.  Uses sorted word as the key
	private static void buildHashMap(){
		
		for(String word : words){
			String sortedWord = sortString(word);
			
			if(map.containsKey(sortedWord)){ 
				map.get(sortedWord).add(word); //add word to arraylist if key already exists
			}else{
				ArrayList<String> a = new ArrayList<String>();
				a.add(word);
				
				map.put(sortedWord, a); //create new arraylist with new key 
			}
		}
	}
	
	//returns the arraylist mapped to the value of the sorted anagram string if it exists
	private static ArrayList<String> solveAnagram(String anagram){
		String sortedAnagram = sortString(anagram);
		
		if(map.containsKey(sortedAnagram)){
			return map.get(sortedAnagram);
		}else{
			return new ArrayList<String>();
		}
		
	}
}
