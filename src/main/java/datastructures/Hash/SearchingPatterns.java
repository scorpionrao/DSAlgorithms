package datastructures.Hash;

public class SearchingPatterns {

    /*

        AreEqual(S1, S2) {
            if S1.length != S2.length {return false;}
            for(i = 0 to S1.length - 1) {
                if(S1[i] != S2[i]) {return false;}
            }
            return true;
        }

        FindPatternNaive(T, P) {
            int[] result = new result[(t.length - p.length)];
            for(i = 0 to (t.length - p.length)) {
                if(AreEqual(substring(i, p.length), P) {
                    result.append(i);
                }
            }
            return result;
        }

        RabinKarp(T, P) {
            p = prime number above T.length, x = random(1, p-1)
            int[] result = new result[(t.length - p.length)];
            pHash = substring(P, bp, x);
            for(i = 0 to (t.length - p.length)) {
                tHash = substring(i, p.length, bp, x);
                if(pHash != tHash) {
                    continue;
                }
                if(AreEqual(substring(i, p.length), P) {
                    result.append(i);
                }
            }
            return result;
        }
     */
}
