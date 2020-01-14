package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IntSequenceUtils {
    private IntSequenceUtils() {
        throw new IllegalStateException("Utility class");
    }
    static List<Integer> createNewList() {
        return createNewList(new ArrayList<>(Arrays.asList(6, -1, 2, -3, 4, -5, 10, -14, 4, 1, 1, 1, 1, 5)));
    }
    static List<Integer> createNewList(List<Integer> list) {
        int[][] sequencesSums = sumAllSequences(list);
//        printArray(sequencesSums);
        int size = sequencesSums.length;
        int maxSumLineIndex = 0;
        int maxSumColumnIndex = 0;
        int maxSum = sequencesSums[maxSumLineIndex][maxSumColumnIndex];
        for (int line = 0; line < size; line++) {
            for (int column = 0; column < size; column++) {
                if (sequencesSums[line][column] > maxSum) {
                    maxSum = sequencesSums[line][column];
                    maxSumLineIndex = line;
                    maxSumColumnIndex = column;
                }
                if (sequencesSums[line][column] == maxSum && line < maxSumLineIndex) {
                    maxSum = sequencesSums[line][column];
                    maxSumLineIndex = line;
                    maxSumColumnIndex = column;
                }
            }
        }
        int from = maxSumColumnIndex;
        int to = maxSumColumnIndex + maxSumLineIndex + 1;

        return list.subList(from, to);
    }

    private static void printArray(int[][] sequencesSums) {
        for ( int[] sums : sequencesSums) {
            System.out.println(Arrays.toString(sums));
        }
    }

    private static int[][] sumAllSequences(List<Integer> list) {
        int size = list.size();
        int[][] sums = new int[size][size];
        for (int line = 0, offset = 0; line < size; line++, offset++) {
            if (line == 0) {
                fillFirstLine(list, size, sums, line);
            } else for (int column = 0; column < size; column++) {
                if ((column + offset) == size) break;
                sums[line][column] = sums[line - 1][column] + list.get(column + offset);
            }
        }
        return sums;
    }

    private static void fillFirstLine(List<Integer> list, int size, int[][] sums, int line) {
        for (int column = 0; column < size; column++) {
            sums[line][column] = list.get(column);
        }
    }
}
