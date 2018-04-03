package algorithms.week3;

public class groupingchildren {

    public int minGroups(int numOfChildren) {
        // worst case
        int numOfGroups = numOfChildren;

        /*
        numOfChildren -> G1 U G2 U G3 U G4
        while(all combinations of group arrangement) {
            boolean isItGood = true;
            for each G {
               if(max(Gi) - min(Gi) > 1) {
                    isItGood = false;
                    break;
               }
            }
            if(isItGood) {
                numOfGroups = min(numOfGroups, k);
            }
         }
         */
        return numOfGroups;
    }
}
