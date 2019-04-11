package StringSimilarityAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.text.Collator;
import org.aksw.commons.util.Pair;



//Problem1 :reducing the candidates

//Step 1: For each string order the tokens based on inverse document frequency or alphabetical order
//Step 2:calculate the prefix of each string using |size of the string|−threshold+1
//Step 3:calculate the overlap of the pair and if it has overlap then calculate the similarity for the pair

//Problem 2: reducing the records to be indexed







public class PPJoinPrefixFiltering {
	
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
		ArrayList<ArrayList<String>> sortedTokens = new ArrayList<ArrayList<String>>();
		 Map<Pair, Integer> filteredTokens = new HashMap<Pair, Integer>();

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
		filteredTokens=prefixFilter(sortedTokens);	
		
		for (String sentence : finalCandidates) {
			
			

			createPositionalIndex(sentence, docID);
			docID++;

		}
			
		
	//	findOverlap(filteredTokens);

	}
//
	private static Map<Pair, Integer> prefixFilter(ArrayList<ArrayList<String>> sortedTokens) {
		// Step 2:calculate the prefix of each string using |size of the string|−threshold+1
		System.out.println("inside prefix filter");
		int prefix=0;
		int threshold=3;
		int size=0;
		int overlap=0;
//ArrayList<ArrayList<String>> sortedTokens = new ArrayList<ArrayList<String>>();
ArrayList<ArrayList<String>> filteredTokens = new ArrayList<ArrayList<String>>();




		
		for(ArrayList<String> tokens:sortedTokens)
		{
			//calculate prefix of each string
			//System.out.println(tokens);
			
			size=tokens.size();
			prefix=size-threshold+1;
			//System.out.println(tokens.subList(0,prefix));
			ArrayList<String> newList = new ArrayList<>(tokens.subList(0,prefix));

			filteredTokens.add(newList);
			
		}
		
	//	System.out.println(filteredTokens);
        int j,l=0;
		 Map<Pair, Integer> similarityMap = new HashMap<Pair, Integer>();
//System.out.println(filteredTokens.size());
		
		for(int i=0;i<=filteredTokens.size();i++)
		{ j=i+1;
			while(j < sortedTokens.size()) {
				//System.out.println("the values of i and j are"+i+" j is"+j);

			 overlap = findOverlap(sortedTokens.get(i),sortedTokens.get(j));
			 Pair<ArrayList<String>, ArrayList<String>> pair = new Pair<>(sortedTokens.get(i),sortedTokens.get(j));
			 similarityMap.put(pair, overlap);
			 
			 for ( Pair key : similarityMap.keySet() ) {
				 /*System.out.println(key);
				 System.out.println(similarityMap.get(key));*/
				 System.out.println("first");
				 if(similarityMap.get(key)>4)
				 { 

				    System.out.println(" all the keys"+ key + "value is"+similarityMap.get(key));
				 }
				}

			/* if(overlap>3)
			 {
				
				 filteredTokens.add(sortedTokens.get(i));
				 filteredTokens.add(sortedTokens.get(j));
			 }*/
			 j++;
			}
		}
		
		
		return similarityMap;
		
		
		/*return similarityMap;
		for(int k=0;k<=similarityMap.size();k++)
		{ l=k+1;
			while(l < similarityMap.size()) {
				
				if(similarityMap.get(k).get(l)> 4)
			}
			}*/


		
	}

	//Step 1: arrange the words in the string in ascending order 
	private static ArrayList<ArrayList<String>>  sortTokens(ArrayList<String> tokens) {
			System.out.println("inside filterprefix");
			ArrayList<ArrayList<String>> sortedTokens = new ArrayList<ArrayList<String>>();


			for (String word : tokens) {
				ArrayList<String> allWords = new ArrayList<String>();

				allWords.addAll(Arrays.asList(word.split("\\s+")));
		    Collections.sort(allWords);
		    
		    sortedTokens.add(allWords);
			}
/*System.out.println("inside sorttokens");
			System.out.println(sortedTokens);*/
			
		
		
		
		return sortedTokens;
		// TODO Auto-generated method stub
		
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
		// System.out.println("Traversing over Set using Iterator");
		Map<Integer, ArrayList<String>> tokensMap = new HashMap<Integer, ArrayList<String>>(10);

		while (itr.hasNext()) {
			// System.out.println("inside while loop");
			ArrayList<String> allWords = new ArrayList<String>();
			allWords.addAll(Arrays.asList(itr.next().split("\\s+")));

			tokensMap.put(i, allWords);

			i++;

		}

		/*for (int k = 0; k < tokensMap.size(); k++) {
			for (int j = k + 1; j < tokensMap.size(); j++) {

				System.out.println("The similarity is" + findSimilarityOfLists(tokensMap.get(k), tokensMap.get(j)));

			}

		}*/

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