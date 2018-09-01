package live;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class StringCombinations {
	
	public static void getStringCombinationsPostOrder(String str) {
		for(int i = 0; i < str.length(); i++) {
			String remaining = str.substring(0, i) + str.substring(i+1);
			getStringCombinationsPostOrder(remaining);
		}
		if(!str.isEmpty()) {
			System.out.println(str);
		}
	}
	
	public static void getStringCombinationsPreOrder(String str) {
		if(!str.isEmpty()) {
			System.out.println(str);
		}
		for(int i = 0; i < str.length(); i++) {
			String remaining = str.substring(0, i) + str.substring(i+1);
			getStringCombinationsPreOrder(remaining);
		}
	}

	public static void getStringCombinations(String str) {
		Set<String> set = new LinkedHashSet<>();
		getStringCombinations(str, set);
		System.out.println(set.toString());
	}

	public static void getStringCombinations(String str, Set<String> set) {
		if(!str.isEmpty()) {
			set.add(str);
		}
		for(int i = 0; i < str.length(); i++) {
			String remaining = str.substring(0, i) + str.substring(i+1);
			getStringCombinations(remaining, set);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Post Order:");
		getStringCombinationsPostOrder("abc");
		System.out.println();
		System.out.println("Pre Order:");
		getStringCombinationsPreOrder("abc");
		System.out.println();
		System.out.println("Set:");
		getStringCombinations("abc");
	}
}