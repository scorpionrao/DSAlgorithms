package kickstart;

public class RecursionTowerOfHanoi
{

    public static void move(int numOfDisks, int src, int dest, int temp) {
        if(numOfDisks == 0) {
            return;
        }
        moveDisks(numOfDisks - 1, src, temp, dest);
        moveDisk(src, dest);
        moveDisks(numOfDisks - 1, temp, dest, src);
    }

    public static void moveDisks(int numOfDisks, int src, int dest, int temp) {}

    public static void moveDisk(int src, int dest) {}
}
