package datastructures.basicdatastructures;

public class PalindromeChecker {
	
	public static void main(String[] args) {
		String str = "madam";
		boolean check = true;
		for(int i = 0; i < str.length() / 2; i++) {
			char leftChar = str.charAt(i);
			char rightChar = str.charAt(str.length() - 1 - i);
			check = leftChar == rightChar;
			if(!check) {
				break;
			}
		}
		if(check) {
			System.out.println("It is a palindrome");
		} else {
			// not a palindrome
		}
	}

}
