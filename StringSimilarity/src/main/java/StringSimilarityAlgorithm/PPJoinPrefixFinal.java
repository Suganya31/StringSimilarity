package StringSimilarityAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.aksw.commons.util.Pair;



//Problem1 :reducing the candidates

//Step 1: For each string order the tokens based on inverse document frequency or alphabetical order
//Step 2:calculate the prefix of each string using |size of the string|−threshold+1
//Step 3:calculate the overlap of the pair and if it has overlap then calculate the similarity for the pair

//Problem 2: reducing the records to be indexed







public class PPJoinPrefixFinal {
	
	static Map<String, Map<Integer,ArrayList<Integer>>> index = new HashMap<String, Map<Integer, ArrayList<Integer>>>();
	static HashSet<Integer> doc = new HashSet<Integer>();

	public static void main(String[] args) throws IOException {

		readFile();

	}

	private static void readFile() throws IOException {
		File file = new File("/home/suganya/git/StringSimilarity/StringSimilarity/Sentence.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		ArrayList<ArrayList<String>> sortedTokens = new ArrayList<ArrayList<String>>();
		 HashSet<Integer> filteredTokens = new HashSet<Integer>();
		 Map<Integer, ArrayList<String>> docIDMAP = new HashMap<Integer, ArrayList<String>>();


		ArrayList<String> finalCandidates = new ArrayList<String>();

		int docID = 0;
		ArrayList<String> tokens = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			String[] sentences = line.split("\\r?\\n");
			for (String str : sentences) {
				tokens.add(str);
			}
		}
		
		sortedTokens=sortTokens(tokens);
		for(int m=0;m<sortedTokens.size();m++)
		{
			docIDMAP.put(m, sortedTokens.get(m));
			
		}
		filteredTokens=prefixFilter(docIDMAP);	
		 for(int i=0;i<filteredTokens.size();i++){
			finalCandidates.add(docIDMAP.get(i).toString());
		}
		
		
		for (String sentence : finalCandidates) {
			
			
			createPositionalIndex(sentence, docID);
			docID++;

		}
			
		
		findSimilarity(finalCandidates);

	}

	private static HashSet<Integer> prefixFilter(Map<Integer, ArrayList<String>> docIDMAP) {
		// Step 2:calculate the prefix of each string using |size of the string|−threshold+1
		int prefix=0;
		int threshold=3;
		int size=0;
		int overlap=0;

Map<Integer, ArrayList<String>> docIDMAPmodified = new HashMap<Integer, ArrayList<String>>();
HashSet<Integer> finalCandidates = new HashSet<Integer>();


for ( Integer key : docIDMAP.keySet() ) 
{
	size=docIDMAP.get(key).size();
	prefix=size-threshold+1;
	ArrayList<String> newList = new ArrayList<>(docIDMAP.get(key).subList(0,prefix));

	docIDMAPmodified.put(key,newList);
}


		
        int j;
		 Map<Pair, Integer> similarityMap = new HashMap<Pair, Integer>();
		
		for(int i=0;i<=docIDMAPmodified.size();i++)
		{ j=i+1;
			while(j < docIDMAPmodified.size()) {

			 overlap = findOverlap(docIDMAPmodified.get(i),docIDMAPmodified.get(j));
			 if(overlap>=3)
			 {
				 finalCandidates.add(i);
			 }
			 Pair<ArrayList<String>, ArrayList<String>> pair = new Pair<>(docIDMAPmodified.get(i),docIDMAPmodified.get(j));
			 similarityMap.put(pair, overlap);
			 j++;
			}
		}
			
			
		
		
		
		return finalCandidates;
		
		
		
	}

	//Step 1: arrange the words in the string in ascending order 
	private static ArrayList<ArrayList<String>>  sortTokens(ArrayList<String> tokens) {
			ArrayList<ArrayList<String>> sortedTokens = new ArrayList<ArrayList<String>>();


			for (String word : tokens) {
				ArrayList<String> allWords = new ArrayList<String>();

				allWords.addAll(Arrays.asList(word.split("\\s+")));
		    Collections.sort(allWords);
		    
		    sortedTokens.add(allWords);
			}

			
		
		
		
		return sortedTokens;
		
	}

	private static int findOverlap(ArrayList<String> list1, ArrayList<String> list2) {

		ArrayList<String> myObject1 = new ArrayList<String>(list1);
		ArrayList<String> myObject2 = new ArrayList<String>(list2);

		for (int k = 0; k < myObject1.size(); k++) {
			for (int j = k + 1; j < myObject2.size(); j++) {

				myObject1.retainAll(new HashSet<String>(myObject2));

			}

		}
		return myObject1.size();

	}

	private static void findSimilarity(ArrayList<String> tokens) {
		int i = 0;

		Iterator<String> itr = tokens.iterator();
		Map<Integer, ArrayList<String>> tokensMap = new HashMap<Integer, ArrayList<String>>(10);

		while (itr.hasNext()) {
			ArrayList<String> allWords = new ArrayList<String>();
			allWords.addAll(Arrays.asList(itr.next().split("\\s+")));

			tokensMap.put(i, allWords);

			i++;

		}

		for (int k = 0; k < tokensMap.size(); k++) {
			for (int j = k + 1; j < tokensMap.size(); j++) {

				System.out.println("The similarity of "+tokensMap.get(k)+" and "+ tokensMap.get(j) +" is "+ findOverlap(tokensMap.get(k), tokensMap.get(j)));

			}

		}

	}

	public static void createPositionalIndex(String sentence, int docID) {
       int i=0;
		if (sentence == null) {
			sentence = "";
		}

		String[] words = sentence.split("\\s*,\\s*");
	      String wordsNew[] = new String[words.length];



		for (String word : words) {
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