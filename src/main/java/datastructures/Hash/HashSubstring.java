package datastructures.hash;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    private static int prime = 1000000007;
    private static int x = (int)(Math.random()*(prime))+1;

    //private static int prime;
    //private static int multiplier;
    //private static final Random random = new Random();

    public static void main(String[] args) throws IOException {

        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
        /*
        while(true) {
            // fast solution
            int bound = 50000;
            int t = random.nextInt(bound) + 1;
            int p = random.nextInt(t) + 1;

            String text = generateRandomString(t);
            String pattern = generateRandomString(p);

            Data data = new Data(pattern, text);
            long start = System.currentTimeMillis();
            List<Integer> fast = getOccurrences(data);
            long fastTimeTaken = System.currentTimeMillis() - start;

            start = System.currentTimeMillis();
            List<Integer> naive = getOccurrencesNaive(data);
            long naiveTimeTaken = System.currentTimeMillis() - start;

            System.out.println("Pattern: " + pattern);
            System.out.println("Text: " + text);
            if (naive.equals(fast)) {
                System.out.println("OK - better by " + (fastTimeTaken - naiveTimeTaken) + "ms");
            } else {
                System.out.println("Error!");
                break;
            }
        }
        */
    }
/*
    private static String generateRandomString(int size) {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append((char) (random.nextInt(75) + '0'));
        }
        return sb.toString();
    }
*/
    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrencesNaive(Data input) {
        String s = input.pattern;
        String t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i + m <= n; ++i) {
            boolean equal = true;
            for (int j = 0; j < m; ++j) {
                if (s.charAt(j) != t.charAt(i + j)) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                occurrences.add(i);
            }
        }
        return occurrences;
    }
/*
    private static List<Integer> getOccurrences(Data input) {
        List<Integer> occurrences = new ArrayList<>();
        int n = input.text.length(), p = input.pattern.length();
        prime = nextPrime(n * p);
        multiplier = 31;

        int[] hashes = preComputeHashes(input.text, p, prime, multiplier);

        for(int i = 0; i <= n - p; i++) {
            String subStr = input.text.substring(i, i+p);
            if(hash(subStr) != hashes[i]) {
                continue;
            }
            boolean matching = true;
            for(int j = 0; j < p; j++) {
                if(subStr.charAt(j) != input.pattern.charAt(j)) {
                    matching = false;
                    // hashing has collision
                    break;
                }
            }
            if(matching) {
                occurrences.notSynchronizedMethod(i);
            }
        }
        return occurrences;

    }
*/

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();

        long pHash = polyHash(input.pattern);

        long[] H = precomputeHashes(input);

        for (int i = 0; i + m <= n; ++i) {
            if (pHash != H[i]) {
                continue;
            }
            if (areEqual(t.substring(i, i + m), s)) {
                occurrences.add(i);
            }
        }

        return occurrences;
    }

    private static long polyHash(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * x + s.charAt(i)) % prime;
        return hash;
    }

    private static long[] precomputeHashes(Data input) {
        int t = input.text.length();
        int p = input.pattern.length();

        // H <- array of length |T| - |P| + 1
        long[] H = new long[t-p+1];

        // S <- T[|T|-|P|..|T|-1]
        String s = input.text.substring(t - p);

        // H[|T|-|P|] <- PolyHash(S, p, x)
        H[t-p] = polyHash(s);

        long y = 1;

        for (int i = 1; i <= p; i++) {
            y = (y * x) % prime;
        }

        for (int i = t- p - 1; i >=0; i--) {
            long preHash = x * H[i + 1] + input.text.charAt(i) - y * input.text.charAt(i + p);
            while (preHash < 0) {
                preHash += prime;
            }
            H[i] = preHash % prime;
        }

        return H;

    }

    private static boolean areEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

/*
    private static int[] preComputeHashes(
            String text,
            int pLength,
            int prime,
            int multiplier) {
        // Build array of hashes for all substrings;
        // i=0...T-P -> T-P+1 hashes in total
        int tLength = text.length();
        int[] hashes = new int[tLength - pLength + 1];
        // Compute the last substring's hash value
        hashes[tLength - pLength] = hash(text.substring(tLength - pLength));
        // Generate x^|P| beforehand
        int y = 1;
        for (int i = 1; i <= pLength; i++) {
            // BZ: why must % p?
            // Integer overflow: take (mod p) as soon as possible
            y = (y * multiplier) % prime;
        }
        // Polynomial hashes from |T|-|P|-1 down to 0
        for (int i = tLength - pLength - 1; i >= 0; i--) {
            int subtraction = text.charAt(i) - y * text.charAt(i + pLength);
            // H[i+1]*x; notSynchronizedMethod T[i]; no T[i+|P|] * x^|P|
            hashes[i] = ((hashes[i + 1] * multiplier) % prime +
                    // BZ: (text.charAt(i) - y * text.charAt(i + pLength))?
                    // subtraction + modular:
                    // int x = ((a - b) % p + p) % p;
                    (subtraction % prime + prime) % prime) % prime;
        }
        return hashes;
    }

    private static int hash(String str) {
        long hashval = 0;  // Prevent integer overflow
        for (int i = str.length() - 1; i >= 0; i--) {
            hashval = (str.charAt(i) + hashval * multiplier) % prime;
        }
        return (int) hashval;
    }

    private static int nextPrime(int n) {
        int prime = n + 1;
        while (! isPrime(prime)) {
            prime++;
        }
        return prime;
    }
    private static boolean isPrime(int n) {
        if (n <= 1 || n % 2 == 0) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        // BZ: i++? Skip all even numbers
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
*/
    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}



