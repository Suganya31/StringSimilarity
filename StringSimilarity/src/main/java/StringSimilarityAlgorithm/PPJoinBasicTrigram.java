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

public class PPJoinBasicTrigram {
	static Map<String, HashSet<Integer>> index = new HashMap<String, HashSet<Integer>>();
	static HashSet<Integer> doc = new HashSet<Integer>();

	public static void main(String[] args) throws IOException {

		readFile();
		//System.out.println(index);

	}

	private static void readFile() throws IOException {
		File file = new File("/home/suganya/git/StringSimilarity/StringSimilarity/words");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		int i = 0;
		Map<Integer, ArrayList<String>> tokensMap = new HashMap<Integer, ArrayList<String>>();
		ArrayList<String> tokens = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			String[] chars = line.split("\\r?\\n");
			for (String str : chars) {
				tokens.add(str);
			}
		}
		 System.out.println("Tokens:" + tokens);
		for (String s : tokens) {

			createIndex(s, i);
			i++;

		}
		findOverlap(tokens);

	}

	private static int findSimilarityOfLists(ArrayList<String> list1, ArrayList<String> list2) {

		ArrayList<String> localList1 = new ArrayList<String>(list1);
		ArrayList<String> localList2 = new ArrayList<String>(list2);
	

		localList1.retainAll(new HashSet<String>(localList2));

		return localList1.size();

	}

	private static void findOverlap(ArrayList<String> tokens) {
		int i = 0;
		String word;
		Iterator<String> itr = tokens.iterator();
		Map<Integer, ArrayList<String>> tokensMap = new HashMap<Integer, ArrayList<String>>();

		while (itr.hasNext()) {
			ArrayList<String> allWords = new ArrayList<String>();
            word=itr.next();

	        for (int k = 0; k < word.length() - 3 + 1; k++) {
			allWords.add(word.substring(k, k + 3));
	        }


			tokensMap.put(i, allWords);

			i++;

		}
		
		
		

		for (int k = 0; k < tokensMap.size(); k++) {
			for (int j = k + 1; j < tokensMap.size(); j++) {
				System.out.println(findSimilarityOfLists(tokensMap.get(k), tokensMap.get(j)));

			}

		}

	}

	public static void createIndex(String line, int i) {

		if (line == null) {
			line = "";
		}

		ArrayList<String> tokens = new ArrayList<String>();
        for (int k = 0; k < line.length() - 3 + 1; k++) {
            tokens.add(line.substring(k, k + 3));
        }

		for (String word : tokens) {
			if (index.containsKey(word)) {
				index.get(word).add(i);
			} else {
				index.put(word, new HashSet<Integer>(Arrays.asList(i)));

			}

		}

	}
}