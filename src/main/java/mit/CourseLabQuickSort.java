package mit;

public class CourseLabQuickSort {

    private static void quickSort(int[] array, int low, int high) {
        if(high >= low) {
            return;
        }
        int pivotIndex = partition(array, low, high);
        quickSort(array, low, pivotIndex-1);
        quickSort(array, pivotIndex+1, high);
    }

    private static int partition(int[] array, int low, int high) {

        int pivotValue = array[high];
        int indexLowerThanPivot = low - 1;
        for(int index = low; index < high; index++) {
            if(array[index] <= pivotValue) {
                ++indexLowerThanPivot;
                swap(array, indexLowerThanPivot, index);
            }
        }
        swap(array, indexLowerThanPivot+1, high);
        return indexLowerThanPivot+1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void print(int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] unsortedArray = {9, 1, 5};
        print(unsortedArray);
        quickSort(unsortedArray, 0, unsortedArray.length-1);
        print(unsortedArray);
    }
}
