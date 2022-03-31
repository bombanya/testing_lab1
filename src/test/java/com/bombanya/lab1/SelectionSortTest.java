package com.bombanya.lab1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SelectionSortTest {

    @Test
    void sortTest1(){
        Integer[] arr = {3, 2, 1};
        List<Integer[]> iterations = Arrays.asList(
                new Integer[]{1, 2, 3},
                new Integer[]{1, 2, 3});
        assertTrue(checkIterations(iterations, SelectionSort.sort(arr)));
    }

    @Test
    void sortTest2(){
        Integer[] arr = {12, 5, 2, 0, -4, 6, 10, 2};
        List<Integer[]> iterations = Arrays.asList(
                new Integer[]{-4, 5, 2, 0, 12, 6, 10, 2},
                new Integer[]{-4, 0, 2, 5, 12, 6, 10, 2},
                new Integer[]{-4, 0, 2, 5, 12, 6, 10, 2},
                new Integer[]{-4, 0, 2, 2, 12, 6, 10, 5},
                new Integer[]{-4, 0, 2, 2, 5, 6, 10, 12},
                new Integer[]{-4, 0, 2, 2, 5, 6, 10, 12},
                new Integer[]{-4, 0, 2, 2, 5, 6, 10, 12});
        assertTrue(checkIterations(iterations, SelectionSort.sort(arr)));
    }

    @Test
    void sortTest3(){
        Integer[] arr = {17, 1, 3, -9, 80, -17, 4};
        List<Integer[]> iterations = Arrays.asList(
                new Integer[]{-17, 1, 3, -9, 80, 17, 4},
                new Integer[]{-17, -9, 3, 1, 80, 17, 4},
                new Integer[]{-17, -9, 1, 3, 80, 17, 4},
                new Integer[]{-17, -9, 1, 3, 80, 17, 4},
                new Integer[]{-17, -9, 1, 3, 4, 17, 80},
                new Integer[]{-17, -9, 1, 3, 4, 17, 80}
        );
        assertTrue(checkIterations(iterations, SelectionSort.sort(arr)));
    }

    @Test
    void sortTest4(){
        Integer[] arr = {666};
        List<Integer[]> iterations  = new ArrayList<>();
        assertTrue(checkIterations(iterations, SelectionSort.sort(arr)));
    }

    @Test
    void sortTest5(){
        Integer[] arr = {1, 2, 3};
        List<Integer[]> iterations = Arrays.asList(
                new Integer[]{1, 2, 3},
                new Integer[]{1, 2, 3});
        assertTrue(checkIterations(iterations, SelectionSort.sort(arr)));
    }

    @Test
    void sortTest6(){
        Integer[] arr = {1000, 1000, 1000};
        List<Integer[]> iterations = Arrays.asList(
                new Integer[]{1000, 1000, 1000},
                new Integer[]{1000, 1000, 1000});
        assertTrue(checkIterations(iterations, SelectionSort.sort(arr)));
    }

    @Test
    void sortTestNPE(){
        assertThrows(NullPointerException.class, () -> SelectionSort.sort(null));
    }

    @Test
    void sortTestEmptyArray(){
        assertTrue(checkIterations(new ArrayList<>(), SelectionSort.sort(new Integer[]{})));
    }

    @Test
    void sortTestStrings(){
        String[] arr = {"testB", "testA", "testD", "testC"};
        List<String[]> iterations = Arrays.asList(
                new String[]{"testA", "testB", "testD", "testC"},
                new String[]{"testA", "testB", "testD", "testC"},
                new String[]{"testA", "testB", "testC", "testD"}
        );
        assertTrue(checkIterations(iterations, SelectionSort.sort(arr)));
    }

    private static <T> boolean checkIterations(List<T[]> expected, List<T[]> actual){
        if (expected.size() != actual.size()) return false;
        for (int i = 0; i < expected.size(); i++){
            if (expected.get(i).length != actual.get(i).length) return false;
            for (int j = 0; j < expected.get(i).length; j++){
                if (!expected.get(i)[j].equals(actual.get(i)[j])) return false;
            }
        }
        return true;
    }
}