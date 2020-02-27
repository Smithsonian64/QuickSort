import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {

    static ArrayList<Integer> array = new ArrayList<>();
    static int size = 100;
    static int magnitude = 100;

    public static void rightPivotSort(int startIndex, int endIndex) {

        int q;
        if(startIndex < endIndex) {
            q = rightPivotPartition(startIndex, endIndex);
            rightPivotSort(startIndex, q - 1);
            rightPivotSort(q + 1, endIndex);
        }
    }

    public static void medianPivotSort(int startIndex, int endIndex) {

        int q;
        if(startIndex < endIndex) {
            q = medianPivotPartition(startIndex, endIndex);
            rightPivotSort(startIndex, q - 1);
            rightPivotSort(q + 1, endIndex);
        }
    }

    public static int rightPivotPartition(int startIndex, int endIndex) {

        int x = array.get(endIndex); //pivot element
        int i = startIndex - 1;
        for(int j = startIndex; j <= endIndex - 1; j++) {
            if(array.get(j) <= x) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, endIndex);
        return i + 1;

    }

    public static int medianPivotPartition(int startIndex, int endIndex) {

        int x = array.get((startIndex + endIndex) / 2); //pivot element
        swap((startIndex + endIndex) / 2, endIndex);
        int i = startIndex - 1;
        for(int j = startIndex; j <= endIndex - 1; j++) {
            if(array.get(j) <= x) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, endIndex);
        return i + 1;

    }

    public static void swap(int index1, int index2) {
        int temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }

    public static void runTests(int setSize, int setMagnitude) {
        size = setSize;
        magnitude = setMagnitude;

        for(int i = 0; i < size; i++) {
            array.add(0);
        }

        long[] averages = new long[10];
        long average = 0;
        long startTime;
        long endTime;

        for (int i = 0; i < averages.length; i++) {
            for(int j = 0; j < size; j++) {
                array.set(i, (int)(Math.random() * magnitude));
            }
            startTime = System.nanoTime();
            rightPivotSort(0, size - 1);
            endTime = (System.nanoTime() - startTime) / 1000000;
            averages[i] = endTime;
        }
        for(int i = 0; i < averages.length; i++) {
            average = average + averages[i];
        }
        average = average/averages.length;

        System.out.println("right pivot sort running time for n = " + size + ": \t" + average + " ms\n");

        for (int i = 0; i < averages.length; i++) {
            for(int j = 0; j < size; j++) {
                array.set(i, (int)(Math.random() * magnitude));
            }
            startTime = System.nanoTime();
            medianPivotSort(0, size - 1);
            endTime = (System.nanoTime() - startTime) / 1000000;
            averages[i] = endTime;
        }
        for(int i = 0; i < averages.length; i++) {
            average = average + averages[i];
        }
        average = average/averages.length;

        System.out.println("median pivot sort running time for n = " + size + ": \t" + average + " ms\n");



    }


    public static void main(String[] args) {

        runTests(100000, 100);

    }

}
