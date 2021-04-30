package com.imooc.architect.aditya.code;

import java.util.Arrays;

/**
 * 快速排序-分治思想
 * @author zhangyoubao
 * @version 2021/4/14
 */
public class QuickSort {

    public static int[] sort(int [] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start >= end) return;
        int mid = partition(array, start, end);
        quickSort(array, start, mid - 1);
        quickSort(array, mid + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int left = start + 1, right = end;
        while (left < right) {
            while (left < right && array[left] <= pivot) {
                left ++;
            }
            while (left < right && array[right] >= pivot) {
                right --;
            }
            if (left < right) {
                swap(array, left, right);
                left ++;
                right --;
            }
        }
        if (left == right && array[right] > pivot) right --;
        swap(array, start, right);
        return right;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 6, 7, 1, 8};
        System.out.println(Arrays.toString(sort(arr)));
    }
}
