package live;

import java.util.HashMap;

public class Encoding
{
    /*
    var x = [];
    x[1] = true;
    x[32] = true;
    x[50] = true;
    x[70010] = false;
    serialize(x)   // CA5EA3BA~70011
    Step 1: Base-32
    Alphabet: ABCDEFGHIJKLMNOPQRSTUVWXYZabcdef
    Example input: [true, false, false, false, true, false]
    Example Encoding:  A=true,false    B=false,false
    Example output: ABA
    Signature: String base32Encoding(boolean[] array)
    Step 2: Run Length Encoding
    Example Input: AAAAbRRR
    Example Output: A4bR3
    Signature: String runLengthEncoding(String base32EncodedString)
    Step 3: Add Length
    Example Output: A4BR3~23
    Signature: String addLength(String runLengthEncodedString)
    Overall: String solve(boolean[] array)
    */

    /*
    // Thoughts
    We need minimum length to represent each character 32 combinations - 2 ^ 5 = 32
    So we will read through 5 indexes at a time and represent that as a encoded value.
    Very Basic example:
    A = [true true true true true]
    Input: [true, true, true, true, true] // 1 1 1 1 1
    Step 1: Base-32
    Output: A
    Output: AA
    Step 2: Run length Encoding
    Output: A
    Output: A2
    Step 3: Add Length
    Output: A~5
    Output: A2~10
    Next Step:
    Step 1: Base-32
    Alphabet: ABCDEFGHIJKLMNOPQRSTUVWXYZabcdef
    Example input: [true, false, false, false, true, false]
    Example Encoding:  A=true,false    B=false,false
    Example output: ABA
    Signature: String base32Encoding(boolean[] array)
    /*
        representation for each of the encoded character
        read 5 elements at a time, look up for the encoded character
        add it to the result

        Edge cases: it is not guaranteed for length to be multiple of 5
        Edge cases: if length is 0 - return empty string, append false as required

    */

    // Signature: String base32Encoding(boolean[] array)
    public String base32Encoding(boolean[] array) {

        if(array == null || array.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        HashMap<String, String> dataSet = buildDataSet();
        for(int i = 0; i < array.length; i = i + 5) {
            StringBuilder sb = new StringBuilder();
            for(int j = i; j < (i + 5); j++) {
                if((i+j > array.length - 1)) {
                    sb.append("0");
                    continue;
                }
                if(array[i+j] == true) {
                    sb.append("0");
                } else {
                    sb.append("1");
                }
            }
            String str = sb.toString();
            String encoded32Value = dataSet.get(str);
            result.append(encoded32Value);
        }
        return result.toString();

    }

    public HashMap<String, String> buildDataSet() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("00000", "A");
        hashMap.put("00001", "B");
        hashMap.put("00010", "C");
        hashMap.put("00011", "D");
        hashMap.put("00100", "E");
        hashMap.put("00101", "F");
        hashMap.put("00110", "G");
        hashMap.put("00111", "H");
        hashMap.put("01000", "I");
        hashMap.put("01001", "J");
        hashMap.put("01010", "K");
        hashMap.put("01011", "L");
        hashMap.put("01100", "M");
        hashMap.put("01101", "N");
        hashMap.put("01110", "O");
        hashMap.put("01111", "P");
        hashMap.put("10000", "Q");
        hashMap.put("10001", "R");
        hashMap.put("10010", "S");
        hashMap.put("10011", "T");
        hashMap.put("10100", "U");
        hashMap.put("10101", "V");
        hashMap.put("10110", "W");
        hashMap.put("10111", "X");
        hashMap.put("11000", "Y");
        hashMap.put("11001", "Z");
        hashMap.put("11010", "a");
        hashMap.put("11011", "b");
        hashMap.put("11100", "c");
        hashMap.put("11101", "d");
        hashMap.put("11110", "e");
        hashMap.put("11111", "f");
        return hashMap;

    }

    /*
    Step 2: Run Length Encoding
    Example Input: AAAAbRRR
    Example Output: A4bR3
    Signature: String runLengthEncoding(String base32EncodedString)
    // pseudo code
    result string
    store the first character
    counter as 1
    traverse through the length {
        if (first character in the string) { store and continue) }
        if (charAt(index) is same as stored character) increment count and continue
        if (this means new character) {
            // explain in detail
            append stored character
            if(counter > 1) {append counter and continue}
            reset store character to char at this index
            reset counter = 1
        }
    }
    */

    // Signature: String runLengthEncoding(String base32EncodedString)
    public String runLengthEncoding(String base32EncodedString) {

        if(base32EncodedString == null || base32EncodedString.length() < 2) {
            return base32EncodedString;
        }

        StringBuilder sb = new StringBuilder();
        int counter = 1;
        char ch = base32EncodedString.charAt(0);
        for(int i = 1; i < base32EncodedString.length(); i++) {
            if (base32EncodedString.charAt(i) == ch) {
                counter++;
                continue;
            } else {
                sb.append(ch);
                if(counter > 1) {
                    sb.append(counter);
                }
                counter = 1;
                ch = base32EncodedString.charAt(i);
            }
        }
        sb.append(ch);
        if(counter > 1) {
            sb.append(counter);
        }
        return sb.toString();
    }

    public String addLength(String runLengthEncodedString, int inputLength) {
        return runLengthEncodedString + "~" + inputLength;
    }

    public String solve(boolean[] array) {
        if(array == null || array.length == 1) {return "";}
        String base32EncodedString = base32Encoding(array);
        String runLengthEncodedString = runLengthEncoding(base32EncodedString);
        String addLengthString = addLength(runLengthEncodedString, array.length);
        return addLengthString;
    }

    public static void main(String[] args) {
        Encoding enc = new Encoding();
        boolean[] booleans = {true, true, true, true, true, false, false, false, false, false};
        System.out.println(enc.solve(booleans));
    }

}
