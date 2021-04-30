package com.imooc.architect.robert.code.chapter1.learn;

/**
 * @author zhangyoubao
 * @version 2021/4/28
 */
public class BinarySearch {

    public static int rank(int key, int[] arr) {
        int lo = 0;
        int hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (key < arr[mid]) hi = mid - 1;
            else if (key > arr[mid]) lo = mid + 1;
            else if (key == arr[mid]) return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10,11,12,16,18,23,29,33,48,54,57,68,77,84,98};
        int rank = rank(50, arr);
        System.out.println(rank);
    }
}
