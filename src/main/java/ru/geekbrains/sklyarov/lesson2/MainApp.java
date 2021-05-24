package ru.geekbrains.sklyarov.lesson2;

public class MainApp {
    private static final int DEFAULT_CAPACITY = 100_000;

    public static void main(String[] args) {
        /*
        attempt #1
            Unsorted array(100_000 rec). Bubble sort duration: 100911 ms
            Unsorted array(100_000 rec). Duration of sorting by selection: 28009 ms
            Unsorted array(100_000 rec). Sort duration on insert: 33241 ms
        attempt #2
            Unsorted array. Bubble sort duration: 100020 ms
            Unsorted array. Duration of sorting by selection: 25794 ms
            Unsorted array. Sort duration on insert: 29031 ms
         */
        UnsortedArrayAnalysis();
        /*
        attempt #1
            Sorted array(100_000 rec). Bubble sort duration: 53429 ms
            Sorted array(100_000 rec). Duration of sorting by selection: 10694 ms
            Sorted array(100_000 rec). Sort duration on insert: 0 ms
        attempt #2
            Sorted array. Bubble sort duration: 54370 ms
            Sorted array. Duration of sorting by selection: 10748 ms
            Sorted array. Sort duration on insert: 8 ms
         */
        sortedArrayAnalysis();
    }

    private static void UnsortedArrayAnalysis() {
        Array<Integer> arrBubble = new ArrayImpl<>(DEFAULT_CAPACITY);
        Array<Integer> arrSelect = new ArrayImpl<>(DEFAULT_CAPACITY);
        Array<Integer> arrInsert = new ArrayImpl<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            int value = (int)(Math.random() * DEFAULT_CAPACITY);
            arrBubble.add(value);
            arrSelect.add(value);
            arrInsert.add(value);
        }

        /*
            Unsorted array. Bubble sort duration: 100911 ms
         */
        long curTimeBubble = System.currentTimeMillis();
        arrBubble.sortBubble();
        System.out.printf("Unsorted array. Bubble sort duration: %d ms \n", (System.currentTimeMillis() - curTimeBubble));
        /*
            Unsorted array. Duration of sorting by selection: 28009 ms
         */
        long curTimeSelect = System.currentTimeMillis();
        arrSelect.sortSelect();
        System.out.printf("Unsorted array. Duration of sorting by selection: %d ms \n", (System.currentTimeMillis() - curTimeSelect));
        /*
            Unsorted array. Sort duration on insert: 33241 ms
         */
        long curTimeInsert = System.currentTimeMillis();
        arrInsert.sortInsert();
        System.out.printf("Unsorted array. Sort duration on insert: %d ms \n", (System.currentTimeMillis() - curTimeInsert));
    }

    private static void sortedArrayAnalysis() {

        Array<Integer> arrBubble = new SortedArrayImpl<>(DEFAULT_CAPACITY);
        Array<Integer> arrSelect = new SortedArrayImpl<>(DEFAULT_CAPACITY);
        Array<Integer> arrInsert = new SortedArrayImpl<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            int value = (int)(Math.random() * DEFAULT_CAPACITY);
            arrBubble.add(value);
            arrSelect.add(value);
            arrInsert.add(value);
        }

        /*
            Sorted array. Bubble sort duration: 53429 ms
         */
        long curTimeBubble = System.currentTimeMillis();
        arrBubble.sortBubble();
        System.out.printf("Sorted array. Bubble sort duration: %d ms \n", (System.currentTimeMillis() - curTimeBubble));
        /*
            Sorted array. Duration of sorting by selection: 10694 ms
         */
        long curTimeSelect = System.currentTimeMillis();
        arrSelect.sortSelect();
        System.out.printf("Sorted array. Duration of sorting by selection: %d ms \n", (System.currentTimeMillis() - curTimeSelect));
        /*
            Sorted array. Sort duration on insert: 0 ms
         */
        long curTimeInsert = System.currentTimeMillis();
        arrInsert.sortInsert();
        System.out.printf("Sorted array. Sort duration on insert: %d ms \n", (System.currentTimeMillis() - curTimeInsert));
    }
}
