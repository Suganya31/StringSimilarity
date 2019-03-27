package StringSimilarityAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PPjoinbk {
	static HashMap<String, HashMap<Integer, Integer>> Index = new HashMap<String, HashMap<Integer, Integer>>();
	static HashMap<Integer, Integer> inner = new HashMap<Integer, Integer>();


	public static void main(String[] args) throws IOException {
		

		readFile();
		

	}

	private static void readFile() throws IOException {
		// Reading the word file with the list of words
		File file = new File("C:\\Users\\Suganya\\StringSimilarity\\StringSimilarity\\Sentence.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		int i = 1;
		Set<String> tokens = new HashSet<>();

		while ((line = br.readLine()) != null) {
			String[] chars = line.split("\\r?\\n");
			for (String str : chars) {
				tokens.add(str);
			}
		}
		for (String s : tokens) {

			tokenize(s, i);
			i++;

		}

	}

	public static void tokenize(String s, int i) {
	

		if (s == null) {
			s = "";
		}

		String[] chars = s.split("\\s+");

		for (String str : chars) {
			if (!Index.containsKey(str) || Index.isEmpty()) {

				inner.put(i, 1);

				Index.put(str, inner);

			}

			else if (Index.containsKey(str)) {
				if (Index.get(str).containsKey(i))

				{
					Collection<Integer> value = Index.get(str).values();

					int count = (int) value.toArray()[0];

					count = count + 1;

					inner.put(i, count);

					Index.put(str, inner);

				}

				else if (!Index.get(str).containsKey(i))

				{

					inner.put(i, 1);

					Index.put(str, inner);

				}

			}

		}
		for (String name: Index.keySet()){

            String key =name.toString();
            System.out.println(key + " ");  


} 
	}
}
