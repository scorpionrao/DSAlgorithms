package gayle;

public class ExcelColumn {

    private static String getColName(int colIndex) {
        int numOfDigits = 1;
        int offset = 26;
        while(true) {
            colIndex = colIndex - offset;
            offset = offset * 26;
            numOfDigits++;
            if(colIndex < offset) {
                return getColName(colIndex, numOfDigits);
            }
        }
    }

    private static String getColName(int colIndex, int numOfDigits) {
        if(numOfDigits == 0) {
            return "";
        }
        int digit = colIndex % 26;
        char ch = (char) (digit + 'A');
        int remaining = colIndex / 26;
        return getColName(remaining, numOfDigits - 1) + ch;
    }

    public static void main(String[] args) {
        int index = 30;
        String colName = getColName(index);
        System.out.println("Index: " + index + ", Column Name: " + colName);
    }
}
