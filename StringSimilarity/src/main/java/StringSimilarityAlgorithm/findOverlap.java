package StringSimilarityAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aksw.commons.util.Pair;

public class findOverlap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list1=new ArrayList<String>();

		ArrayList<String> list2=new ArrayList<String>();

		ArrayList<String> list3=new ArrayList<String>();
		ArrayList<String> list4=new ArrayList<String>();

		ArrayList<ArrayList<String>> sortedTokens = new ArrayList<ArrayList<String>>();
		//ArrayList<ArrayList<String>> filteredTokens = new ArrayList<ArrayList<String>>();
		Set<String> filteredTokens= new HashSet<String>();

		Integer overlap=0;

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
		
		list4.add("as");
		list4.add("as");
		list4.add("possible");
		list4.add("soon");
		list4.add("yes");
		list4.add("zatt");


		
		
		
		sortedTokens.add(list1);
		sortedTokens.add(list2);

		sortedTokens.add(list3);
		sortedTokens.add(list4);

		int j;
		 Map<Pair, Integer> similarityMap = new HashMap<Pair, Integer>();

		for(int i=0;i<=sortedTokens.size();i++)
		{ for (j = i + 1; j < sortedTokens.size(); j++) {
				//System.out.println("the values of i and j are"+i+" j is"+j);

			 overlap = find(sortedTokens.get(i),sortedTokens.get(j));
			 
			 Pair<ArrayList<String>, ArrayList<String>> pair = new Pair<>(sortedTokens.get(i),sortedTokens.get(j));
			 similarityMap.put(pair, overlap);
			 
				 
				 for ( Pair key : similarityMap.keySet() ) {
					 System.out.println(key);
					 System.out.println(similarityMap.get(key));
					 if(similarityMap.get(key)>4)
					 { 

					    System.out.println(" all the keys"+ key + "value is"+similarityMap.get(key));
					 }
					}
			 
			 j++;
				//similarityMap.get(pair);

			}

		}
		
		//System.out.println(similarityMap);



		

	}
	
	public static int find(ArrayList<String> list1, ArrayList<String> list2)
	{
		ArrayList<String> myObject1 = new ArrayList<String>(list1);
		ArrayList<String> myObject2 = new ArrayList<String>(list2);
		/*System.out.println(myObject1);
		System.out.println(myObject2);*/


		for (int k = 0; k < myObject1.size(); k++) {
			for (int j = k + 1; j < myObject2.size(); j++) {

				myObject1.retainAll(new HashSet<String>(myObject2));

			}

		}
	//	System.out.println(myObject1.size());
		return myObject1.size();

	}

}
