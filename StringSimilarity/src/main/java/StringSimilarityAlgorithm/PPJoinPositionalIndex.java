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
import java.util.List;
import java.util.Map;

public class PPJoinPositionalIndex {
	
	// instead of hashset of integers it should be a map of <integer which represents the document id, position of the word>
	static Map<String, Map<Integer,ArrayList<Integer>>> index = new HashMap<String, Map<Integer, ArrayList<Integer>>>();
	static HashSet<Integer> doc = new HashSet<Integer>();

	public static void main(String[] args) throws IOException {

		readFile();
		System.out.println(index);

	}

	private static void readFile() throws IOException {
		File file = new File("/home/suganya/git/StringSimilarity/StringSimilarity/Sentence.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		int docID = 0;
		ArrayList<String> tokens = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			String[] sentences = line.split("\\r?\\n");
			for (String str : sentences) {
				tokens.add(str);
			}
		}
		for (String sentence : tokens) {

			createPositionalIndex(sentence, docID);
			docID++;

		}
		findOverlap(tokens);

	}

	private static int findSimilarityOfLists(ArrayList<String> list1, ArrayList<String> list2) {

		ArrayList<String> myObject1 = new ArrayList<String>(list1);
		ArrayList<String> myObject2 = new ArrayList<String>(list2);

		for (int k = 0; k < myObject1.size(); k++) {
			for (int j = k + 1; j < myObject2.size(); j++) {

				myObject1.retainAll(new HashSet<String>(myObject2));

			}

		}
		return myObject1.size();

	}

	private static void findOverlap(ArrayList<String> tokens) {
		int i = 0;

		Iterator<String> itr = tokens.iterator();
		// System.out.println("Traversing over Set using Iterator");
		Map<Integer, ArrayList<String>> tokensMap = new HashMap<Integer, ArrayList<String>>(10);

		while (itr.hasNext()) {
			// System.out.println("inside while loop");
			ArrayList<String> allWords = new ArrayList<String>();
			allWords.addAll(Arrays.asList(itr.next().split("\\s+")));

			tokensMap.put(i, allWords);

			i++;

		}

		for (int k = 0; k < tokensMap.size(); k++) {
			for (int j = k + 1; j < tokensMap.size(); j++) {

				System.out.println("The similarity is" + findSimilarityOfLists(tokensMap.get(k), tokensMap.get(j)));

			}

		}

	}

	public static void createPositionalIndex(String sentence, int docID) {
       int i=0;
		if (sentence == null) {
			sentence = "";
		}

		String[] words = sentence.split("\\s+");
	      String wordsNew[] = new String[words.length];



		for (String word : words) {
			// System.out.println("word is: " + word);
			wordsNew[i] =word;
			i++;
			if (!index.containsKey(word)) {
				
		
				Map<Integer, ArrayList<Integer>> positionMap = new HashMap<Integer, ArrayList<Integer>>();
				ArrayList<Integer> listOfPositions = new ArrayList<Integer>();
				listOfPositions.add(Arrays.asList(wordsNew).lastIndexOf(word));
				 positionMap.put(docID,listOfPositions);
				 index.put(word, positionMap);		 

         } else {
				  Map<Integer, ArrayList<Integer>> positionMap = 
                          index.get(word);
			
			              
			         			       
	          if (positionMap.containsKey(docID)) {
	        	  positionMap.get(docID).add(Arrays.asList(wordsNew).lastIndexOf(word));
	          } else {
			         ArrayList<Integer> listOfPositions = new ArrayList<Integer>();
			         listOfPositions.add(Arrays.asList(wordsNew).lastIndexOf(word));
	             positionMap.put(docID, listOfPositions);
	          }

			}

		}

	}
}