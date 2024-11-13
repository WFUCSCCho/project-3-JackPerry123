import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if (left >= right)
            return;

        int mid = (left + right) / 2;

        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);

        merge(a, left, mid, right);


    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me

        ArrayList<T> p1 = new ArrayList<T>();
        ArrayList<T> p2 = new ArrayList<T>();

        for(int i = 0; i < mid-left + 1; i++)
            p1.add(a.get(left + i));

        for(int i = 0; i < right - mid; i++)
            p2.add(a.get(mid + i + 1));
        ArrayList<T> combined = new ArrayList<T>();
        while(!p2.isEmpty() && !p1.isEmpty()){
            if(p1.get(0).compareTo(p2.get(0)) < 0){
                combined.add(p1.get(0));
                p1.remove(0);
            } else {
                combined.add(p2.get(0));
                p2.remove(0);
            }
        }

        while(!p1.isEmpty()){
            combined.add(p1.get(0));
            p1.remove(0);
        }

        while(!p2.isEmpty()){
            combined.add(p2.get(0));
            p2.remove(0);
        }

        for(int i = 0; i < combined.size(); i++)
            a.set(left + i, combined.get(i));

        return;

    }

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if(right >= left)
            return;

        int pivot = partition(a, left, right);

        quickSort(a, left, pivot - 1);
        quickSort(a, pivot + 1, right);

    }

    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // Finish Me

        int mid = left + (right - left) / 2;

        if(a.get(left).compareTo(a.get(mid))> 0)
            swap(a,left,mid);
        if(a.get(left).compareTo(a.get(right))>0)
            swap(a,left,right);
        if(a.get(mid).compareTo(a.get(right))>0)
            swap(a,mid,right);

        T pivot = a.get(mid);

        swap(a, mid, right);

        int lefts = left;

        for(int i = left; i < right; i++){
            if(a.get(i).compareTo(pivot) <= 0){
                swap(a, i, lefts);
                lefts++;
            }
        }

        swap(a, lefts, right);

        return lefts;
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        int size = right - left + 1;
        //first build the max heap
        //by calling heapify
        for(int i = (size)/2 - 1; i >= 0; i--){
            heapify(a, i, right);
        }


        //take root and swap with the last element in the array and then remove it.
        for(int i = right; i > left; i--) {
            swap(a, i, left);

            //ignore the sorted part of the array.

            heapify(a, left, i - 1);
        }
    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // Finish Me
        int largestIndex = left;
        if((2*left + 1) <= right && a.get(2*left + 1).compareTo(a.get(largestIndex))>0)
            largestIndex = 2*left + 1;


        if((2*left+2) <=  right && a.get(2*left + 2).compareTo(a.get(largestIndex)) >0)
            largestIndex = 2*left + 2;

        if(largestIndex != left){
            swap(a, largestIndex, left);

            heapify(a, largestIndex, right);
        }

    }

    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me
        int numComparisons = 0;

        for(int i = 0; i< a.size(); i++){
            for(int j = 1; j < a.size() - 1; j++){
                numComparisons++;
                if(a.get(j).compareTo(a.get(j+1)) > 0) {
                    swap(a, j, j+1);

                }
            }
        }
        return numComparisons;
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
        int numComparisons = 0;
        boolean isSorted = false;

        while(!isSorted){
            isSorted = true;

            for(int i = 0; i < size -1; i += 2){
                numComparisons++;
                if(a.get(i).compareTo(a.get(i+1))>0){
                    swap(a, i, i+1);
                    isSorted = false;
                }
            }

            for(int i = 1; i < size - 1; i +=2){
                if(a.get(i).compareTo(a.get(i+1))>0){
                    swap(a, i, i+1);
                    isSorted = false;
                }
            }
        }

        return numComparisons;


    }

    //METHODS NEEDED IN MAIN
    public static boolean oneElementIsEmpty(String[] s){
        for (String e : s) {
            if (e.isEmpty())
                return true;
        }
        return false;
    }

    public static void writeToFile(String content, String filePath) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        } catch(IOException e){
            System.out.println("error " + e);
        }

    }

    public static void main(String [] args)  throws IOException {
        //...
        // Finish Me
        //...

        if (args.length != 3) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String SpyCsv = args[0];
        String AlgorithmnType = args[1];
        int numLines = Integer.parseInt(args[2]);

        // For file input
        FileInputStream SpyCsvStream = null;
        Scanner SpyCsvScanner = null;

        // Open the input file
        SpyCsvStream = new FileInputStream(SpyCsv);
        SpyCsvScanner = new Scanner(SpyCsvStream);

        // ignore first line
        SpyCsvScanner.nextLine();

        // FINISH ME

        ArrayList<SPY> Array = new ArrayList<SPY>();

        String line;
        int count = 0;
        while (SpyCsvScanner.hasNext() && count < numLines) {

            line = SpyCsvScanner.nextLine();
            line = line.trim();

            if (line.isEmpty()) continue;

            //separates by the correct commas (avoids separating by commas that are within quotes.
            String[] p = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            if (p.length != 16 || oneElementIsEmpty(p))
                continue;

            SPY s = new SPY(p[0],
                    p[1],
                    p[2],
                    p[3],
                    p[4],
                    p[5],
                    Double.parseDouble(p[6]),
                    Double.parseDouble(p[7]),
                    Double.parseDouble(p[8]),
                    Double.parseDouble(p[9]),
                    p[10],
                    p[11],
                    p[12],
                    p[13],
                    p[14],
                    Double.parseDouble(p[15]));
            //check this
            Array.add(s);

            count++;

        }

        //Collections.shuffle(Array);

        //System.out.println(Array.toString());
        //System.out.println(Array.size());

        /*

        //These should be safe.

        Collections.sort(Array);
        ArrayList<SPY> Sorted = (ArrayList<SPY>)Array.clone();

        Collections.shuffle(Array);
        ArrayList<SPY> Shuffled = (ArrayList<SPY>)Array.clone();

        Collections.sort(Array, Collections.reverseOrder());
        ArrayList<SPY> Reversed = (ArrayList<SPY>)Array.clone();

        //String AlgorithmnType = args[1];



        long start;
        long averageTimeElapsed = 0;
        int NumberOfComparisons;

        if (AlgorithmnType.equals("mergeSort")){
            //For Sorted
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                mergeSort(Sorted, 0, Sorted.size() - 1);
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Sorted Merge Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

            //For Shuffled
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                mergeSort(Shuffled, 0, Shuffled.size() - 1);
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Shuffled Merge Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

            //For Reversed
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                mergeSort(Reversed, 0, Reversed.size() - 1);
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Reversed Merge Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");


        } else if(AlgorithmnType.equals("quickSort")){
            //For Sorted
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                quickSort(Sorted, 0, Sorted.size() - 1);
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Sorted Quick Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

            //For Shuffled
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                quickSort(Shuffled, 0, Shuffled.size() - 1);
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Shuffled Quick Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

            //For Reversed
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                quickSort(Reversed, 0, Reversed.size() - 1);
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Reversed Quick Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

        } else if(AlgorithmnType.equals("heapSort")){
            //For Sorted
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                heapSort(Sorted, 0, Sorted.size() - 1);
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Sorted Heap Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

            //For Shuffled
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                heapSort(Shuffled, 0, Shuffled.size() - 1);
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Shuffled Heap Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

            //For Reversed
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                heapSort(Reversed, 0, Reversed.size() - 1);
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Reversed Heap Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");


        } else if(AlgorithmnType.equals("bubbleSort")) {
            //For Sorted
            start = System.nanoTime();

            for (int i = 0; i < 4; i++) {
                NumberOfComparisons = bubbleSort(Sorted, Sorted.size());
            }
            averageTimeElapsed = (System.nanoTime() - start) / 5;
            writeToFile("Average Time Elapsed For Sorted Bubble Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines, "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile("Number of Comparisons " + NumberOfComparisons, "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

            //For Shuffled
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                NumberOfComparisons = bubbleSort(Shuffled, Shuffled.size());
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Shuffled Bubble Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile("Number of Comparisons " + NumberOfComparisons, "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

            //For Reversed
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                NumberOfComparisons = bubbleSort(Reversed, Reversed.size());
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Reversed Bubble Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile("Number of Comparisons " + NumberOfComparisons, "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

        } else if(AlgorithmnType.equals("transpositionSort")) {
            //For Sorted
            start = System.nanoTime();
            for (int i = 0; i < 4; i++) {
                NumberOfComparisons = transpositionSort(Sorted, Sorted.size());
            }
            averageTimeElapsed = (System.nanoTime() - start) / 5;
            writeToFile("Average Time Elapsed For Sorted Transposition Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines, "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile("Number of Comparisons " + NumberOfComparisons, "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

            //For Shuffled
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                NumberOfComparisons = transpositionSort(Shuffled, Shuffled.size());
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Shuffled Transposition Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile("Number of Comparisons " + NumberOfComparisons, "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            //For Reversed
            start = System.nanoTime();
            for(int i = 0; i < 4; i++) {
                NumberOfComparisons = transpositionSort(Reversed, Reversed.size());
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile("Average Time Elapsed For Reversed Transposition Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile(averageTimeElapsed + ", " + numLines,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
            writeToFile("Number of Comparisons " + NumberOfComparisons, "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        }

        */
        //ArrayList<SPY> Sorted = new ArrayList<SPY>(Array.subList(0, 20));
        //System.out.println(Sorted);

        writeToFile("Average Time Elapsed For Sorted Merge Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Merge", "Sorted");

        writeToFile("Average Time Elapsed For Sorted Quick Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Quick", "Sorted");

        writeToFile("Average Time Elapsed For Sorted Heap Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Heap", "Sorted");

        writeToFile("Average Time Elapsed For Sorted Bubble Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Bubble", "Sorted");

        writeToFile("Average Time Elapsed For Sorted Transposition Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Transposition", "Sorted");


        writeToFile("Shuffled", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

        writeToFile("Average Time Elapsed For Shuffled Merge Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Merge", "Shuffled");

        writeToFile("Average Time Elapsed For Shuffled Quick Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Quick", "Shuffled");

        writeToFile("Average Time Elapsed For Sorted Heap Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Heap", "Shuffled");

        writeToFile("Average Time Elapsed For Shuffled Bubble Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Bubble", "Shuffled");

        writeToFile("Average Time Elapsed For Sorted Transposition Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Transposition", "Shuffled");


        writeToFile("Reversed", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

        writeToFile("Average Time Elapsed For Reversed Merge Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Merge", "Reversed");

        writeToFile("Average Time Elapsed For Shuffled Quick Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Quick", "Reversed");

        writeToFile("Average Time Elapsed For Sorted Heap Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Heap", "Reversed");

        writeToFile("Average Time Elapsed For Shuffled Bubble Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Bubble", "Reversed");

        writeToFile("Average Time Elapsed For Sorted Transposition Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        Call(Array, "Transposition", "Reversed");

    }



    public static void Call(ArrayList<SPY> Array, String AlgType, String form){
        writeToFile(form + " " + AlgType + " Sort", "/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");
        long start;
        long averageTimeElapsed = 0;

        for(int i = 5; i <= 100; i += 5){

            ArrayList<SPY> newArray = new ArrayList<SPY>(Array.subList(0, i));
            if(form.equals("Sorted")) {
                Collections.sort(newArray);
            } else if(form.equals("Shuffled")){
                Collections.shuffle(newArray);
            } else{
                Collections.sort(newArray,Collections.reverseOrder());
            }

            start = System.nanoTime();

            for(int j = 0; j < 5; j++) {
                if(AlgType.equals("Merge")) {
                    mergeSort(newArray, 0, newArray.size() - 1);
                } else if(AlgType.equals("Quick")){
                    quickSort(newArray, 0, newArray.size()-1);
                } else if(AlgType.equals("Heap")){
                    heapSort(newArray, 0, newArray.size()-1);
                } else if(AlgType.equals("bubble")){
                    bubbleSort(newArray,newArray.size());
                } else if(AlgType.equals("transposition")){
                    transpositionSort(newArray, newArray.size());
                }
            }
            averageTimeElapsed = (System.nanoTime() - start)/ 5;
            writeToFile(averageTimeElapsed + ", " + i,"/Users/jackperry/IdeaProjects/project-3-JackPerry123/src/result.txt");

        }
    }
}
