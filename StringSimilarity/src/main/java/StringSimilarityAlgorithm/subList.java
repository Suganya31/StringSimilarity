package StringSimilarityAlgorithm;

import java.util.List;
import java.util.ArrayList;

public class subList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int prefix=0;
		int threshold=3;
		int size=0;
		ArrayList<ArrayList<String>> sortedTokens = new ArrayList<ArrayList<String>>();

		ArrayList<String> list1=new ArrayList<String>();

		ArrayList<String> list2=new ArrayList<String>();

		ArrayList<String> list3=new ArrayList<String>();
		//zatt yes as soon as possible
		//as soon as possible please
		//as soon as please
		list1.add("as");
		list1.add("as");
		list1.add("possible");
		list1.add("soon");
		list1.add("yes");
		list1.add("zatt");



		
		list2.add("as");
		list2.add("as");
		list2.add("please");
		list2.add("possible");

		list2.add("soon");

		
		list3.add("as");
		list3.add("as");
		list3.add("please");
		list3.add("soon");

		
		sortedTokens.add(list1);
		sortedTokens.add(list2);

		sortedTokens.add(list3);

		
		

		

		
		
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
		
		System.out.println(filteredTokens);

	}

}
