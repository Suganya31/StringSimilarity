package StringSimilarityAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*1. Reading file line by line
2. Tokenize every word and create a map of word and document list
3. for all the words retreive the postings list from the index and find the overlap*/

public class PPJoinAlgorithmBasic {
	static Map<String, HashSet<Integer>> index = new HashMap<String, HashSet<Integer>>();
	static HashSet<Integer> doc = new HashSet<Integer>();

	public static void main(String[] args) throws IOException {

		readFile();
		System.out.println(index);

	}

	private static void readFile() throws IOException {
		// Reading the word file with the list of sentences
		File file = new File("/home/suganya/git/StringSimilarity/StringSimilarity/Sentence.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		int i = 0;
		Set<String> tokens = new HashSet<String>();
		// Storing each line in a set of tokens
		while ((line = br.readLine()) != null) {
			String[] chars = line.split("\\r?\\n");
			for (String str : chars) {
				tokens.add(str);
			}
		}
		System.out.println("Tokens:" + tokens);
		for (String s : tokens) {

			createIndex(s, i);
		//	System.out.println("Index: " + index);
			i++;

		}
		findOverlap(tokens);

	}

	private static void findOverlap(Set<String> tokens) {
		// find similarity between two set of strings
		int i=0;

		//String[] firstSentence = tokens..split("\\s+");
		Iterator<String> itr = tokens.iterator();
		System.out.println("Traversing over Set using Iterator"); 
		// find the posting list of all the words in the first and second string
		Map<Integer,ArrayList<String>> tokensMap = new HashMap<Integer, ArrayList<String>>(10);
		
		
		while(itr.hasNext())
		{
			ArrayList<String> allWords = new ArrayList<String>();
			allWords.addAll(Arrays.asList(itr.next().split("\\s+")));

			tokensMap.put(i,allWords);
			
		    i++;

			
        }
	

		tokensMap.get(1).retainAll(new HashSet<String>(tokensMap.get(0)));

	
	System.out.println("The similarity is"+tokensMap.get(1).size());

	}

	public static void createIndex(String line, int i) {

		if (line == null) {
			line = "";
		}

		//Indexing every words; create a map of the word and the list of documents that contain that word
		String[] words = line.split("\\s+");

		for (String word : words) {
			System.out.println("word is: "+word);
			if (index.containsKey(word)) {   
				index.get(word).add(i);
			}else{
				index.put(word, new HashSet<Integer>(Arrays.asList(i)));

			}
			

		}
		

	}
}