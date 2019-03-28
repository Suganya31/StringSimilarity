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

public class PPJoinAlgorithmBasicV1 {
	static Map<String, HashSet<Integer>> index = new HashMap<String, HashSet<Integer>>();
	static HashSet<Integer> doc = new HashSet<Integer>();

	public static void main(String[] args) throws IOException {

		readFile();
		System.out.println(index);

	}

	private static void readFile() throws IOException {
		File file = new File("/home/suganya/git/StringSimilarity/StringSimilarity/Sentence.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		int i = 0;
		// System.out.println("Traversing over Set using Iterator");
		Map<Integer, ArrayList<String>> tokensMap = new HashMap<Integer, ArrayList<String>>(10);
		ArrayList<String> tokens = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			String[] chars = line.split("\\r?\\n");
			for (String str : chars) {
				tokens.add(str);
			}
		}
		// System.out.println("Tokens:" + tokens);
		for (String s : tokens) {

			createIndex(s, i);
			i++;

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

	public static void createIndex(String line, int i) {

		if (line == null) {
			line = "";
		}

		String[] words = line.split("\\s+");

		for (String word : words) {
			// System.out.println("word is: " + word);
			if (index.containsKey(word)) {
				index.get(word).add(i);
			} else {
				index.put(word, new HashSet<Integer>(Arrays.asList(i)));

			}

		}

	}
}