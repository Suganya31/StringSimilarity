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
import java.util.List;
import java.util.Map;
import java.util.Set;

/*1. Reading file line by line
2. Tokenize every word and create a map of word and document list
3. for all the words retreive the postings list from the index and find the overlap*/

public class PPJoinAlgorithm {
	static Map<String, HashSet<Integer>> index = new HashMap<String, HashSet<Integer>>();
	static HashSet<Integer> doc = new HashSet<>();


	public static void main(String[] args) throws IOException {


		readFile();
		System.out.println(index);


	}

	private static void readFile() throws IOException {
		// Reading the word file with the list of sentences
		File file = new File("C:\\Users\\Suganya\\StringSimilarity\\StringSimilarity\\Sentence.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		int i = 0;
		Set<String> tokens = new HashSet<>();
		//Storing each line in a set of tokens
		while ((line = br.readLine()) != null) {
			String[] chars = line.split("\\r?\\n");
			for (String str : chars) {
				tokens.add(str);
			}
		}
		System.out.println("Tokens:" + tokens);
		for (String s : tokens) {

			createIndex(s, i);
			System.out.println("Index: " + index);
			i++;

		}
		findOverlap(tokens);


	}

	private static void findOverlap(Set<String> tokens) {
		// find similarity between two set of strings
		int overlap=0;
		int flag=0;
		List<Integer> firstWord = new ArrayList<Integer>();

		//retrieve the postings lists from the index
		// eg. 	firstWord=index.getvalue("firstword")
		firstWord.add(1);
		firstWord.add(2);
		firstWord.add(3);

		List<Integer> secondWord = new ArrayList<Integer>();
		secondWord.add(1);
		secondWord.add(2);


		if(firstWord.size()<secondWord.size())
		{
			flag=1;
		}

		// have the outer loop as the shortest list
		for(int i=0;i<secondWord.size();i++)
		{
			for(int j=0;j<firstWord.size();j++)
			{
				if(secondWord.get(i)==firstWord.get(j))
					overlap++;
			}


		}
		System.out.println("The similarity is"+overlap);


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
		/*
		for (String word : words) {
			System.out.println("word is "+word);
				doc.add(i);
                Index.put(word, doc);


		}*/
//		for (String name: index.keySet()){
//
//			String key =name.toString();
//			System.out.println(key + " ");  
//			System.out.println(index.values() + " ");  
//
//
//
//		} 

	}
}